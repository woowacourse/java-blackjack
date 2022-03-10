package blackjack;

import blackjack.domain.BlackJackBoard;

public class Application {

    public static void main(final String[] args) {
        final BlackJackBoard blackJackBoard = BlackJackGame.createBlackJackBoard();
        BlackJackGame.printFirstDrawCard(blackJackBoard);
        BlackJackGame.runAllPlayerTurn(blackJackBoard);
        BlackJackGame.printAllResults(blackJackBoard);
    }
}
