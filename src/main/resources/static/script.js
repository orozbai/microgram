'use strict';

function showLoginScreen() {
    const loginScreen = document.getElementById('login-screen');
    loginScreen.style.display = 'block';
}

function hideLoginScreen() {
    const loginScreen = document.getElementById('login-screen');
    loginScreen.style.display = 'none';
}

function commentExampleConstructor(text, timestamp, userEmail, userLogin) {
    this.text = text;
    this.timestamp = timestamp;
    this.userEmail = userEmail;
    this.userLogin = userLogin;
}

const commentExample = new commentExampleConstructor(
    'Text comment', // для проверки создания комментария
    Date.now(),
    'test@test.com',
    'testUser'
);

function createCommentElement(comment) {
    const commentElement = document.createElement('div');
    commentElement.classList.add('comment');

    const headerElement = document.createElement('div');
    headerElement.classList.add('comment-header');

    const authorElement = document.createElement('div');
    authorElement.classList.add('comment-author');

    const authorNameElement = document.createElement('span');
    authorNameElement.classList.add('comment-author-name');
    authorNameElement.textContent = comment.userLogin;

    const authorEmailElement = document.createElement('span');
    authorEmailElement.classList.add('comment-author-email');
    authorEmailElement.textContent = comment.userEmail;

    authorElement.appendChild(authorNameElement);
    authorElement.appendChild(authorEmailElement);

    const timestampElement = document.createElement('div');
    timestampElement.classList.add('comment-timestamp');
    const timestamp = new Date(comment.timestamp);
    timestampElement.textContent = `Posted on ${timestamp.toLocaleString()}`;

    const bodyElement = document.createElement('div');
    bodyElement.classList.add('comment-body');
    bodyElement.textContent = comment.text;

    headerElement.appendChild(authorElement);
    headerElement.appendChild(timestampElement);

    commentElement.appendChild(headerElement);
    commentElement.appendChild(bodyElement);

    return commentElement;
}

function postExampleConstructor(imagePath, description, timestamp, user) {
    this.imagePath = imagePath;
    this.description = description;
    this.timestamp = timestamp;
    this.user = user;
}

const post = new postExampleConstructor(
    'https://i.pinimg.com/564x/7b/e8/0e/7be80e1dd312352fb3616ff285f18037.jpg', // для проверки поста
    'description',
    Date.now(),
    'user@example.com'
);

function createPostElement(post) {
    const postElement = document.createElement('div');
    postElement.classList.add('post');

    const imageElement = document.createElement('img');
    imageElement.src = post.imagePath;
    imageElement.alt = 'post image';
    postElement.appendChild(imageElement);

    const postInfoElement = document.createElement('div');
    postInfoElement.classList.add('post-info');

    const descriptionElement = document.createElement('p');
    descriptionElement.classList.add('post-description');

    const timeElement = document.createElement('p');
    timeElement.classList.add('post-time');
    const date = new Date(post.timestamp);
    timeElement.textContent = 'posted on ' + date.toLocaleDateString() + ' at ' + date.toLocaleTimeString();
    postInfoElement.appendChild(timeElement);

    const userElement = document.createElement('p');
    userElement.classList.add('post-user');
    userElement.textContent = 'posted by ' + post.user;
    postInfoElement.appendChild(userElement);

    postElement.appendChild(postInfoElement);

    return postElement;
}

function insertPostElement(postElement) {
    const containerElement = document.querySelector('.posts-container');
    containerElement.appendChild(postElement);
}