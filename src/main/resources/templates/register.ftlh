<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <link rel="stylesheet" href="../register.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/logout">logout <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

<form id="register-post-form" enctype="multipart/form-data">
    <label for="register-accountName"></label>
    <input type="text" id="register-accountName" name="accountName" required placeholder="account Name">
    <br>
    <label for="register-name"></label>
    <input type="text" id="register-name" name="name" required placeholder="name">
    <br>
    <label for="register-email"></label>
    <input type="email" id="register-email" name="email" required placeholder="Email">
    <div id="emailError" class="error"></div>
    <br>
    <label for="register-password"></label>
    <input type="password" id="register-password" name="password" required placeholder="Password">
    <br>
    <label for="repeatPassword"></label>
    <input type="password" id="repeatPassword" name="repeatPassword" required placeholder="Repeat password">
    <div id="passwordError" class="error"></div>
    <br>
    <input type="submit" value="register">
</form>
<script src="../authentication.js"></script>
<script>

    const emailInput = document.getElementById("register-email");
    const passwordInput = document.getElementById("register-password");
    const repeatPasswordInput = document.getElementById("repeatPassword");

    const emailError = document.getElementById("emailError");
    const passwordError = document.getElementById("passwordError");

    function validateEmail() {
        const email = emailInput.value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            emailError.innerHTML = "Enter correct email";
            emailInput.classList.add("error");
            return false;
        } else {
            emailError.innerHTML = "";
            emailInput.classList.remove("error");
            return true;
        }
    }

    function validatePassword() {
        const password = passwordInput.value;
        const repeatPassword = repeatPasswordInput.value;
        if (password !== repeatPassword) {
            passwordError.innerHTML = "Password mismatch";
            passwordInput.classList.add("error");
            repeatPasswordInput.classList.add("error");
            return false;
        } else {
            passwordError.innerHTML = "";
            passwordInput.classList.remove("error");
            repeatPasswordInput.classList.remove("error");
            return true;
        }
    }

    emailInput.addEventListener("input", validateEmail);
    passwordInput.addEventListener("input", validatePassword);
    repeatPasswordInput.addEventListener("input", validatePassword);

</script>
</body>
</html>