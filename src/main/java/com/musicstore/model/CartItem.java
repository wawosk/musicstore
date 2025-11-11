package com.musicstore.model;

import java.math.BigDecimal;

public class CartItem {
    private Album album;
    private int quantity;

    public CartItem() {}

    public CartItem(Album album, int quantity) {
        this.album = album;
        this.quantity = quantity;
    }

    // Getters and setters
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        if (album != null && album.getPrice() != null) {
            return album.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return album != null ? album.getId().equals(cartItem.album.getId()) : cartItem.album == null;
    }

    @Override
    public int hashCode() {
        return album != null ? album.getId().hashCode() : 0;
    }
}