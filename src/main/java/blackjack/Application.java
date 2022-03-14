package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(final String... args) {
        Deck deck = Deck.createShuffledCards();
        BlackjackGame blackjackGame = BlackjackGame.create(InputView.inputParticipantsNames());
        blackjackGame.drawBaseCards(deck);
        OutputView.showParticipantsHand(blackjackGame.getDealer(), blackjackGame.getPlayers());

        processPlayerTurn(blackjackGame.getPlayers(), deck, blackjackGame);
        processDealerTurn(blackjackGame.getDealer(), deck, blackjackGame);
        OutputView.printParticipantResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printBlackjackGameResult(blackjackGame.calculateGameScore());
    }

    private static void processDealerTurn(Participant dealer, Deck deck,
        BlackjackGame blackjackGame) {
        while (blackjackGame.takeMoreCard(dealer, deck)) {
            OutputView.printDealerHandDrawMessage();
        }
    }

    private static void processPlayerTurn(List<Player> players, Deck deck,
        BlackjackGame blackjackGame) {
        for (Player player : players) {
            playerTurn(deck, blackjackGame, player);
        }
    }

    private static void playerTurn(Deck deck, BlackjackGame blackjackGame, Participant player) {
        while (!player.isBust() && InputView.inputOneMoreCard(player) && blackjackGame.takeMoreCard(
            player, deck)) {
            OutputView.showPlayerHand(player);
        }
        if (player.isBust()) {
            OutputView.printBustMessage();
            return;
        }
        OutputView.showPlayerHand(player);
    }
}
