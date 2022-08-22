# rhythm-game

## Overview

- Made by: ChunHao
- Last Update: 8/21
- Language: Java
- Editor: Eclipse

## Description

there are three different screens:
1. **HOME**: the main screen
2. **PLAYING**: play the game!!
3. **SETTLE**: you're score

![](https://i.imgur.com/owYyw9i.png)


you can set the game on the menubar, there are two setting:
1. **Volume**: change the volume of music and soundeffect
2. **Control**: change your keyboard control

- maybe you can add a menu by yourself?!!

## Download Notice
- please add your music **by yourself**
- the music I use(recommend):
    -  [You and me(music2)](https://www.youtube.com/watch?v=WtRHih2nZxk)
    -  [Summertime(music2)](https://www.youtube.com/watch?v=nIoQMVTxyd4)
1. download your own two music files and change them to **.wav** files
2. add the files to **/res/audio** and set the filename to **music1(for background)** and **music2(for game)**
3. you can make your own beat as well(with class **GetBeat**)
4. you can change the scale of the game by changing the parameter **SCALE** in class **Game**

## How to get beats

- type **ENTER** to get the beat in nanotime
- type **SPACE** to get the result array
- there will be a little error in the first beat, try get the first beat with function **getFirstBeat** in class **RhyGameThread**. To trigger this function, you can type **ENTER** in playing state

## Judgement

there are four level for accuracy:
- **Bad**: 0 point
- **Good**: 4 point
- **Great**: 7 point
- **Perfect**: 10 point

there are four level for your result score:
- **S**: score >= 3500
- **A**: score >= 2500
- **B**: score >= 1500
- **C**: score < 1500


## Reference
- the reference of pic is in the project
- the project I refer to:
> [KaarinGaming/PlatformerTutorial](https://github.com/KaarinGaming/PlatformerTutorial)

> [How To Make Java Rhythm Game](https://blog.naver.com/ndb796/220997028385)
