package model;

import java.util.Random;
import java.util.Scanner;

public class Raid {
    private Superhero[] heroes; // Array für Superhelden
    private int heroCount; // Anzahl der Superhelden

    // Konstruktor, um Superhelden zu initialisieren
    public Raid(Superhero[] heroes) {
        this.heroes = heroes;
        this.heroCount = heroes.length;
    }

    // Hauptfunktion, um den Raubzug zu starten
    public void startRaid() {
        // Überprüfen, ob Superhelden verfügbar sind
        if (heroCount == 0) {
            System.out.println("Keine Superhelden verfügbar. Der Raubzug kann nicht starten!");
            return;
        }

        // Ausgabe der Superhelden
        System.out.println("Der Raubzug wurde gestartet! Mit diesen Helden:");
        for (int i = 0; i < heroCount; i++) {
            System.out.println("- " + heroes[i].getName());
        }

        // Das Raid-Menü wird wiederholt angezeigt
        boolean raidActive = true;
        Scanner sc = new Scanner(System.in);
        while (raidActive) {
            System.out.println("\n=== RAID MENU ===");
            System.out.println("(1) Explore");
            System.out.println("(2) Dance");
            System.out.println("(3) Quit Raid");
            System.out.print("Wähle eine Option: ");
            int choice = sc.nextInt();

            // Option auswählen
            switch (choice) {
                case 1:
                    explore();
                    break;
                case 2:
                    dance();
                    break;
                case 3:
                    raidActive = false;
                    System.out.println("Zurück ins Hauptmenü...");
                    break;
                default:
                    System.out.println("Ungültige Auswahl. Versuche es noch einmal.");
            }
        }
    }

    // Die "Explore"-Option des Raubzugs
    private void explore() {
        Random rand = new Random();
        Villain encounteredVillain = createRandomVillain(); // Zufälliger Gegner wird erstellt

        // 50% Chance für einen Gegner
        System.out.println("\nErforschen der feindlichen Zentrale...");
        if (rand.nextBoolean()) {
            System.out.println("Du hast einen " + encounteredVillain.getName() + " getroffen!");
            if (encounteredVillain.isArtefactPresent()) {
                meetEnemy(encounteredVillain);
            } else {
                System.out.println("Du hast diesen Gegner schon besiegt. Du schleichst dich davon.");
            }
        } else {
            System.out.println("Die Gegend scheint sicher. Kein Gegner gefunden.");
        }
    }

    // Interaktion mit einem Gegner
    private void meetEnemy(Villain villain) {
        Random rand = new Random();

        // Wenn der Gegner ein Employee ist, gibt es einen Kampf
        if (villain instanceof Employee) {
            System.out.println("Du hast einen Employee getroffen! Ein Kampf beginnt.");
            fightEmployee(villain);
        } else {
            System.out.println("Du hast einen " + villain.getName() + " getroffen! Es wird ein Rätsel gespielt.");
            playPuzzle(villain); // Ein Rätsel wird gespielt
        }
    }

    // Der Kampf gegen einen Employee
    private void fightEmployee(Villain villain) {
        Random rand = new Random();
        int damageToVillain = rand.nextInt(50) + 1; // Zufälliger Schaden an Gegner
        int damageToHero = rand.nextInt(50) + 1; // Zufälliger Schaden am Superhelden

        System.out.println("Der Superheld kämpft gegen " + villain.getName());
        System.out.println("Schaden an Gegner: " + damageToVillain);
        System.out.println("Schaden am Superhelden: " + damageToHero);

        // Schaden an Gegner und Superhelden anrichten
        villain.takeDamage(damageToVillain);

        // Wenn der Superheld Schaden erhält, verliert er Energie
        if (damageToHero > 0) {
            heroes[0].takeDamage(damageToHero); // Beispiel für den ersten Superhelden
        }

        // Überprüfen, ob der Gegner besiegt wurde
        if (!villain.isAlive()) {
            System.out.println("Du hast den Gegner besiegt!");
        } else {
            System.out.println("Du hast den Kampf verloren.");
            loseLife(); // Der Superheld verliert ein Leben
        }
    }

    // Rätsel lösen (einfache Zufallsentscheidung)
    private void playPuzzle(Villain villain) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        System.out.println("Du spielst ein Rätsel gegen " + villain.getName());
        
        // Einfaches Zufallsspiel (Zahlen raten)
        System.out.print("Rate eine Zahl zwischen 1 und 10: ");
        int userGuess = sc.nextInt();
        int correctAnswer = rand.nextInt(10) + 1; // Zufällige Zahl für das Rätsel

        if (userGuess == correctAnswer) {
            System.out.println("Du hast das Rätsel gelöst! Du erhältst das Artefakt.");
            villain.setArtefactPresent(false); // Artefakt wird übergeben
        } else {
            System.out.println("Du hast das Rätsel verloren.");
            loseLife(); // Der Superheld verliert ein Leben
        }
    }

    // Ein Leben des Superhelden verlieren
    private void loseLife() {
        Random rand = new Random();
        int heroIndex = rand.nextInt(heroCount); // Zufällig einen Superhelden auswählen
        Superhero hero = heroes[heroIndex];
        hero.takeDamage(20); // Der Superheld verliert 20 Energiepunkte
        System.out.println(hero.getName() + " hat ein Leben verloren!");
    }

    // Zufälligen Gegner erstellen
    private Villain createRandomVillain() {
        Random rand = new Random();
        int villainType = rand.nextInt(4); // Zufällig einen Gegnertyp auswählen
        switch (villainType) {
            case 0: return new Employee(); // Employee
            case 1: return new Admin(); // Admin
            case 2: return new Manager(); // Manager
            case 3: return new TheBigBoss(); // TheBigBoss
            default: return new Employee(); // Fallback
        }
    }

    // Tanz-Option
    private void dance() {
        // Alle Superhelden tanzen lassen
        for (Superhero hero : heroes) {
            System.out.println(hero.getName() + " tanzt!");
        }
    }
}