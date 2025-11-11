package com.musicstore.service;

import com.musicstore.model.Album;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private List<Album> albums = new ArrayList<>();

    public AlbumService() {
        // Inicjalizacja przykładowych danych z zarządzaniem stanem magazynowym
        initializeAlbums();
    }

    private void initializeAlbums() {
        // Używaj lokalnych ścieżek do obrazów
        String baseImagePath = "/images/albums/";

        albums.add(new Album(1L, "Thriller", "Michael Jackson", "Pop", 2025,
                new BigDecimal("29.99"),
                "Najlepiej sprzedający się album wszech czasów. Zawiera takie hity jak 'Thriller', 'Billie Jean' i 'Beat It'.",
                "https://upload.wikimedia.org/wikipedia/en/5/55/Michael_Jackson_-_Thriller.png",
                15, 5, null, false));

        albums.add(new Album(2L, "Back in Black", "AC/DC", "Rock", 2025,
                new BigDecimal("24.99"),
                "Klasyczny album hard rockowy, jeden z najlepiej sprzedających się albumów w historii.",
                "https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png",
                3, 5, null, false)); // Ostatnie egzemplarze

        albums.add(new Album(3L, "The Dark Side of the Moon", "Pink Floyd", "Rock", 2025,
                new BigDecimal("27.99"),
                "Kultowy album progresywnego rocka, znany z innowacyjnej produkcji i głębokich tekstów.",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
                25, 5, null, true)); // Limitowana edycja

        albums.add(new Album(4L, "Rumours", "Fleetwood Mac", "Rock", 1977,
                new BigDecimal("22.99"),
                "Jeden z najlepiej sprzedających się albumów w historii, stworzony podczas trudnego okresu w relacjach zespołu.",
                "https://upload.wikimedia.org/wikipedia/en/f/fb/FMacRumours.PNG",
                0, 5, null, false)); // Niedostępny

        albums.add(new Album(5L, "Bad", "Michael Jackson", "Pop", 1987,
                new BigDecimal("26.99"),
                "Kolejny wielki sukces Michaela Jacksona z hitami 'Bad', 'The Way You Make Me Feel' i 'Man in the Mirror'.",
                "https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png",
                8, 5, null, false)); // Ostatnie egzemplarze

        albums.add(new Album(6L, "Nevermind", "Nirvana", "Grunge", 1991,
                new BigDecimal("25.99"),
                "Album, który zdefiniował grunge i wprowadził alternatywny rock do głównego nurtu.",
                "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg",
                12, 5, null, false));

        albums.add(new Album(7L, "The Joshua Tree", "U2", "Rock", 1987,
                new BigDecimal("23.99"),
                "Przełomowy album irlandzkiej grupy, łączący rock z elementami folk i americana.",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                20, 5, LocalDate.now().plusDays(14), false)); // Przedpremiera

        albums.add(new Album(8L, "Kind of Blue", "Miles Davis", "Jazz", 1959,
                new BigDecimal("28.99"),
                "Najlepiej sprzedający się album jazzowy w historii, arcydzieło modalnego jazzu.",
                "https://jazz.pl/wp-content/uploads/2015/07/KindofBlueAlbum.jpg",
                6, 5, null, true)); // Limitowana edycja + ostatnie egzemplarze

        albums.add(new Album(9L, "Abbey Road", "The Beatles", "Rock", 1969,
                new BigDecimal("30.99"),
                "Ostatni nagrany album Beatlesów, znany z ikonicznej okładki i medleyu na drugiej stronie.",
                "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg",
                18, 5, null, false));

        albums.add(new Album(10L, "The Marshall Mathers LP", "Eminem", "Hip-Hop", 2000,
                new BigDecimal("24.99"),
                "Kontrowersyjny i przełomowy album, który ugruntował pozycję Eminema w świecie hip-hopu.",
                "https://cdn-images.dzcdn.net/images/cover/941c2d3c366affdc662956559e078a4e/1900x1900-000000-81-0-0.jpg",
                2, 5, null, false)); // Bardzo mało egzemplarzy

        albums.add(new Album(11L, "Random Access Memories", "Daft Punk", "Electronic", 2013,
                new BigDecimal("26.99"),
                "Nagrodzony Grammy album, który połączył elektronikę z żywymi instrumentami.",
                "https://cdn-images.dzcdn.net/images/cover/311bba0fc112d15f72c8b5a65f0456c1/1900x1900-000000-80-0-0.jpg",
                0, 5, LocalDate.now().plusDays(7), false)); // Przedpremiera + niedostępny

        albums.add(new Album(12L, "Hotel California", "Eagles", "Rock", 1976,
                new BigDecimal("22.99"),
                "Klasyk rocka z kultowym tytułowym utworem i 'New Kid in Town'.",
                "https://upload.wikimedia.org/wikipedia/en/4/49/Hotelcalifornia.jpg",
                30, 5, null, false));

        // Metoda do zmniejszania stanu magazynowego
        albums.add(new Album(13L, "Future Nostalgia", "Dua Lipa", "Pop", 2020,
                new BigDecimal("23.99"),
                "Nagrodzony Grammy album z hitami 'Don't Start Now' i 'Physical'.",
                "https://m.media-amazon.com/images/I/71VQFsqlPJL.jpg",
                4, 5, null, true)); // Limitowana edycja + ostatnie egzemplarze

        albums.add(new Album(14L, "After Hours", "The Weeknd", "R&B", 2020,
                new BigDecimal("25.99"),
                "Krytycznie chwalony album z singlem 'Blinding Lights'.",
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Weeknd_-_After_Hours.png",
                0, 5, LocalDate.now().plusDays(21), true)); // Przedpremiera + limitowana edycja
    }

    public List<Album> getAllAlbums() {
        return new ArrayList<>(albums);
    }

    public List<Album> getAvailableAlbums() {
        return albums.stream()
                .filter(Album::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Album> getFeaturedAlbums() {
        return getAvailableAlbums().stream()
                .limit(8)
                .collect(Collectors.toList());
    }

    public Album getAlbumById(Long id) {
        Optional<Album> album = albums.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        return album.orElse(null);
    }

    public List<Album> getAlbumsByGenre(String genre) {
        return albums.stream()
                .filter(album -> album.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres() {
        return Arrays.asList("Pop", "Rock", "Jazz", "Hip-Hop", "Electronic", "Grunge", "R&B");
    }

    // Metoda do zmniejszania stanu magazynowego po zakupie
    public boolean decreaseStock(Long albumId, int quantity) {
        Album album = getAlbumById(albumId);
        if (album != null && album.getStockQuantity() >= quantity) {
            album.setStockQuantity(album.getStockQuantity() - quantity);
            return true;
        }
        return false;
    }

    // Metoda do sprawdzania dostępności
    public boolean isAlbumAvailable(Long albumId, int quantity) {
        Album album = getAlbumById(albumId);
        return album != null && album.isAvailable() && album.getStockQuantity() >= quantity;
    }



    public List<Album> getNewAlbums() {
        LocalDate cutoffDate = LocalDate.now().minusMonths(3); // Albumy z ostatnich 3 miesięcy
        return albums.stream()
                .filter(album -> album.getReleaseYear() >= LocalDate.now().getYear() - 1) // Albumy z ostatniego roku
                .sorted((a1, a2) -> Integer.compare(a2.getReleaseYear(), a1.getReleaseYear())) // Sortuj od najnowszych
                .collect(Collectors.toList());
    }

    public List<Album> getPreorderAlbums() {
        return albums.stream()
                .filter(Album::isPreorder)
                .sorted((a1, a2) -> {
                    // Sortuj według daty dostępności (najbliższe pierwsze)
                    if (a1.getAvailableFrom() == null && a2.getAvailableFrom() == null) return 0;
                    if (a1.getAvailableFrom() == null) return 1;
                    if (a2.getAvailableFrom() == null) return -1;
                    return a1.getAvailableFrom().compareTo(a2.getAvailableFrom());
                })
                .collect(Collectors.toList());
    }









}