import console.InputConsole;
import console.OutputConsole;
import game.BlackJackGame;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(
                new InputConsole(new Scanner(System.in)),
                new OutputConsole());
        blackJackGame.play();
    }
}
