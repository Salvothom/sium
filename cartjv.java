// Funzione per pulire il prezzo ed evitare NaN
function cleanPrice(price) {
    if (!price) return 0;

    // Assicurarsi che price sia una stringa, così possiamo usare il replace
    let clean = String(price).replace(/[^\d,.]/g, '').trim(); // Rimuove tutto tranne numeri, virgole e punti
    clean = clean.replace(',', '.'); // Sostituisce virgole con punti per il formato decimale

    let finalPrice = parseFloat(clean);
    return isNaN(finalPrice) ? 0 : finalPrice; // Se il risultato è NaN, restituisce 0
}

// Carrello vuoto (inizializzazione da localStorage, se disponibile)
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Funzione per aggiungere un prodotto al carrello
function addToCart(product) {
    const existingProduct = cart.find(item => item.title === product.title && item.size === product.size);

    if (existingProduct) {
        existingProduct.quantity += 1;
    } else {
        product.quantity = 1;
        product.price = cleanPrice(product.price); // Assicura che il prezzo sia corretto
        product.code = document.getElementById('product-code') ? document.getElementById('product-code').textContent.trim() : 'Codice non disponibile'; // Aggiungi il codice prodotto
        cart.push(product);
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartSidebar();
    updateCartCounter();
}

// Funzione per aggiornare la visualizzazione del carrello
function updateCartSidebar() {
    const cartItemsList = document.getElementById('cartItems');
    cartItemsList.innerHTML = '';

    cart.forEach(item => {
        const itemPrice = cleanPrice(item.price);
        const totalPrice = (itemPrice * item.quantity).toFixed(2);

        const li = document.createElement('li');
        li.classList.add('cart-item');
        li.innerHTML = `
            <img src="${item.imageUrl}" alt="${item.title}">
            <div>
                <span>${item.code}</span><br>
                <span>${item.quantity > 1 ? item.quantity + 'x ' : ''}${item.title}</span><br>
                <span>Taglia: ${item.size}</span><br>
                <span>€${itemPrice.toFixed(2)}</span>
            </div>
            <div class="quantity-controls">
                <button class="quantity-btn" data-action="decrease" data-title="${item.title}" data-size="${item.size}">-</button>
                <span>${item.quantity}</span>
                <button class="quantity-btn" data-action="increase" data-title="${item.title}" data-size="${item.size}">+</button>
            </div>
            <div><strong>Total: €${totalPrice}</strong></div>
        `;
        cartItemsList.appendChild(li);
    });

    updateCartTotal();
    document.querySelectorAll('.quantity-btn').forEach(button => {
        button.addEventListener('click', handleQuantityChange);
    });
}

// Funzione per calcolare e visualizzare il totale del carrello
function updateCartTotal() {
    const total = cart.reduce((acc, item) => acc + (cleanPrice(item.price) * item.quantity), 0).toFixed(2);
    const totalElement = document.createElement('div');
    totalElement.innerHTML = `<strong>Totale carrello: €${total}</strong>`;
    const cartItemsList = document.getElementById('cartItems');
    cartItemsList.appendChild(totalElement);
}

// Funzione per gestire il clic sui pulsanti + e -
function handleQuantityChange(event) {
    const action = event.target.dataset.action;
    const title = event.target.dataset.title;
    const size = event.target.dataset.size;
    const existingProductIndex = cart.findIndex(item => item.title === title && item.size === size);

    if (existingProductIndex !== -1) {
        if (action === 'increase') {
            cart[existingProductIndex].quantity += 1;
        } else if (action === 'decrease') {
            if (cart[existingProductIndex].quantity > 1) {
                cart[existingProductIndex].quantity -= 1;
            } else {
                cart.splice(existingProductIndex, 1);
            }
        }

        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartSidebar();
        updateCartCounter();
    }
}

// Funzione per aggiornare il contatore dell'icona del carrello
function updateCartCounter() {
    const cartCounter = document.getElementById('cart-counter');
    const totalItems = cart.reduce((total, item) => total + item.quantity, 0);

    if (totalItems > 0) {
        cartCounter.textContent = totalItems;
        cartCounter.style.display = 'block';
    } else {
        cartCounter.style.display = 'none';
    }
}

// Funzione per gestire il click sul bottone "Aggiungi al carrello"
document.getElementById('addToCartBtn')?.addEventListener('click', function() {
    const product = {
        title: document.getElementById('product-title').textContent,
        description: document.getElementById('product-description').textContent,
        price: cleanPrice(document.getElementById('product-price').textContent),
        imageUrl: document.getElementById('product-image').src,
        size: document.querySelector('.size-btn.selected') ? document.querySelector('.size-btn.selected').dataset.size : 'S',
        code: document.getElementById('product-code') ? document.getElementById('product-code').textContent.trim() : 'Codice non disponibile'
    };
    addToCart(product);
});

// Carica il carrello e aggiorna l'interfaccia utente quando la pagina è pronta
document.addEventListener('DOMContentLoaded', function () {
    cart = JSON.parse(localStorage.getItem('cart')) || [];
    updateCartSidebar();
    updateCartCounter();
});
