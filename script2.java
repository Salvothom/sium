document.addEventListener("DOMContentLoaded", function () {
    const sideMenu = document.getElementById("sideMenu2");
    const toggleButton = document.querySelector(".button2");

    // Debug: Controlliamo se gli elementi esistono
    if (!sideMenu) {
        console.error("Errore: Elemento #sideMenu2 non trovato!");
        return;
    }
    if (!toggleButton) {
        console.error("Errore: Elemento .button2 non trovato!");
        return;
    }

    // Funzione per aprire e chiudere la sidebar
    function toggleMenu() {
        console.log("Toggle Sidebar"); // Debug: Verifica che la funzione venga chiamata
        sideMenu.classList.toggle("open");
    }

    // Apertura della sidebar al click del bottone
    toggleButton.addEventListener("click", function (event) {
        event.stopPropagation(); // Previene la chiusura immediata
        toggleMenu();
    });

    // Chiusura della sidebar se si clicca fuori
    document.addEventListener("click", function (event) {
        if (!sideMenu.contains(event.target) && !toggleButton.contains(event.target)) {
            console.log("Chiudo Sidebar"); // Debug: Controlliamo se la chiusura avviene correttamente
            sideMenu.classList.remove("open");
        }
    });

    // Evita la chiusura quando si premono i pulsanti + e -
    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("quantity-btn")) {
            console.log("Clic su + o -"); // Debug: Controlliamo se viene riconosciuto il click
            sideMenu.classList.add("open");
        }
    });
});
