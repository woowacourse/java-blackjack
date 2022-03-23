package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.RandomDeck;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.inputPlayerNames();
        Map<String, BetMoney> playerNameAndBets = InputView.inputBettingMoney(names);
        BlackjackGame blackjackGame = new BlackjackGame();
        Deck deck = new RandomDeck();

        Players players = blackjackGame.start(names);
        ResultView.printInitHand(blackjackGame.takeInitHand(deck));

        takeTurns(blackjackGame, players, deck);
        takeDealerTurn(blackjackGame, deck);

        ResultView.printCardsResults(blackjackGame.calculateCardResult());
        ResultView.printProfit(blackjackGame.calculateProfit(playerNameAndBets));
    }

    private void takeTurns(BlackjackGame blackjackGame, Players players, Deck deck) {
        for (User player : players.get()) {
            takePlayerCards(blackjackGame, player, deck);
        }
    }

    private void takePlayerCards(BlackjackGame blackjackGame, User player, Deck deck) {
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
}
