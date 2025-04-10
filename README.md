# TypeMaster Overview

TypeMaster is a typing game designed to enhance real-life typing skills. Players must type predefined stories accurately, erasing and correcting mistakes. The game features different modes and difficulty levels with a modern JavaFX-based UI.

---

## Development Setup

### 1. Programming Language
- Java (JDK 24)

### 2. Framework & GUI
- JavaFX (for GUI and animations)

### 3. IDE
- IntelliJ IDEA

### 4. Version Control
- Git (for source code management and collaboration)

### 5. Story Management
- Initially: Predefined stories stored in text or JSON files.
- Future: Procedural story generation.

### 6. Core Features
- Dynamic text rendering.
- Real-time input validation.
- Word highlighting (correct/incorrect feedback).
- Game state management (starting, scoring, and ending conditions).
- Animations for smooth transitions.

---

## Game Modes

### 1. Story Mode
- Players type out predefined stories (up to 300 words).
- Three difficulty levels: Easy, Medium, Hard (affects word complexity).
- No time limit.

### 2. Type Attack Mode
- Players type as many words as possible.
- Stories up to 1000 words and a 1-minute time limit.
- Three difficulty levels affecting word complexity.
- Every error subtracts 1 second from the remaining time.

### 3. Survival Mode
- Players must type without making mistakes.
- A single error results in game over.
- Increasing difficulty as words become more complex.
- Players must maintain a minimum words-per-minute (WPM) threshold to avoid losing.

---

## Mechanics
- JavaFX-based input field (required).
- Players must manually erase and correct mistakes.
- Real-time feedback on correct/incorrect letters.
- Words-per-minute (WPM) tracking implemented to measure typing speed.

---

## Next Steps
- Implement predefined story loading system.
- Build JavaFX-based GUI for displaying and typing stories.
- Implement real-time input validation and correction system.
- Add animations and UI enhancements.