---
layout: default
title: Additional specification
---
# Non-functional requirements

## 1. Constraints
- **Platform Compatibility**: The application must support Android devices running API level 21 (Lollipop) and above.
- **Resource Usage**: The application should be optimized to use minimal CPU and memory resources to ensure smooth performance on a wide range of devices.
- **Network Dependency**: The application should function offline, except for the feature that requires fetching random numbers from an external API.

## 2. Security Requirements
- **Data Protection**: User scores and records should be stored securely in the local SQLite database. Consider encrypting sensitive data to prevent unauthorized access.
- **Permissions**: The application should request only the necessary permissions required for its functionality, minimizing potential security risks.
- **API Security**: Ensure secure communication with external APIs using HTTPS to protect data in transit.

## 3. Reliability Requirements
- **Error Handling**: The application should gracefully handle errors, such as network failures or database access issues, and provide informative feedback to the user.
- **Data Integrity**: Ensure that game progress and high scores are consistently saved and retrieved without corruption.
- **Crash Recovery**: The application should be able to recover gracefully from unexpected crashes, preserving the user's game state whenever possible.

## 4. Performance Requirements
- **Response Time**: The user interface should respond to user actions within 100 milliseconds to ensure a smooth user experience.
- **Loading Times**: Data loading operations, such as fetching records from the database, should be performed asynchronously to avoid blocking the main UI thread.
- **Animation Smoothness**: Although animations are not required, any visual updates should be rendered smoothly at 60 frames per second if implemented.

## 5. Other Considerations
- **User Experience**: The application should have an intuitive and user-friendly interface, making it easy for both new and experienced players to navigate and play the game.
- **Localization**: Consider supporting multiple languages to reach a broader audience, if applicable.
- **Accessibility**: Ensure that the application is accessible to users with disabilities by following Android accessibility guidelines.
