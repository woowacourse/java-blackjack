package blackjack;

import blackjack.domain.Result;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        Players players = Players.of(playerNames);
        Dealer dealer = DefaultDealer.create();
        Deck deck = Deck.create();
        deck.shuffle();

        dealer.giveCards(deck.draw(), deck.draw());
        for (int i = 0; i < players.memberSize(); i++) {
            players.giveCards(i, deck.draw(), deck.draw());
        }

        OutputView.printInitialInfo(dealer, players);

        for (Player player : players.getPlayers()) {
            while (player.isNotBust() && InputView.inputToHitOrStay(player).equals("y")) {
                player.giveCards(deck.draw());
                OutputView.printUserCard(player);
            }
        }

        while (dealer.shouldReceiveCard()) {
            dealer.giveCards(deck.draw());
            OutputView.printDealerTurn(dealer);
        }

        OutputView.printFinalInfo(dealer, players);

        Result result = Result.of(dealer, players);

        OutputView.printResult(result);
    }
}
