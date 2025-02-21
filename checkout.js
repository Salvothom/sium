// Funzione per recuperare i dati del carrello dal localStorage e mostrarli
function loadCartItems() {
    // Recupera il carrello dal localStorage (presumiamo che i prodotti siano salvati come un array di oggetti)
    const cartItems = JSON.parse(localStorage.getItem("cart")) || [];
    
    // Se ci sono articoli nel carrello
    if (cartItems.length > 0) {
        const cartList = document.getElementById("cart-items");
        
        // Svuota l'elenco esistente
        cartList.innerHTML = "";
        
        // Aggiungi ogni articolo al carrello
        cartItems.forEach(item => {
            // Crea un nuovo elemento di lista per ciascun prodotto
            const listItem = document.createElement("li");
            listItem.classList.add("cart-item");
            listItem.innerHTML = `
                <div class="product-image">
                    <img src="${item.imageUrl}" alt="${item.name}" />
                </div>
                <div class="product-details">
                    <div class="product-name">${item.title}</div>
                    <div class="product-size">Taglia: ${item.size}</div>
                    <div class="product-price">Prezzo unitario: ${item.price} €</div>
                    <div class="product-quantity">Quantità: ${item.quantity}</div>
                    <div class="tot-price">Prezzo totale: ${(item.price * item.quantity).toFixed(2)} €</div>
                </div>
            `;
            
            // Aggiungi l'elemento alla lista del carrello
            cartList.appendChild(listItem);
        });
        
        // Calcola e mostra il totale
        const totalPrice = cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
        document.getElementById("total-price").textContent = `Totale: ${totalPrice.toFixed(2)} €`;
    } else {
        document.getElementById("cart-items").innerHTML = "<li>Il carrello è vuoto</li>";
        document.getElementById("total-price").textContent = "Totale: 0.00 €";
    }
}

// Chiama la funzione per caricare gli articoli nel carrello quando la pagina è caricata
window.onload = loadCartItems;

// Funzione per aggiungere un prodotto al carrello
function addToCart(product) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    
    // Controlla se il prodotto è già nel carrello
    const existingProduct = cart.find(item => item.name === product.name && item.size === product.size);
    if (existingProduct) {
        existingProduct.quantity += product.quantity;
    } else {
        cart.push(product);
    }
    
    // Salva il carrello nel localStorage
    localStorage.setItem("cart", JSON.stringify(cart));
}



