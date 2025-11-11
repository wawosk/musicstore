package com.musicstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private int releaseYear;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private int stockQuantity;
    private int lowStockThreshold;
    private LocalDate availableFrom;
    private boolean limitedEdition;
    private List<String> additionalImages; // Lista dodatkowych obrazów

    // Konstruktory
    public Album() {
        this.additionalImages = new ArrayList<>();
    }

    public Album(Long id, String title, String artist, String genre,
                 int releaseYear, BigDecimal price, String description,
                 String imageUrl, int stockQuantity, int lowStockThreshold,
                 LocalDate availableFrom, boolean limitedEdition) {
        this();
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
        this.availableFrom = availableFrom;
        this.limitedEdition = limitedEdition;
    }

    public Album(Long id, String title, String artist, String genre,
                 int releaseYear, BigDecimal price, String description,
                 String imageUrl, int stockQuantity, int lowStockThreshold,
                 LocalDate availableFrom, boolean limitedEdition, List<String> additionalImages) {
        this(id, title, artist, genre, releaseYear, price, description, imageUrl,
                stockQuantity, lowStockThreshold, availableFrom, limitedEdition);
        this.additionalImages = additionalImages != null ? additionalImages : new ArrayList<>();
    }

    // Gettery i settery
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public int getLowStockThreshold() { return lowStockThreshold; }
    public void setLowStockThreshold(int lowStockThreshold) { this.lowStockThreshold = lowStockThreshold; }

    public LocalDate getAvailableFrom() { return availableFrom; }
    public void setAvailableFrom(LocalDate availableFrom) { this.availableFrom = availableFrom; }

    public boolean isLimitedEdition() { return limitedEdition; }
    public void setLimitedEdition(boolean limitedEdition) { this.limitedEdition = limitedEdition; }

    public List<String> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages != null ? additionalImages : new ArrayList<>();
    }

    public void addAdditionalImage(String imageUrl) {
        if (this.additionalImages == null) {
            this.additionalImages = new ArrayList<>();
        }
        this.additionalImages.add(imageUrl);
    }

    // Metody pomocnicze
    public boolean isLowStock() {
        return stockQuantity > 0 && stockQuantity <= lowStockThreshold;
    }

    public boolean isOutOfStock() {
        return stockQuantity <= 0;
    }

    public boolean isPreorder() {
        return availableFrom != null && availableFrom.isAfter(LocalDate.now());
    }

    public boolean isAvailable() {
        return !isOutOfStock() && !isPreorder();
    }

    public String getAvailabilityStatus() {
        if (isOutOfStock()) {
            return "OUT_OF_STOCK";
        } else if (isPreorder()) {
            return "PREORDER";
        } else if (isLowStock()) {
            return "LOW_STOCK";
        } else if (isLimitedEdition()) {
            return "LIMITED_EDITION";
        } else {
            return "AVAILABLE";
        }
    }

    public String getAvailabilityMessage() {
        switch (getAvailabilityStatus()) {
            case "OUT_OF_STOCK":
                return "Produkt niedostępny";
            case "PREORDER":
                return "Dostępny od " + availableFrom;
            case "LOW_STOCK":
                return "Ostatnie " + stockQuantity + " egzemplarzy!";
            case "LIMITED_EDITION":
                return "Limitowana edycja";
            default:
                return "Dostępny";
        }
    }
}