# Hangman

## Description  

Hangman is created in Java and requires command-line parameters to obtain the words used in the game. The number of rounds automatically adjusts according to the number of words. In Hangman, you can guess predefined words and demonstrate your knowledge.

## Installation Instructions  

### Requirements:  
- Development environment or CMD command prompt  
- Text document  
- Hangman program code  

First, download the Hangman program code. Then, open your development environment, go to the settings under command-line parameters, and add the path to your text file containing the words for the game.  

Alternatively, you can use the CMD command prompt, but a development environment is recommended.

## Instructions  

### Preparation  
The program shuffles all the words before the game begins, ensuring a different order each time. The letters are then replaced with dashes, and the game starts.

### Gameplay  
The player guesses individual letters, and depending on whether the letter is part of the word or not, the correct letter is added, replacing the corresponding dash. If the guessed letter is not in the word, it is added to the "miss" list, and a part of the hangman figure is drawn.

### End of the Game  
When all rounds are completed, the number of rounds won is displayed. You can play again with new words. The easiest way to do this is by adding new words to the existing text file, but it’s also possible to create a new text file and link it as a new command-line parameter.
