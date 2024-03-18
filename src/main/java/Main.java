import blackjack.BlackJackGameBoard;

public class Main {
    public static void main(String[] args) {
        try {
            BlackJackGameBoard.startGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
