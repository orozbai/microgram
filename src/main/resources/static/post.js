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

function createPostElement() {
    const postElement = document.createElement('div');
    postElement.classList.add('post');

    const imgElement = document.createElement('img');
    imgElement.classList.add('post-image');
    imgElement.id = 'post-image';
    imgElement.src = 'https://i.pinimg.com/564x/7b/e8/0e/7be80e1dd312352fb3616ff285f18037.jpg';
    imgElement.alt = 'post image';
    postElement.appendChild(imgElement);

    const heartContainerElement = document.createElement('div');
    heartContainerElement.id = 'heart-container';
    postElement.appendChild(heartContainerElement);

    const heartElement = document.createElement('div');
    heartElement.id = 'heart';
    heartContainerElement.appendChild(heartElement);

    const postLikesElement = document.createElement('div');
    postLikesElement.classList.add('post-likes');
    const svgElement = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    svgElement.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
    svgElement.setAttribute('width', '30');
    svgElement.setAttribute('height', '30');
    svgElement.setAttribute('fill', 'currentColor');
    svgElement.classList.add('bi', 'bi-heart-fill');
    svgElement.setAttribute('viewBox', '0 0 16 16');
    const pathElement = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    pathElement.setAttribute('fill-rule', 'evenodd');
    pathElement.setAttribute('d', 'M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z');
    svgElement.appendChild(pathElement);
    postLikesElement.appendChild(svgElement);
    postElement.appendChild(postLikesElement);

    const postCommentsElement = document.createElement('div');
    postCommentsElement.classList.add('post-comments');
    const commentsHeadingElement = document.createElement('h2');
    commentsHeadingElement.textContent = 'Комментарии';
    postCommentsElement.appendChild(commentsHeadingElement);
    postElement.appendChild(postCommentsElement);

    const addToFavoritesElement = document.createElement('div');
    addToFavoritesElement.classList.add('add-to-favorites');
    addToFavoritesElement.id = 'add-to-favorites';
    const favoriteSvgElement = document.createElementNS('http://www.w3.org//2000/svg', 'svg');
    favoriteSvgElement.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
    favoriteSvgElement.classList.add('bi', 'bi-bookmark-fill');
    favoriteSvgElement.setAttribute('viewBox', '0 0 16 16');
    const favoritePathElement = document.createElementNS('http://www.w3.org/2000/svg', 'path');
    favoritePathElement.setAttribute('d', 'M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z');
    favoriteSvgElement.appendChild(favoritePathElement);
    addToFavoritesElement.appendChild(favoriteSvgElement);
    postElement.appendChild(addToFavoritesElement);

    return postElement;
}

const postElement = createPostElement();

function insertPostElement(postElement) {
    const containerElement = document.getElementById('posts-container');
    containerElement.appendChild(postElement);
}