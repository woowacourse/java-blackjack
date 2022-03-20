package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.RandomDeck;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.inputPlayerNames();
        BlackjackGame blackjackGame = new BlackjackGame();
        Deck deck = new RandomDeck();

        Players players = blackjackGame.start(names);
        ResultView.printInitHand(blackjackGame.takeInitHand(deck));

        takeTurns(blackjackGame, players, deck);
        takeDealerTurn(blackjackGame, deck);

        printResults(blackjackGame);
    }

    private void takeTurns(BlackjackGame blackjackGame, Players players, Deck deck) {
        for (Player player : players.get()) {
            takePlayerCards(blackjackGame, player, deck);
        }
    }

    private void takePlayerCards(BlackjackGame blackjackGame, Player player, Deck deck) {
        if (blackjackGame.isPlayerFinished(player) || InputView.requestIsStay(player)) {
            return;
        }
        blackjackGame.takePlayerCard(player, deck);
        ResultView.printHand(player);
        takePlayerCards(blackjackGame, player, deck);
    }

    private void takeDealerTurn(BlackjackGame blackjackGame, Deck deck) {
        while (!blackjackGame.isDealerFinished()) {
            ResultView.printDealerHitMessage();
            blackjackGame.takeDealerCard(deck);
        }
    }

    private void printResults(BlackjackGame blackjackGame) {
        ResultView.printCardsResults(blackjackGame.calculateCardResult());
        ResultView.printOutcomes(blackjackGame.calculateOutcome());
    }
}
