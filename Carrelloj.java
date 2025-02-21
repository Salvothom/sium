// Apre il carrello
const carrelloBtn = document.getElementById("carrello-btn");
const carrello = document.getElementById("carrello");
const chiudiCarrello = document.getElementById("chiudi-carrello");

// Funzione per aprire il carrello
carrelloBtn.addEventListener("click", () => {
    carrello.classList.add("open");
});

// Funzione per chiudere il carrello
chiudiCarrello.addEventListener("click", () => {
    carrello.classList.remove("open");
});
