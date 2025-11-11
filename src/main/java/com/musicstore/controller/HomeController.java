package com.musicstore.controller;

import com.musicstore.model.Album;
import com.musicstore.service.AlbumService;
import com.musicstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/")
    public String home(Model model) {
        List<Album> featuredAlbums = albumService.getFeaturedAlbums();
        model.addAttribute("featuredAlbums", featuredAlbums);
        return "index";
    }

    @GetMapping("/albums")
    public String albums(@RequestParam(value = "genre", required = false) String genre,
                         @RequestParam(value = "showAll", defaultValue = "false") boolean showAll,
                         Model model) {
        List<Album> albums;
        if (genre != null && !genre.isEmpty()) {
            albums = albumService.getAlbumsByGenre(genre);
        } else if (showAll) {
            albums = albumService.getAllAlbums();
        } else {
            albums = albumService.getAvailableAlbums();
        }
        model.addAttribute("albums", albums);
        model.addAttribute("genres", albumService.getAllGenres());
        model.addAttribute("showAll", showAll);
        return "albums";
    }

    @GetMapping("/album/{id}")
    public String albumDetails(@PathVariable Long id, Model model) {
        Album album = albumService.getAlbumById(id);
        if (album == null) {
            return "redirect:/albums";
        }
        model.addAttribute("album", album);
        return "album-details";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long albumId,
                            @RequestParam(defaultValue = "1") int quantity,
                            RedirectAttributes redirectAttributes) {
        Album album = albumService.getAlbumById(albumId);
        if (album == null) {
            redirectAttributes.addFlashAttribute("error", "Album nie został znaleziony.");
            return "redirect:/albums";
        }

        if (!albumService.isAlbumAvailable(albumId, quantity)) {
            redirectAttributes.addFlashAttribute("error", "Niewystarczająca ilość produktu na stanie.");
            return "redirect:/album/" + albumId;
        }

        cartService.addItem(albumId, quantity);
        redirectAttributes.addFlashAttribute("success", "Produkt dodany do koszyka!");
        return "redirect:/album/" + albumId;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.getTotal());
        model.addAttribute("itemCount", cartService.getCartItemCount());
        model.addAttribute("isEmpty", cartService.isEmpty());
        return "cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long albumId) {
        cartService.removeItem(albumId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Long albumId, @RequestParam int quantity) {
        cartService.updateQuantity(albumId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        if (cartService.checkout()) {
            redirectAttributes.addFlashAttribute("success", "Zamówienie złożone pomyślnie!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Niektóre produkty są niedostępne w wymaganej ilości.");
        }
        return "redirect:/cart";
    }

    // Endpoint dla AJAX - liczba produktów w koszyku
    @GetMapping("/cart/count")
    @ResponseBody
    public int getCartCount() {
        return cartService.getCartItemCount();
    }



    @GetMapping("/albums/new")
    public String newAlbums(Model model) {
        List<Album> newAlbums = albumService.getNewAlbums();
        model.addAttribute("albums", newAlbums);
        model.addAttribute("genres", albumService.getAllGenres());
        model.addAttribute("showAll", false);
        model.addAttribute("pageTitle", "Nowe albumy");
        return "albums";
    }

    @GetMapping("/albums/preorders")
    public String preorders(Model model) {
        List<Album> preorderAlbums = albumService.getPreorderAlbums();
        model.addAttribute("albums", preorderAlbums);
        model.addAttribute("genres", albumService.getAllGenres());
        model.addAttribute("showAll", false);
        model.addAttribute("pageTitle", "Przedpremiery");
        return "albums";
    }





}