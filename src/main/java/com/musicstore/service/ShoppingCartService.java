package com.musicstore.service;

import com.musicstore.model.Album;
import com.musicstore.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

    @Autowired
    private AlbumService albumService;

    private final ConcurrentMap<Long, CartItem> cartItems = new ConcurrentHashMap<>();

    public void addItem(Long albumId, int quantity) {
        Album album = albumService.getAlbumById(albumId);
        if (album != null && albumService.isAlbumAvailable(albumId, quantity)) {
            CartItem existingItem = cartItems.get(albumId);
            if (existingItem != null) {
                int newQuantity = existingItem.getQuantity() + quantity;
                if (albumService.isAlbumAvailable(albumId, newQuantity)) {
                    existingItem.setQuantity(newQuantity);
                }
            } else {
                cartItems.put(albumId, new CartItem(album, quantity));
            }
        }
    }

    public void removeItem(Long albumId) {
        cartItems.remove(albumId);
    }

    public void updateQuantity(Long albumId, int quantity) {
        CartItem item = cartItems.get(albumId);
        if (item != null) {
            if (quantity <= 0) {
                removeItem(albumId);
            } else if (albumService.isAlbumAvailable(albumId, quantity)) {
                item.setQuantity(quantity);
            }
        }
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems.values());
    }

    public BigDecimal getTotal() {
        return cartItems.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getCartItemCount() {
        return cartItems.values().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public CartItem getCartItem(Long albumId) {
        return cartItems.get(albumId);
    }

    // Metoda do finalizacji zakupu - zmniejsza stan magazynowy
    public boolean checkout() {
        // Sprawdź czy wszystkie produkty są dostępne w wymaganej ilości
        for (CartItem item : cartItems.values()) {
            if (!albumService.isAlbumAvailable(item.getAlbum().getId(), item.getQuantity())) {
                return false;
            }
        }

        // Zmniejsz stan magazynowy
        for (CartItem item : cartItems.values()) {
            albumService.decreaseStock(item.getAlbum().getId(), item.getQuantity());
        }

        clearCart();
        return true;
    }
}