package model;

import java.util.Random;
import java.util.Scanner;

public class NrGuessing implements Game {
    private int targetNumber;
    private int currentRound;
    private boolean finished;
    private boolean won;

    public NrGuessing() {
        this.currentRound = 0;
        this.finished = false;
        this.won = false;
        generateNewNumber();
    }

    @Override
    public void playNextRound() {
        if (finished) return;

        Scanner scanner = new Scanner(System.in);
        currentRound++;
        System.out.println("Runde " + currentRound + ": Rate eine Zahl zwischen 1 und 100.");

        int guess = scanner.nextInt();
        if (guess == targetNumber) {
            finished = true;
            won = true;
            System.out.println("Herzlichen Glückwunsch! Du hast die Zahl richtig geraten!");
        } else if (guess < targetNumber) {
            System.out.println("Die Zahl ist größer.");
        } else {
            System.out.println("Die Zahl ist kleiner.");
        }
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean isWon() {
        return won;
    }

    @Override
    public boolean isLost() {
        return !won && finished;
    }

    @Override
    public boolean isTie() {
        return false;  // Für dieses Spiel gibt es kein Unentschieden
    }

    private void generateNewNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;  // Zufallszahl zwischen 1 und 100
    }
}
