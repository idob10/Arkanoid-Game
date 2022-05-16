# Arkanoid
Arkanoid is an arcade game developed by Taito in 1986. The player controls the 'Vaus', a space vessel that acts as the game's 'paddle' which prevents a ball from falling from the playing field, attempting to bounce it against a number of bricks. The ball striking a brick causes the brick to disappear. When all the bricks are gone, the player goes to the next level, where another pattern of bricks appears.
During the course "Object-Oriented Programming", we were asked to create our own version of the Arkanoid game.

## Prerequisites
This project requires Java Development Kit, biuoop graphics library (already provided) and [Apache Ant](https://github.com/ariecattan/biuoop2021/wiki/Installing-Ant).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Installation

**BEFORE YOU INSTALL :** please read the [prerequisites](#prerequisites)

Start with cloning this repo on your local machine:

```sh
$ git clone https://github.com/idob10/Arkanoid-Game.git
```

## Usage

### Serving the app
The build.xml file has already been provided; in order to serve the app, run the following commands:
- Run the compile command to compile the code:
```sh
$ ant compile
```
You can play which levels you want and in what order you want in this game. For example, you can play level 3 first, then level 2, and finally level 1. To do so, use run command:
```sh
$ ant run -Dargs="3 2 1"
```
## General Instructions
1. In order to stop the game, press 'p' button, and space to continue.
2. When lose/win screen appears, press space button to exit the game.
3. In order to move the paddle, use the arrow keys.

## Sample Pictures
![2nd level](https://user-images.githubusercontent.com/84286628/168551582-5a5bbcc7-2021-48e1-b77f-d9ea33884591.png)
![3rd level](https://user-images.githubusercontent.com/84286628/168551697-23cca446-72f1-4135-838e-12d8a77d21f3.png)
![4th level](https://user-images.githubusercontent.com/84286628/168551784-400b6f70-e2be-4c68-8173-49cc6a0243b8.png)

## Author

* **Ido Barkai** - [Ido Barkai](https://github.com/idob10)