package model; 

import java.util.Scanner;

public class TicTacToe implements Game {
    private char[][] board;  // Spielfeld
    private char currentPlayer; // Aktueller Spieler (X oder O)
    private boolean gameFinished;  // Flag, das angibt, ob das Spiel beendet ist
    private boolean won;  // Flag, das angibt, ob der aktuelle Spieler gewonnen hat
    private boolean draw;  // Flag, das angibt, ob das Spiel unentschieden ist
    private int currentRound; // Aktuelle Runde

    // Konstruktor
    public TicTacToe() {
        board = new char[3][3];  // Spielfeld initialisieren
        currentPlayer = 'X';  // Spieler X beginnt
        gameFinished = false;  // Spiel läuft noch
        won = false;  // Niemand hat gewonnen
        draw = false;  // Kein Unentschieden
        currentRound = 0;  // Start mit Runde 0
        initializeBoard();
    }

    // Initialisiert das Spielfeld mit leeren Feldern
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';  // Alle Felder leer
            }
        }
    }

    // Gibt das Spielfeld auf der Konsole aus
    private void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");  // Zeigt jedes Feld an
            }
            System.out.println();  // Neue Zeile nach jeder Reihe
        }
    }

    // Führt den Zug des aktuellen Spielers aus
    private void makeMove(int row, int col) {
        if (board[row][col] == '-') {
            board[row][col] = currentPlayer;
            currentRound++;  // Erhöht die Runde nach jedem Zug
        } else {
            System.out.println("Dieses Feld ist bereits belegt. Versuch es nochmal!");
        }
    }

    // Wechselt den Spieler
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Wechsel von X zu O und umgekehrt
    }

    // Prüft, ob der aktuelle Spieler gewonnen hat
    private boolean checkWin() {
        // Überprüfen aller Reihen, Spalten und Diagonalen
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;  // Reihen prüfen
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;  // Spalten prüfen
            }
        }
        // Diagonalen prüfen
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // Prüft, ob das Spielfeld voll ist und das Spiel unentschieden endet
    private boolean isDraw() {
        return currentRound == 9;  // Wenn alle Felder belegt sind, ist es ein Unentschieden
    }

    // Methode, die aus dem Interface kommt, um den nächsten Spielzug durchzuführen
    @Override
    public void playNextRound() {
        if (gameFinished) return;  // Wenn das Spiel bereits beendet ist, tue nichts

        Scanner scanner = new Scanner(System.in);

        printBoard();  // Zeigt das aktuelle Spielfeld an

        // Fragt den aktuellen Spieler nach einem Zug
        System.out.println("Spieler " + currentPlayer + ", gib deine Position ein (Zeile, Spalte): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        makeMove(row, col);  // Führt den Zug aus

        // Überprüft, ob jemand gewonnen hat
        if (checkWin()) {
            printBoard();
            System.out.println("Spieler " + currentPlayer + " hat gewonnen!");
            gameFinished = true;  // Spiel ist beendet
            won = true;  // Aktueller Spieler hat gewonnen
        } else if (isDraw()) {
            printBoard();
            System.out.println("Unentschieden!");
            gameFinished = true;  // Spiel ist beendet
            draw = true;  // Das Spiel endet unentschieden
        } else {
            switchPlayer();  // Wechselt den Spieler
        }
    }

    // Methode aus dem Interface: Gibt die aktuelle Runde zurück
    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    // Methode aus dem Interface: Überprüft, ob das Spiel beendet ist
    @Override
    public boolean isFinished() {
        return gameFinished;
    }

    // Methode aus dem Interface: Überprüft, ob der aktuelle Spieler gewonnen hat
    @Override
    public boolean isWon() {
        return won;
    }

    // Methode aus dem Interface: Überprüft, ob das Spiel verloren wurde
    @Override
    public boolean isLost() {
        return false;  // Diese Methode könnte für andere Spiele genutzt werden, hier nicht notwendig
    }

    // Methode aus dem Interface: Überprüft, ob das Spiel unentschieden endet
    @Override
    public boolean isTie() {
        return draw;
    }
}