package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.result.GameResult;
import blackjack.domain.user.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        String playerNames = InputView.inputPlayerNames();
        Players players = Players.of(playerNames);
        Dealer dealer = Dealer.create();

        Deck deck = Deck.create();
        deck.shuffle();

        dealer.drawCards(deck, 2);
        players.drawCards(deck, 2);

        OutputView.printInitialInfo(dealer, players);

        for (Player player : players.getPlayers()) {
            while (player.isNotBust() && inputToHit(player)) {
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

    private static boolean inputToHit(Player player) {
        try {
            HitOrStay hitOrStay = HitOrStay.of(InputView.inputToHitOrStay(player));
            return hitOrStay.isToHit();
        } catch (HitOrStayException e) {
            return inputToHit(player);
        }
    }
}
