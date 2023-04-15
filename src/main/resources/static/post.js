'use strict'

document.querySelector('.bi-heart-fill').addEventListener('click', function () {
    const postIconLike = document.querySelector('.bi-heart-fill');
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }
})

document.querySelector(".post-image").addEventListener("dblclick", function (event) {
    const postIconLike = document.querySelector('.bi-heart-fill');
    const currentColor = postIconLike.style.color
    if (currentColor === 'red' || currentColor === 'rgb(255, 0,0)') {
        postIconLike.style.color = 'grey';
    } else {
        postIconLike.style.color = 'red';
    }

    const heart = document.querySelector('.heart');
    heart.style.top = (event.clientY - heart.offsetHeight / 2) + "px";
    heart.style.left = (event.clientX - heart.offsetWidth / 2) + "px";
    heart.style.opacity = "1";
    setTimeout(function () {
        heart.style.opacity = "0";
    }, 1000);
});

document.querySelector('.bi-bookmark-fill').addEventListener('click', function () {
    let postIconFavorite = document.querySelector('.bi-bookmark-fill');
    const currentColor = postIconFavorite.style.color;
    if (currentColor === 'grey') {
        postIconFavorite.style.color = 'orange';
    } else {
        postIconFavorite.style.color = 'grey';
    }
});

function createPostElement(formData) {
    const reader = new FileReader();
    reader.onload = function (e) {

        const postElement = document.createElement('div');
        postElement.classList.add('post');

        const imgElement = document.createElement('img');
        imgElement.classList.add('post-image');
        imgElement.src = e.target.result;
        imgElement.alt = 'post image';
        postElement.appendChild(imgElement);

        const heartContainerElement = document.createElement('div');
        heartContainerElement.classList.add('heart-container');
        postElement.appendChild(heartContainerElement);

        const heartElement = document.createElement('div');
        heartElement.classList.add('heart');
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
        commentsHeadingElement.textContent = 'Описание';
        const descriptionElement = document.createElement('p');
        descriptionElement.textContent = formData.get('description');
        postCommentsElement.appendChild(commentsHeadingElement);
        postElement.appendChild(postCommentsElement);
        postElement.appendChild(descriptionElement);

        const divElement = document.createElement("div");
        divElement.className = "add-to-favorites";
        const addToFavoritesSvgElement = document.createElementNS("http://www.w3.org/2000/svg", "svg");
        addToFavoritesSvgElement.classList.add('bi', 'bi-bookmark-fill');
        addToFavoritesSvgElement.setAttribute("viewBox", "0 0 16 16");
        const favoritePathElement = document.createElementNS("http://www.w3.org/2000/svg", "path");
        favoritePathElement.setAttribute("d", "M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z");
        addToFavoritesSvgElement.appendChild(favoritePathElement);
        divElement.appendChild(addToFavoritesSvgElement);
        postElement.appendChild(divElement);

        insertPostElement(postElement);
    }
    reader.readAsDataURL(formData.get('imageLink'))
}

function insertPostElement(postElement) {
    const containerElement = document.getElementById('posts-container');
    containerElement.appendChild(postElement);
}

document.getElementById('post-form').onsubmit = async (e) => {
    let imageInput = document.getElementById('image-input');
    let descriptionInput = document.getElementById('description-input');
    let userIdInput = document.getElementById('user-id-input');

    let formData = new FormData();
    formData.append('imageLink', imageInput.files[0]);
    formData.append('description', descriptionInput.value);
    formData.append('user_id', userIdInput.value);

    e.preventDefault();
    let response = await fetch('/publications/add', {
        method: 'POST',
        body: formData
    });
    createPostElement(formData)
    let result = await response.json();
    alert(result.message);
}