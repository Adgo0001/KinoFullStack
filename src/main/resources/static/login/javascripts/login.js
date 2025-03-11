// Login formular validering (server-side check)
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Forhindre siden i at genindlæse

    // Henter værdier fra inputfelterne
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let errorMessage = document.getElementById("error-message");

    // Klient-side validering (f.eks. tjek om felterne er tomme)
    if (!username || !password) {
        errorMessage.textContent = "Email og password er påkrævet!";
        errorMessage.style.display = "block";
    } else {
        // Hvis alt ser godt ud, send formularen (server vil håndtere den rigtige validering)
        this.submit();
    }
});

// Signup formular validering
document.getElementById('signup-form').onsubmit = function(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    let errorMessage = document.getElementById('error-message');

    // Tjek om password og confirm password er ens
    if (password !== confirmPassword) {
        event.preventDefault();  // Forhindrer formularen i at blive sendt
        errorMessage.innerText = "Passwords do not match!";
        errorMessage.style.display = 'block';  // Viser fejlbeskeden
    } else {
        errorMessage.style.display = 'none';  // Skjuler fejlbeskeden, hvis passwords matcher
    }
};

// Server-side error message visning
// Hent fejlmeddelelse fra serverens URL-querystring (hvis tilgængelig)
const urlParams = new URLSearchParams(window.location.search);
const errorMessageFromServer = urlParams.get('error');

if (errorMessageFromServer) {
    let errorMessageElement = document.getElementById('error-message');
    errorMessageElement.innerText = errorMessageFromServer;
    errorMessageElement.style.display = 'block';
}

