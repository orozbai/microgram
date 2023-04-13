'use strict'

function likeUnlike() {
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }
}

let postIconLike;
postIconLike = document.getElementById('post-like-icon');

postIconLike.addEventListener('click', likeUnlike)

document.getElementById("post-image").addEventListener("dblclick", function (event) {
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }

    const heart = document.getElementById("heart");
    heart.style.top = (event.clientY - heart.offsetHeight / 2) + "px";
    heart.style.left = (event.clientX - heart.offsetWidth / 2) + "px";
    heart.style.opacity = "1";
    setTimeout(function () {
        heart.style.opacity = "0";
    }, 1000);
});

let postIconFavorite;
postIconFavorite = document.getElementById('post-favorite-icon');

postIconFavorite.addEventListener('click', favoriteAdd);

function favoriteAdd() {
    const currentColor = postIconFavorite.style.color;
    if (currentColor === 'grey') {
        postIconFavorite.style.color = 'orange';
    } else {
        postIconFavorite.style.color = 'grey';
    }
}

document.getElementById('login-button').addEventListener('click', function () {
    document.getElementById('splash-screen').style.display = 'none';

    document.getElementById('main-content').style.display = 'flex';
});