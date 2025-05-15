# TypeMaster Overview

TypeMaster is a typing game designed to enhance real-life typing skills. Players must type predefined stories accurately, erasing and correcting mistakes. The game features different modes and difficulty levels with a modern JavaFX-based UI.

---

## Development Setup

### 1. Programming Language
- Java (JDK 21)

### 2. Framework & GUI
- JavaFX (for GUI and animations)

### 3. IDE
- IntelliJ IDEA

### 4. Version Control
- Git (for source code management and collaboration)

### 5. Story Management
- Predefined stories stored in text or JSON files.

### 6. Core Features
- Dynamic text rendering.
- Real-time input validation.
- Word highlighting (correct/incorrect feedback).
- Game state management (starting, scoring, and ending conditions).
- Animations for smooth transitions.

---

## Mechanics
- JavaFX-based input field (required).
- Players must manually erase and correct mistakes.
- Real-time feedback on correct/incorrect letters.
- Words-per-minute (WPM) tracking implemented to measure typing speed.

---

## Next Steps

1. **Implement story loading system**
   - Stwórz model i serwis do ładowania opowiadań z plików (np. TXT lub JSON).
   - Wyświetlanie dostępnych historii na ekranie wyboru.

2. **Build gameplay logic**
   - Zaprojektuj widok do przepisania tekstu (wyświetlanie i input).
   - Dodaj walidację wpisywanego tekstu w czasie rzeczywistym z wymuszeniem poprawek.
   - Wprowadź podświetlanie poprawnych i błędnych fragmentów.

3. **Add statistics tracking**
   - Zaimplementuj śledzenie i wyświetlanie słów na minutę (WPM) oraz innych statystyk.
   - Dodaj wyświetlanie podsumowania po zakończeniu rozgrywki.

4. **Enhance animations and UI feedback**
   - Dodaj animacje oraz efekty wizualne podczas przełączania ekranów i wpisywania tekstu.
   - Zadbaj o wyraźny feedback przy poprawnych i błędnych akcjach.

5. **Finish and improve settings**
   - Podłącz ustawienia do faktycznego działania (np. zmiana rozdzielczości, fullscreen).

6. **(Opcjonalnie) Rozszerz możliwości gry**
   - Dodaj zapis wyników i ranking.
   - Wprowadź nowe tryby rozgrywki oraz opcję importu własnych tekstów.