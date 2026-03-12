package blackjack;

public class BlackjackApplication {

    public static void main(final String[] args) {
        final BlackjackBoard board = BlackjackBoardFactory.create();
        new BlackjackGame(board).start();
    }
}
