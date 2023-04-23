'use strict';

document.getElementById('register-post-form').onsubmit = async (event) => {
    event.preventDefault();
    let formData = new FormData(event.target);
    await fetch('http://localhost:8089/register', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(Object.fromEntries(formData))
    });
    alert('registered successfully');
}