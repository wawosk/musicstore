// Funkcje pomocnicze dla koszyka
document.addEventListener('DOMContentLoaded', function() {
    // Inicjalizacja licznika koszyka przy załadowaniu strony
    updateCartCount();

    // Aktualizacja ilości w koszyku
    const quantityInputs = document.querySelectorAll('.quantity-input');
    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            const form = this.closest('form');
            if (form) {
                form.submit();
            }
        });
    });

    // Walidacja formularza
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const quantityInput = this.querySelector('input[name="quantity"]');
            if (quantityInput && quantityInput.value < 1) {
                e.preventDefault();
                alert('Ilość musi być większa niż 0');
                quantityInput.focus();
            }
        });
    });

    // POPRAWIONE: Dodawanie do koszyka z poziomu listy albumów (TYLKO dla przycisków poza stroną szczegółów)
    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn:not(.album-detail .add-to-cart-btn)');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();

            const albumId = this.getAttribute('data-album-id');
            if (albumId && !this.disabled) {
                addToCart(albumId, 1);
            }
        });
    });

    // Animacje
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = 1;
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);

    // Obserwuj elementy do animacji
    const animatedElements = document.querySelectorAll('.album-card, .feature');
    animatedElements.forEach(el => {
        el.style.opacity = 0;
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(el);
    });

    // Powiadomienie o dodaniu do koszyka z URL
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('added') === 'true') {
        showNotification('Produkt dodany do koszyka!', 'success');
        // Aktualizuj licznik po załadowaniu strony
        setTimeout(updateCartCount, 500);
    }

    // Inicjalizacja galerii obrazów
    initializeImageGallery();

    // POPRAWIONE: Obsługa przycisku dodawania do koszyka na stronie szczegółów albumu
    const albumDetailAddToCartBtn = document.querySelector('.album-detail .add-to-cart-btn');
    if (albumDetailAddToCartBtn) {
        albumDetailAddToCartBtn.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();

            const albumId = this.getAttribute('data-album-id');
            const quantityInput = document.querySelector('.album-detail .quantity-input');
            const quantity = quantityInput ? parseInt(quantityInput.value) : 1;

            console.log('Dodawanie do koszyka:', { albumId, quantity });

            if (albumId && quantity > 0) {
                addToCart(albumId, quantity);
            } else {
                showNotification('Proszę wybrać poprawną ilość', 'error');
            }
        });
    }

    // Walidacja ilości przy zmianie na stronie szczegółów
    const albumDetailQuantityInput = document.querySelector('.album-detail .quantity-input');
    if (albumDetailQuantityInput) {
        albumDetailQuantityInput.addEventListener('change', function() {
            const max = parseInt(this.getAttribute('max')) || 10;
            const min = parseInt(this.getAttribute('min')) || 1;
            let value = parseInt(this.value);

            if (isNaN(value) || value < min) {
                this.value = min;
            } else if (value > max) {
                this.value = max;
                showNotification(`Maksymalna ilość to ${max}`, 'warning');
            }
        });
    }
});

// POPRAWIONA Funkcja do dodawania do koszyka z poziomu JS
function addToCart(albumId, quantity = 1) {
    console.log('Wywołanie addToCart:', { albumId, quantity });

    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `albumId=${albumId}&quantity=${quantity}`
    })
        .then(response => {
            if (response.ok) {
                // Odśwież licznik koszyka
                updateCartCount();
                // Pokaż potwierdzenie
                showNotification('Produkt dodany do koszyka!');
            } else {
                showNotification('Wystąpił błąd podczas dodawania do koszyka', 'error');
            }
        })
        .catch(error => {
            console.error('Błąd:', error);
            showNotification('Wystąpił błąd podczas dodawania do koszyka', 'error');
        });
}

// POPRAWIONA Funkcja do aktualizacji licznika koszyka
function updateCartCount() {
    fetch('/cart/count')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const cartCountElements = document.querySelectorAll('.cart-count');
            const count = data.count || 0;

            cartCountElements.forEach(cartCount => {
                cartCount.textContent = count;

                // Pokaż/ukryj licznik w zależności od liczby produktów
                if (count > 0) {
                    cartCount.style.display = 'flex';
                    cartCount.style.visibility = 'visible';
                    cartCount.style.opacity = '1';
                    cartCount.style.background = '#e74c3c';
                } else {
                    cartCount.style.display = 'none'; // Ukryj gdy 0
                }
            });

            console.log('Licznik koszyka zaktualizowany:', count);
        })
        .catch(error => {
            console.error('Błąd podczas aktualizacji licznika koszyka:', error);
            const cartCountElements = document.querySelectorAll('.cart-count');
            cartCountElements.forEach(cartCount => {
                cartCount.textContent = '0';
                cartCount.style.display = 'none';
            });
        });
}

// Upewnij się, że funkcja jest wywoływana przy załadowaniu każdej strony
document.addEventListener('DOMContentLoaded', function() {
    console.log('Strona załadowana - aktualizuję licznik koszyka');
    updateCartCount();

    // Dodatkowo zaktualizuj po 1 sekundzie na wypadek problemów z timingiem
    setTimeout(updateCartCount, 1000);
});

// Funkcja do wyświetlania powiadomień
function showNotification(message, type = 'success') {
    // Sprawdź czy już istnieje powiadomienie i usuń je
    const existingNotification = document.querySelector('.notification');
    if (existingNotification) {
        document.body.removeChild(existingNotification);
    }

    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;

    // Style dla powiadomień
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 15px 20px;
        background: ${type === 'success' ? '#27ae60' : '#e74c3c'};
        color: white;
        border-radius: 5px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        z-index: 1000;
        transform: translateX(100%);
        transition: transform 0.3s ease;
        max-width: 300px;
        word-wrap: break-word;
    `;

    document.body.appendChild(notification);

    // Animacja wejścia
    setTimeout(() => {
        notification.style.transform = 'translateX(0)';
    }, 100);

    // Automatyczne ukrywanie
    setTimeout(() => {
        notification.style.transform = 'translateX(100%)';
        setTimeout(() => {
            if (document.body.contains(notification)) {
                document.body.removeChild(notification);
            }
        }, 300);
    }, 3000);
}

// Funkcja do obsługi błędów obrazków
function handleImageError(img) {
    var title = img.getAttribute('data-title') || 'No Image';
    var encodedTitle = encodeURIComponent(title);
    img.src = 'https://via.placeholder.com/300x300/2c3e50/ffffff?text=' + encodedTitle;
    img.onerror = null; // Zapobiegaj zapętleniu
}

// Obsługa galerii obrazów
function initializeImageGallery() {
    const galleryItems = document.querySelectorAll('.gallery-item');
    if (galleryItems.length === 0) return;

    const modal = document.createElement('div');
    modal.className = 'image-modal';
    modal.style.cssText = `
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0,0,0,0.9);
    `;

    modal.innerHTML = `
        <span class="close-modal" style="position: absolute; top: 20px; right: 35px; color: white; font-size: 40px; font-weight: bold; cursor: pointer; z-index: 1001;">&times;</span>
        <img class="modal-content" id="modal-image" style="margin: auto; display: block; max-width: 90%; max-height: 80%; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%);">
    `;

    document.body.appendChild(modal);

    galleryItems.forEach(item => {
        item.addEventListener('click', function() {
            const img = this.querySelector('img');
            const modalImg = document.getElementById('modal-image');
            modal.style.display = 'block';
            modalImg.src = img.src;
            modalImg.alt = img.alt;
        });
    });

    // Zamknij modal
    const closeBtn = document.querySelector('.close-modal');
    closeBtn.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    // Zamknij modal po kliknięciu poza obraz
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Zamknij modal klawiszem ESC
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            modal.style.display = 'none';
        }
    });
}