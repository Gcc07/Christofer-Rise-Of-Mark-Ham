# CHRISTOFER: THE RISE OF MARK-HAM

A sequel to the WORLD RENOWNED Chris-Topher (https://number1bidoof.github.io/Chris-topher/), created by the WEB-115 Dev team, this text-based dungeon crawler RPG with roguelike elements is the perfect game for chance-based fun. (Or Frustration.)

Brought to you by...

Gabriel Cardenas — Developer
Rahul Murgai — Developer
Huzaifah Sajjad — Developer
Troy Gardner — Developer (No relation to Mr. Gardner)

All events that may or may not depict characters with likeness to actual humans are fictional, and do not at all represent real life events or characters.

## Game Overview

With your randomly generated character, Navigate through the Ruins of Be-Ta to stop Chris-tofer, the Script-King, from merging with the Dungeon Core. 
Battle enemies, collect items, and upgrade your stats as you descend through 30 floors of procedurally-generated rooms.

### Features
- Random character generation with 5 core stats (Life, Anger, Peace, Smartness, Finesse)
- Multiple room types: Battle, Loot, Shop, Boss, and Special
- Variety of weapons and consumable items
- Timing-based combat minigame for damage multipliers
- Boss encounters every 10 floors

## How to Run

1. Ensure you have Java installed (JDK 8 or higher recommended)
2. Compile all Java files in the GameFiles directory:
   ```bash
   javac GameFiles/*.java
   ```
3. Run the game:
   ```bash
   java -cp GameFiles GameFlow
   ```

## Known Issues

- Missing `break` statements in Boss.java switch cases cause fall-through behavior (all bosses get Final Form Gardner's stats) (FIXED)
- Shop functionality not yet implemented (INCOMPLETE)
- Information Codex (menu option 3) incomplete (NOT STARTED)
- Some item special effects described but not coded (e.g., Lil' Zingbah, Worn Football) (NOT STARTED)
- Moon Blessing system referenced but not implemented (NOT STARTED)
- Item stat modifications don't currently apply when consumed (INCOMPLETE)
- Clicking CTRL + C during the game will immediately cause an error. (UNFIXED)

## AI Usage:
- AI was not used in the programming of this project, aside from where it is explicitly stated to have been used. (see code comments for details.)
- No AI was used in item descriptions or text content.
- All ideas and extended lore is a product of the teams imagination and effort.