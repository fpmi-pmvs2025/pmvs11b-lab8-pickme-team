---
layout: default
title: Home
---

# 2048 Game - Project Documentation
## About

The **2048 Game** app allows users to enjoy the popular sliding puzzle game on their Android devices. With intuitive controls and a sleek interface, players can immerse themselves in the challenge of reaching the elusive 2048 tile.

## Main Features

- **Game Interface**: 
  - A 4x4 grid where players slide tiles to combine numbers and reach the 2048 tile.
  - Intuitive swipe controls and on-screen directional buttons for easy gameplay.

- **Score Tracking**:
  - Real-time score updates as tiles merge.
  - Bonus and penalty points based on random numbers fetched from an external API.

- **Records System**:
  - Stores the top 10 high scores locally using SQLite.
  - Displays a leaderboard with scores and achievement dates.

- **API Integration**:
  - Fetches a random number from an external API at the start of each game.
  - Adjusts the player's score based on the fetched number.

- **Performance and Reliability**:
  - Asynchronous data loading and API requests to ensure a smooth user experience.
  - Error handling and crash recovery to maintain data integrity and user progress.

- **User Experience**:
  - A user-friendly interface with clear navigation and responsive controls.
  - Localization support for multiple languages.

## User Intefrace

<img src="https://github.com/fpmi-pmvs2025/pmvs11b-lab8-pickme-team/blob/main/docs/pics/start.jpg" alt="start" width="20%" />
<img src="https://github.com/fpmi-pmvs2025/pmvs11b-lab8-pickme-team/blob/main/docs/pics/game2.jpg" alt="game example" width="20%" />
<img src="https://github.com/fpmi-pmvs2025/pmvs11b-lab8-pickme-team/blob/main/docs/pics/game1.jpg" alt="game example 2" width="20%" />
<img src="https://github.com/fpmi-pmvs2025/pmvs11b-lab8-pickme-team/blob/main/docs/pics/records.jpg" alt="records table" width="20%" />

## Contributors
* [Demid Moshkovich](https://github.com/IronGunYT)
* [Yulia Kanoplich](https://github.com/Juliet165)
* [Victoria Samsonova](https://github.com/victoriaSamsonovaaa)
