package model;

import java.util.Random;

public class RollDice implements Game {
    private int targetNumber;
    private int currentRound;
    private boolean finished;
    private boolean won;

    public RollDice() {
        this.currentRound = 0;
        this.finished = false;
        this.won = false;
        this.targetNumber = 6;  // Zielwert für das Würfeln (kann angepasst werden)
    }

    @Override
    public void playNextRound() {
        if (finished) return;

        Random rand = new Random();
        currentRound++;
        System.out.println("Runde " + currentRound + ": Würfle den Würfel.");

        int rolledNumber = rand.nextInt(6) + 1;  // Zufallszahl zwischen 1 und 6
        System.out.println("Du hast eine " + rolledNumber + " geworfen.");

        if (rolledNumber == targetNumber) {
            finished = true;
            won = true;
            System.out.println("Du hast das Spiel gewonnen! Du hast die Zielzahl geworfen.");
        } else {
            System.out.println("Versuche es noch einmal.");
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
}

