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
        initializeAlbums();
    }

    private void initializeAlbums() {
        // Album 1: Thriller - Michael Jackson
        Album thriller = new Album(1L, "Thriller", "Michael Jackson", "Pop", 2025,
                new BigDecimal("29.99"),
                "Najlepiej sprzedający się album wszech czasów. Zawiera takie hity jak 'Thriller', 'Billie Jean' i 'Beat It'.",
                "https://upload.wikimedia.org/wikipedia/en/5/55/Michael_Jackson_-_Thriller.png",
                15, 5, null, false);
        thriller.setAdditionalImages(Arrays.asList(
                "https://globalnews.ca/wp-content/uploads/2017/11/michael-jackson-thriller.jpg?quality=65&strip=all",
                "https://i.ebayimg.com/images/g/Y7wAAOSwHzRjayB5/s-l400.jpg",
                "https://i.etsystatic.com/36791234/r/il/8c3f88/4893524045/il_570xN.4893524045_qh89.jpg"
        ));
        albums.add(thriller);

        // Album 2: Back in Black - AC/DC
        Album backInBlack = new Album(2L, "Back in Black", "AC/DC", "Rock", 2025,
                new BigDecimal("24.99"),
                "Klasyczny album hard rockowy, jeden z najlepiej sprzedających się albumów w historii.",
                "https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png",
                3, 5, null, false);
        backInBlack.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png",
                "https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png",
                "https://upload.wikimedia.org/wikipedia/commons/9/92/ACDC_Back_in_Black.png"
        ));
        albums.add(backInBlack);

        // Album 3: The Dark Side of the Moon - Pink Floyd
        Album darkSide = new Album(3L, "The Dark Side of the Moon", "Pink Floyd", "Rock", 2025,
                new BigDecimal("27.99"),
                "Kultowy album progresywnego rocka, znany z innowacyjnej produkcji i głębokich tekstów.",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
                25, 5, null, true);
        darkSide.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png"
        ));
        albums.add(darkSide);

        // Album 4: Rumours - Fleetwood Mac
        Album rumours = new Album(4L, "Rumours", "Fleetwood Mac", "Rock", 1977,
                new BigDecimal("22.99"),
                "Jeden z najlepiej sprzedających się albumów w historii, stworzony podczas trudnego okresu w relacjach zespołu.",
                "https://upload.wikimedia.org/wikipedia/en/f/fb/FMacRumours.PNG",
                0, 5, null, false);
        rumours.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/f/fb/FMacRumours.PNG",
                "https://upload.wikimedia.org/wikipedia/en/f/fb/FMacRumours.PNG"
        ));
        albums.add(rumours);

        // Album 5: Bad - Michael Jackson
        Album bad = new Album(5L, "Bad", "Michael Jackson", "Pop", 1987,
                new BigDecimal("26.99"),
                "Kolejny wielki sukces Michaela Jacksona z hitami 'Bad', 'The Way You Make Me Feel' i 'Man in the Mirror'.",
                "https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png",
                8, 5, null, false);
        bad.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png",
                "https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png",
                "https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png"
        ));
        albums.add(bad);

        // Album 6: Nevermind - Nirvana
        Album nevermind = new Album(6L, "Nevermind", "Nirvana", "Grunge", 1991,
                new BigDecimal("25.99"),
                "Album, który zdefiniował grunge i wprowadził alternatywny rock do głównego nurtu.",
                "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg",
                12, 5, null, false);
        nevermind.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg",
                "https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg"
        ));
        albums.add(nevermind);

        // Album 7: The Joshua Tree - U2
        Album joshuaTree = new Album(7L, "The Joshua Tree", "U2", "Rock", 1987,
                new BigDecimal("23.99"),
                "Przełomowy album irlandzkiej grupy, łączący rock z elementami folk i americana.",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                20, 5, LocalDate.now().plusDays(14), false);
        joshuaTree.setAdditionalImages(Arrays.asList(
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg",
                "https://m.media-amazon.com/images/I/61cWZs9qZ2L._UF894,1000_QL80_.jpg"
        ));
        albums.add(joshuaTree);

        // Album 8: Kind of Blue - Miles Davis
        Album kindOfBlue = new Album(8L, "Kind of Blue", "Miles Davis", "Jazz", 1959,
                new BigDecimal("28.99"),
                "Najlepiej sprzedający się album jazzowy w historii, arcydzieło modalnego jazzu.",
                "https://jazz.pl/wp-content/uploads/2015/07/KindofBlueAlbum.jpg",
                6, 5, null, true);
        kindOfBlue.setAdditionalImages(Arrays.asList(
                "https://jazz.pl/wp-content/uploads/2015/07/KindofBlueAlbum.jpg",
                "https://jazz.pl/wp-content/uploads/2015/07/KindofBlueAlbum.jpg",
                "https://jazz.pl/wp-content/uploads/2015/07/KindofBlueAlbum.jpg"
        ));
        albums.add(kindOfBlue);

        // Album 9: Abbey Road - The Beatles
        Album abbeyRoad = new Album(9L, "Abbey Road", "The Beatles", "Rock", 1969,
                new BigDecimal("30.99"),
                "Ostatni nagrany album Beatlesów, znany z ikonicznej okładki i medleyu na drugiej stronie.",
                "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg",
                18, 5, null, false);
        abbeyRoad.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg",
                "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg"
        ));
        albums.add(abbeyRoad);

        // Album 10: The Marshall Mathers LP - Eminem
        Album marshallMathers = new Album(10L, "The Marshall Mathers LP", "Eminem", "Hip-Hop", 2000,
                new BigDecimal("24.99"),
                "Kontrowersyjny i przełomowy album, który ugruntował pozycję Eminema w świecie hip-hopu.",
                "https://cdn-images.dzcdn.net/images/cover/941c2d3c366affdc662956559e078a4e/1900x1900-000000-81-0-0.jpg",
                2, 5, null, false);
        marshallMathers.setAdditionalImages(Arrays.asList(
                "https://cdn-images.dzcdn.net/images/cover/941c2d3c366affdc662956559e078a4e/1900x1900-000000-81-0-0.jpg",
                "https://cdn-images.dzcdn.net/images/cover/941c2d3c366affdc662956559e078a4e/1900x1900-000000-81-0-0.jpg"
        ));
        albums.add(marshallMathers);

        // Album 11: Random Access Memories - Daft Punk
        Album randomAccess = new Album(11L, "Random Access Memories", "Daft Punk", "Electronic", 2013,
                new BigDecimal("26.99"),
                "Nagrodzony Grammy album, który połączył elektronikę z żywymi instrumentami.",
                "https://cdn-images.dzcdn.net/images/cover/311bba0fc112d15f72c8b5a65f0456c1/1900x1900-000000-80-0-0.jpg",
                0, 5, LocalDate.now().plusDays(7), false);
        randomAccess.setAdditionalImages(Arrays.asList(
                "https://cdn-images.dzcdn.net/images/cover/311bba0fc112d15f72c8b5a65f0456c1/1900x1900-000000-80-0-0.jpg",
                "https://cdn-images.dzcdn.net/images/cover/311bba0fc112d15f72c8b5a65f0456c1/1900x1900-000000-80-0-0.jpg"
        ));
        albums.add(randomAccess);

        // Album 12: Hotel California - Eagles
        Album hotelCalifornia = new Album(12L, "Hotel California", "Eagles", "Rock", 1976,
                new BigDecimal("22.99"),
                "Klasyk rocka z kultowym tytułowym utworem i 'New Kid in Town'.",
                "https://upload.wikimedia.org/wikipedia/en/4/49/Hotelcalifornia.jpg",
                30, 5, null, false);
        hotelCalifornia.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/4/49/Hotelcalifornia.jpg",
                "https://upload.wikimedia.org/wikipedia/en/4/49/Hotelcalifornia.jpg"
        ));
        albums.add(hotelCalifornia);

        // Album 13: Future Nostalgia - Dua Lipa
        Album futureNostalgia = new Album(13L, "Future Nostalgia", "Dua Lipa", "Pop", 2020,
                new BigDecimal("23.99"),
                "Nagrodzony Grammy album z hitami 'Don't Start Now' i 'Physical'.",
                "https://m.media-amazon.com/images/I/71VQFsqlPJL.jpg",
                4, 5, null, true);
        futureNostalgia.setAdditionalImages(Arrays.asList(
                "https://m.media-amazon.com/images/I/71VQFsqlPJL.jpg",
                "https://m.media-amazon.com/images/I/71VQFsqlPJL.jpg",
                "https://m.media-amazon.com/images/I/71VQFsqlPJL.jpg"
        ));
        albums.add(futureNostalgia);

        // Album 14: After Hours - The Weeknd
        Album afterHours = new Album(14L, "After Hours", "The Weeknd", "R&B", 2020,
                new BigDecimal("25.99"),
                "Krytycznie chwalony album z singlem 'Blinding Lights'.",
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Weeknd_-_After_Hours.png",
                0, 5, LocalDate.now().plusDays(21), true);
        afterHours.setAdditionalImages(Arrays.asList(
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Weeknd_-_After_Hours.png",
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Weeknd_-_After_Hours.png",
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Weeknd_-_After_Hours.png"
        ));
        albums.add(afterHours);
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
        return albums.stream()
                .filter(album -> album.getReleaseYear() >= LocalDate.now().getYear() - 1)
                .sorted((a1, a2) -> Integer.compare(a2.getReleaseYear(), a1.getReleaseYear()))
                .collect(Collectors.toList());
    }

    public List<Album> getPreorderAlbums() {
        return albums.stream()
                .filter(Album::isPreorder)
                .sorted((a1, a2) -> {
                    if (a1.getAvailableFrom() == null && a2.getAvailableFrom() == null) return 0;
                    if (a1.getAvailableFrom() == null) return 1;
                    if (a2.getAvailableFrom() == null) return -1;
                    return a1.getAvailableFrom().compareTo(a2.getAvailableFrom());
                })
                .collect(Collectors.toList());
    }
}