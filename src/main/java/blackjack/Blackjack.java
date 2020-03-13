package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.result.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        Players players = Players.of(playerNames);
        Dealer dealer = Dealer.create();

        Deck deck = Deck.create();
        deck.shuffle();

        dealer.drawCards(deck.draw(), deck.draw());
        for (int i = 0; i < players.memberSize(); i++) {
            players.drawCards(i, deck.draw(), deck.draw());
        }

        OutputView.printInitialInfo(dealer, players);

        for (Player player : players.getPlayers()) {
            while (player.isNotBust() && InputView.inputToHitOrStay(player).equals("y")) {
                player.drawCards(deck.draw());
                OutputView.printUserCard(player);
            }
        }

        while (dealer.shouldReceiveCard()) {
            dealer.drawCards(deck.draw());
            OutputView.printDealerTurn(dealer);
        }

        OutputView.printFinalInfo(dealer, players);

        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printGameResult(gameResult);
    }
}
