<p align="center">
 <img src="https://i.ibb.co/HzDV6YD/Screenshot-4.png" alt="Game Logo" width="400" height="257">
</p>

<h3 align="center">Mobile Suit Gundam: Epyon's Counter Attack</h3>

<div align="center">
 
 [![Eclipse](https://badgen.net/badge/icon/eclipse?icon=eclipse&label)](https://https://eclipse.org/)
 [![made-with-java](https://img.shields.io/badge/Made%20with-Java-e76f00.svg)](https://www.java.com)
 [![javafx-version](https://img.shields.io/badge/JavaFX-v.19-1f425f.svg)](https://openjfx.io/)
 [![Status](https://img.shields.io/badge/status-active-success.svg)](https://github.com/SupanutT/Programming-Methodology-Project)

</div>

## Project Overview
This project is a part of the **2110215 Programming Methodology I** curriculum of the Chulalongkorn University Department of Computer Engineering. These are the subjects that we studied and implemented into this project.

* Basic Java
* Exception and JUnit
* Inheritance
* Abstract class
* Interface and Polymorphism
* JavaFX GUI
* Event Handling
* Thread

## Game Instructions
### Introduction
Mobile Suit Gundam: Epyon's Counter Attack is based on the well-known internet game **"Box Head"**, which is a 2D shooting game.
In this game, you will pilot the *Strike Gundam* against the army of *Zaku* and *Epyon Gundam*. The goal is to **eliminate** all enemies and save the planet.

### Rule
You will begin with 100 health points and one head gun. To pass and receive points as you kill enemies, you must clear all of them in each wave. There are three types of enemies: **Zaku, Red Zaku, and Epyon Gundam**, each with their own set of *abilities* and *points* that the player can earn.
Higher rounds **increase** your chances of receiving a new gun from a loot box dropped in the game. You must keep an eye out for the enemy as you progress through the rounds, as there will be a lot of them! If your health reaches zero, the game and the world will end.

### Objects in the game
* **Player**

| Type | Graphic | Health | Speed |
| :--: | :-----: | :----: | :---: |
| **Strike Gundam** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/gundam/strike.PNG" width="70" height="70"> | 100 | 2 |

* **Enermy**

| Type | Graphic | Health | Speed | Melee Damage | Range Damage | Score | Heal point | Movement Animation |
| :--: | :-----: | :----: | :---: | :----------: | :----------: | :---: | :--------: | :----------------: |
| **Zaku** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/zaku/zaku1_downright.png" width="90" height="60"> | 100 | 0.7 | 0.4 | - | 25 | 5 | <img src="https://media.giphy.com/media/KrYsIgyiCG5v5M5VWZ/giphy.gif"> |
| **Fast Zaku** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/zaku/zaku2_downright.png" width="90" height="60"> | 100 | 1.2 | 0.33 | - | 25 | 5 | <img src="https://media.giphy.com/media/mK56MlhW7YPiVp8FtN/giphy.gif"> |
| **Epyon Gundam** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/gundam/epyon_downright.png" width="90" height="60"> | 200 | 0.7 | 1 | 20 | 100 | 10 | <img src="https://media.giphy.com/media/fBXut3dfDND8VelYl9/giphy.gif"> |

* **Gun**

| Type | Graphic | Ammo Speed | Damage | Cooldown | Recoil angle | Recoil Distance | Shooting Style | Shooting Animation |
| :--: | :-----: | :--------: | :----: | :------: | :----------: | :-------------: | :------------: | :----------------: |
| **Headgun** | - | 15 | 20 | 20 | 2 | 1 | Fully-Automatic | <img src="https://media.giphy.com/media/B8fowdBSjNQZCweWeC/giphy.gif"> |
| **Shotgun** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/weapon/shotgun_right.png" width="50" height="25"> | 30 | 40 | 40 | 0 | 10 | Semi-Automatic with 3 bullets | <img src="https://media.giphy.com/media/vGV2y1dQSa6NznLGbe/giphy.gif"> |
| **Uzi** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/weapon/uzi_right.png" width="45" height="25"> | 50 | 10 | 5 | 1.5 | 2 | Fully-Automatic | <img src="https://media.giphy.com/media/4SdcQQsoB0ngkS84gm/giphy.gif"> |
| **Sniper** | <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/weapon/buster_rifle_right.png" width="75" height="20"> | 70 | 100 | 120 | 0 | 10 | Bolt-Action | <img src="https://media.giphy.com/media/euEXs3iBdula6i3N8D/giphy.gif"> |

* **Loot Box**
  * <img src="https://github.com/SupanutT/Programming-Methodology-Project/blob/main/res/lootbox/haro.PNG" width="50" height="50">    Loot Box

* **Wall**
  * <img src="https://i.ibb.co/g91TF9C/Untitled-3.png" width="50" height="50">    Wall

### Instruction in game

| Key | Action |
| :-: | :----: |
| W/Arrow Up | move up |
| A/Arrow Left | move left |
| S/Arrow Down | move down |
| D/Arrow Right | move right |
| P | pause/resume |
| Num1 | change weapon to *Headgun* |
| Num1 | change weapon to *Shotgun* |
| Num1 | change weapon to *Uzi* |
| Num1 | change weapon to *Sniper* |
| spacebar | shoot |

### Gameplay
This game's main menu will be displayed when the game begins.
<p align="center">
 <img src="https://i.ibb.co/qBVynkT/ingame1.png" alt="Main menu" width="517" height="409">
</p>

If you click on the **"INSTRUCTIONS"** text, you will be taken to the *Instructions page*, which contains all of the button keys needed to play this game.
<p align="center">
 <img src="https://i.ibb.co/56FqYRD/ingame2.png" alt="Main menu" width="517" height="409">
</p>

Back at the main menu, clicking the **"PLAY"** text will take you to the *in-game screen*, where you will see yourself as **Strike Gundam**. The enemy will approach you, so you must kill them before they attack you!
<p align="center">
 <img src="https://i.ibb.co/FXFQctR/ingame3.png" alt="Main menu" width="517" height="409">
</p>

As the developers of this game, we understand how difficult and intense it is. If you need to **pause** the game, simply press the **"P"** button on your keyboard at any time. If you're ready to return and save the world, press the **"P"** button once more.
<p align="center">
 <img src="https://i.ibb.co/0FfzhMD/ingame4.png" alt="Main menu" width="517" height="409">
</p>

The game ends when the player's health reaches *zero*. The highest wave and score will be displayed on the game over page. You can either **continue playing** or **exit the game**.
<p align="center">
 <img src="https://i.ibb.co/wL6FqCX/ingame5.png" alt="Main menu" width="517" height="409">
</p>





## About

### Developer

* <img src="https://avatars.githubusercontent.com/u/107112702" width="20" height="20">[ SupanutT](https://github.com/SupanutT)
* <img src="https://avatars.githubusercontent.com/u/110981996" width="20" height="20">[ pinkyPingy](https://github.com/pinkyPingy)

## Contributing
//TODO
