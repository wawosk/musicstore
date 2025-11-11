// Funkcje pomocnicze dla koszyka
document.addEventListener('DOMContentLoaded', function() {
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
});

// Funkcja do dodawania do koszyka z poziomu JS
function addToCart(albumId, quantity = 1) {
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
            }
        })
        .catch(error => {
            console.error('Błąd:', error);
            showNotification('Wystąpił błąd podczas dodawania do koszyka', 'error');
        });
}

// Funkcja do aktualizacji licznika koszyka
function updateCartCount() {
    fetch('/cart/count')
        .then(response => response.json())
        .then(data => {
            const cartCount = document.querySelector('.cart-count');
            if (cartCount) {
                cartCount.textContent = data.count;
            }
        });
}

// Funkcja do wyświetlania powiadomień
function showNotification(message, type = 'success') {
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
            document.body.removeChild(notification);
        }, 300);
    }, 3000);
}



document.addEventListener('DOMContentLoaded', function() {
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

    // Szybkie dodawanie do koszyka z poziomu listy albumów
    const addToCartButtons = document.querySelectorAll('.album-actions .btn-primary');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const form = this.closest('form');
            const albumId = form.querySelector('input[name="albumId"]').value;

            // Tutaj możesz dodać AJAX zamiast standardowego submit
            form.submit();
        });
    });

    // Animacje dla kart albumów
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
    const animatedElements = document.querySelectorAll('.album-card, .feature, .album-detail');
    animatedElements.forEach(el => {
        el.style.opacity = 0;
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(el);
    });

    // Powiadomienie o dodaniu do koszyka
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('added') === 'true') {
        showNotification('Produkt dodany do koszyka!', 'success');
    }
});

// Funkcja do wyświetlania powiadomień
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;

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

document.addEventListener('DOMContentLoaded', function() {
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

    // Szybkie dodawanie do koszyka z poziomu listy albumów
    const addToCartButtons = document.querySelectorAll('.album-actions .btn-primary');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const form = this.closest('form');
            const albumId = form.querySelector('input[name="albumId"]').value;

            // Tutaj możesz dodać AJAX zamiast standardowego submit
            form.submit();
        });
    });

    // Animacje dla kart albumów
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
    const animatedElements = document.querySelectorAll('.album-card, .feature, .album-detail');
    animatedElements.forEach(el => {
        el.style.opacity = 0;
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
        observer.observe(el);
    });

    // Powiadomienie o dodaniu do koszyka
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('added') === 'true') {
        showNotification('Produkt dodany do koszyka!', 'success');
    }
});

// Funkcja do wyświetlania powiadomień
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;

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