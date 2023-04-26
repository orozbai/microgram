'use strict';

const registerForm = document.getElementById('register-post-form');
if (registerForm != null) {
    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        let formData = new FormData(event.target);
        let loginFormData = new FormData();
        loginFormData.append('password', document.getElementById('register-password').value);
        const email = document.getElementById('register-email').value;
        loginFormData.append('email', email);
        localStorage.setItem('email', email);
        alert('registered successfully');
        window.location.href = 'http://localhost:8089/'
        await fetch('http://localhost:8089/register', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });
        await fetch('http://localhost:8089/aut/login', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(loginFormData))
        })
    })
}

addEventListener('DOMContentLoaded', addUserEmailNavbar);

function addUserEmailNavbar() {
    const userEmailNavbar = document.getElementById('navbar-user-email');
    let userEmail = localStorage.getItem('email');
    if (userEmail) {
        userEmailNavbar.innerHTML = userEmail;
    }
}

function deleteLocalStorageEmail() {
    if (localStorageEmail != null) {
        localStorage.removeItem('email');
    }
}

const splashScreen = document.getElementById('splash-screen');
const loginForm = document.getElementById('login-form');
const localStorageEmail = localStorage.getItem('email');
if (localStorageEmail != null) {
    splashScreen.style.display = 'none';
    const postsContainer = document.getElementById('posts-container');
    postsContainer.style.display = 'block';
} else {
    splashScreen.style.display = 'block';
}
if (loginForm != null) {
    loginForm.addEventListener('submit', async (event) => {
        const postsContainer = document.getElementById('posts-container');
        postsContainer.style.display = 'block';
        splashScreen.style.display = 'none';
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const formData = new FormData();
        formData.append('email', email);
        formData.append('password', password);
        localStorage.setItem('email', email);
        const userEmailNavbar = document.getElementById('navbar-user-email');
        let userEmail = localStorage.getItem('email');
        if (userEmail) {
            userEmailNavbar.innerHTML = userEmail;
        }
        await fetch('http://localhost:8089/aut/login', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        }).then(response => response.json())
            .then(data => {
                if (data !== true) {
                    localStorage.removeItem('email');
                }
            })
    })
}