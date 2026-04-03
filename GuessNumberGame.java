import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    private int secretNumber;
    private ArrayList<Integer> guesses;
    private Scanner scanner;
    private Random random;

    public GuessNumberGame() {
        random = new Random();
        scanner = new Scanner(System.in);
        guesses = new ArrayList<>();
        secretNumber = random.nextInt(100) + 1;
    }

    public void startGame() {
        System.out.println("Welcome to the Guess the Number Game!");
        System.out.println("I picked a number between 1 and 100.");
        System.out.println("Try to guess it.");

        boolean guessedCorrectly = false;

        while (!guessedCorrectly) {
            int guess = getUserGuess();
            guesses.add(guess);

            if (guess < secretNumber) {
                System.out.println("Too low. Try again.");
            } else if (guess > secretNumber) {
                System.out.println("Too high. Try again.");
            } else {
                System.out.println("Correct! You guessed the number.");
                guessedCorrectly = true;
            }
        }

        displayGuesses();
        saveResultsToFile();
        System.out.println("Game results were saved to a file.");
    }

    public int getUserGuess() {
        System.out.print("Enter your guess: ");
        return scanner.nextInt();
    }

    public void displayGuesses() {
        System.out.println("Your guesses were:");
        for (int guess : guesses) {
            System.out.println(guess);
        }
        System.out.println("Total guesses: " + guesses.size());
    }

    public void saveResultsToFile() {
        try {
            FileWriter writer = new FileWriter("game_results.txt");
            writer.write("Guess the Number Game Results\n");
            writer.write("Secret number: " + secretNumber + "\n");
            writer.write("Guesses:\n");

            for (int guess : guesses) {
                writer.write(guess + "\n");
            }

            writer.write("Total guesses: " + guesses.size() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    public static void main(String[] args) {
        GuessNumberGame game = new GuessNumberGame();
        game.startGame();
    }
}