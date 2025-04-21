---
layout: default
title: DB and Files Scheme
---

# Files Structure

```
project/
├── Game2048/                      # Main project directory
│   ├── app/                       # Application module
│   │   ├── src/                   # Source code directory
│   │   │   ├── main/              # Main source code
│   │   │   │   ├── java/          # Java source files
│   │   │   │   │   └── com/
│   │   │   │   │       └── example/
│   │   │   │   │           └── game2048/  # Game package
│   │   │   │   │               ├── SwipeGestureDetector.java    # Handles swipe gestures
│   │   │   │   │               ├── RecordsActivity.java         # High scores screen
│   │   │   │   │               ├── RandomNumberTask.java        # Random number generation
│   │   │   │   │               ├── GameManager.java             # Core game logic
│   │   │   │   │               ├── MainActivity.java            # Main application screen
│   │   │   │   │               ├── GameActivity.java            # Game screen implementation
│   │   │   │   │               └── DBHelper.java                # Database operations
│   │   │   │   ├── res/          # Resources directory
│   │   │   │   └── AndroidManifest.xml  # Application manifest
│   │   │   ├── test/             # Unit tests
│   │   │   └── androidTest/      # Android instrumentation tests
│   │   ├── build.gradle.kts      # App module build configuration
│   │   └── proguard-rules.pro    # ProGuard rules for code obfuscation
│   ├── gradle/                    # Gradle wrapper files
│   ├── build.gradle.kts          # Project build configuration
│   ├── settings.gradle.kts       # Project settings
│   ├── gradle.properties         # Gradle properties
│   ├── gradlew                   # Gradle wrapper script (Unix)
│   └── gradlew.bat               # Gradle wrapper script (Windows)
├── .github/                      # GitHub configuration
├── .git/                         # Git repository
├── README.md                     # Project documentation
└── .gitignore                    # Git ignore rules
```

## Key Components

### Core Game Files
- **GameActivity.java**: Main game screen implementation, handles UI and user interactions
- **GameManager.java**: Contains core game logic, including tile movement and merging
- **SwipeGestureDetector.java**: Processes swipe gestures for tile movement
- **RandomNumberTask.java**: Handles random number generation for new tiles

### Data Management
- **DBHelper.java**: Manages database operations for storing high scores and game state
- **RecordsActivity.java**: Displays and manages high scores

### Configuration Files
- **build.gradle.kts**: Contains project dependencies and build configurations
- **AndroidManifest.xml**: Defines application components and permissions
- **proguard-rules.pro**: Contains rules for code optimization and obfuscation

## Build System
The project uses Gradle as its build system with Kotlin DSL (`.kts` files). The build configuration is split between:
- Project-level `build.gradle.kts`: Contains project-wide settings
- App-level `build.gradle.kts`: Contains app-specific configurations
- `settings.gradle.kts`: Defines project structure and included modules

## Testing Structure
The project includes separate directories for different types of tests:
- `test/`: Contains unit tests
- `androidTest/`: Contains Android instrumentation tests

## Resources
The `res/` directory contains all application resources including:
- Layouts
- Drawables
- Strings
- Styles
- Other Android resources

## Version Control
The project uses Git for version control with:
- `.gitignore`: Specifies files to be ignored by Git
- `.github/`: Contains GitHub-specific configurations

# Database Overview

## Basic Structure
- **Database**: SQLite database named `records.db`
- **Table**: Single table `records` storing game scores
- **Fields**: 
  - `id` (auto-incrementing primary key)
  - `score` (integer)

## Key Features
- Stores and retrieves game high scores
- Shows top 10 scores in descending order
- Simple and efficient implementation
- Asynchronous loading of records

## Implementation
- Uses `SQLiteOpenHelper` for database management
- Two main operations:
  1. `insertRecord(int score)` - Saves new scores
  2. `getTopRecords()` - Retrieves top 10 scores

## Usage
- Used in `RecordsActivity` to display high scores
- Scores are loaded asynchronously with a progress indicator
- Simple UI showing ranked list of scores

## Technical Details
- Database version: 1
- Basic upgrade strategy (drop and recreate)
- Thread-safe operations
- Proper resource management

## Database Scheme
<img src="https://github.com/fpmi-pmvs2025/pmvs11b-lab8-pickme-team/blob/main/docs/pics/db.png" alt="db_scheme" width="50%" />
