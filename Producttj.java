// Dati dei prodotti
const products = [
    {
        id: 1,
        title: "PIGIAMA ROSA",
        description: "Un dolce pigiama rosa per bambine, realizzato in morbido cotone per garantire comfort e libertà di movimento. Decorato con adorabili stampe e dettagli delicati, è perfetto per notti serene e sogni felici.",
        code: "EG01",
        price: "€20,99",
        imageUrl: "Immagini/PjG2.png",
        size: "" // Qui verrà salvata la taglia selezionata
    },
    {
        id: 2,
        title: "PIGIAMA ARANCIONE",
        description: "Un vivace pigiama arancione per bambine, realizzato in tessuto morbido e traspirante per un comfort ottimale. Allegro e colorato, è perfetto per accompagnare dolci sogni con un tocco di energia e allegria!",
        code: "EG02",
        price: "€20,99",
        imageUrl: "Immagini/PjG1.png",
        size: ""
    },
    {
        id: 3,
        title: "PIGIAMA SPIDER-MAN",
        description: "Un fantastico pigiama di Spider-Man per bambini, perfetto per piccoli supereroi! Realizzato in morbido cotone, offre comfort e libertà di movimento per notti avventurose. Con stampe ispirate all'iconico eroe Marvel, è ideale per sognare incredibili missioni tra i grattacieli!",
        price: "€24,99",
        code: "EB01",
        imageUrl: "Immagini/PjB1.png",
        size: ""
    },
    {
        id: 4,
        title: "PIJIAMA MINIONS",
        description: "Un simpatico pigiama dei Minions per bambini, perfetto per notti piene di allegria! Realizzato in morbido cotone, offre comfort e libertà di movimento. Decorato con le divertenti stampe dei buffi aiutanti gialli, è ideale per accompagnare sogni spensierati e avventure esilaranti!",
        price: "€€24,99",
        code: "EB02",
        imageUrl: "Immagini/PjB2.png",
        size: ""
    },
    {
        id: 5,
        title: "JEANS BAMBINA",
        description: "Comodi e alla moda, questi jeans per bambina sono perfetti per ogni occasione! Realizzati in denim morbido ed elastico, offrono una vestibilità confortevole e libertà di movimento. Ideali per look casual e versatili, da abbinare a magliette colorate o felpe alla moda! ",
        price: "€14,99",
        code: "EG03",
        imageUrl: "Immagini/PaG2.png",
        size: ""
    },
    {
        id: 6,
        title: "T-SHIRT BAMBINA",
        description: "Una dolcissima T-shirt per bambina con la scritta Love, perfetta per aggiungere un tocco di stile e tenerezza al look quotidiano. Realizzata in morbido cotone traspirante, assicura comfort e libertà di movimento. Ideale da abbinare a jeans, gonne o leggings per un outfit trendy e romantico!",
        price: "€12,99",
        code: "EG04",
        imageUrl: "Immagini/TG2.png",
        size: ""
    },
    {
        id: 7,
        title: "JEANS BAMBINO",
        description: "Questi jeans per bambino uniscono stile e comfort, perfetti per ogni avventura quotidiana! Realizzati in denim resistente ed elastico, offrono una vestibilità comoda e libertà di movimento. Ideali per look casual e versatili, da abbinare a magliette e felpe per un outfit sempre alla moda",
        price: "€14,99",
        code: "EB03",
        imageUrl: "Immagini/PaB2.png",
        size: ""
    },
    {
        id: 8,
        title: "POLO BAMBINO",
        description: "Una simpatica polo per bambino con stampa di Tom & Jerry, perfetta per un look casual e divertente! Realizzata in morbido cotone traspirante, assicura comfort e freschezza per tutto il giorno. Il design vivace con i personaggi del celebre cartone rende questa polo ideale per piccoli fan dell’intramontabile duo!",
        price: "€13,99",
        code: "EB04",
        imageUrl: "Immagini/TB2.png",
        size: ""
    }
];

// Funzione per recuperare il prodotto dall'ID nella query string
function getProductById(id) {
    return products.find(product => product.id === parseInt(id));
}

// Ottieni l'ID del prodotto dalla query string della URL
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('id');

// Recupera il prodotto e aggiorna la pagina
const product = getProductById(productId);

if (product) {
    document.getElementById('product-title').textContent = product.title;
    document.getElementById('product-description').textContent = product.description;
    document.getElementById('product-code').textContent =  product.code;
    document.getElementById('product-price').textContent = "Prezzo: " + product.price;
    document.getElementById('product-image').src = product.imageUrl;
} else {
    console.log("Prodotto non trovato!");
}

// **Gestione della selezione della taglia**
document.querySelectorAll('.size-btn').forEach(button => {
    button.addEventListener('click', function () {
        const selectedSize = this.getAttribute('data-size'); // Prendi la taglia dal bottone
        document.getElementById('selected-size').textContent = `Taglia: ${selectedSize}`;
        product.size = selectedSize; // Salva la taglia nel prodotto
        console.log(`Taglia selezionata per ${product.title}: ${product.size}`); // Debug
    });
});

// Aggiungere la gestione per i pulsanti di quantità
const sizeButtons = document.querySelectorAll('.size-btn');
const selectedSizeText = document.getElementById('selected-size');

sizeButtons.forEach(button => {
    button.addEventListener('click', function () {
        const selectedSize = this.getAttribute('data-size'); // Ottiene la taglia dal bottone
        selectedSizeText.textContent = `Taglia: ${selectedSize}`; // Mostra la taglia selezionata

        // Rimuove la classe "selected" da tutti i bottoni
        sizeButtons.forEach(btn => btn.classList.remove('selected'));

        // Aggiunge la classe "selected" solo al bottone cliccato
        this.classList.add('selected');
    });
});
