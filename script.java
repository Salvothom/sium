function ToggleMenu() {
    const sideMenu = document.getElementById("sideMenu", "sideMenu2");
    sideMenu.classList.toggle("open");  // Aggiunge o rimuove la classe "open"
}

document.addEventListener('click', function(event) {
    const sideMenu = document.getElementById("sideMenu", "sideMenu2");
    const button = document.querySelector('.button', "button2");
    
    // Verifica se il clic è stato fatto fuori dal menu e dal bottone
    if (!sideMenu.contains(event.target) && !button.contains(event.target)) {
        sideMenu.classList.remove("open"); // Chiude il menu
    }
});

