// JavaScript code for Guess the Number Game
// Written by ChatGPT
// Modified by Ylldrit Miftari
// Student Number: 32004724

// Generate a random number between min and max (inclusive)
function getRandomNumber(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Initialize variables
const minNumber = 1;
const maxNumber = 100;
let secretNumber = getRandomNumber(minNumber, maxNumber);
let attempts = 0;

// Function to check the guessed number
function checkGuess() {
    const guess = parseInt(document.getElementById('guessInput').value);

    if (isNaN(guess) || guess < minNumber || guess > maxNumber) {
        setMessage('Warning: Please enter a valid number between 1 and 100.', 'red');
        return;
    }

    attempts++;

    if (guess === secretNumber) {
        setMessage(`Congratulations! You guessed the number ${secretNumber} in ${attempts} attempts.`, 'green');
        disableInput();
    } else if (guess < secretNumber) {
        setMessage('Too low! Try a higher number.', 'orange');
    } else {
        setMessage('Too high! Try a lower number.', 'orange');
    }

    document.getElementById('guessInput').value = '';
}

// Function to set message and style
function setMessage(message, color) {
    const messageElement = document.getElementById('message');
    messageElement.textContent = message;
    messageElement.style.color = color;
}

// Function to disable input after winning
function disableInput() {
    document.getElementById('guessInput').disabled = true;
}
