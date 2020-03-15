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

        Deck deck = Deck.createWithShuffle();

        dealer.receiveInitialCards(deck);
        players.receiveTwoCards(deck);

        OutputView.printInitialInfo(dealer, players);

        playAllPlayersTurn(players, deck);
        playDealerTurn(dealer, deck);

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

    private static void playAllPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(deck, player);
        }
    }

    private static void playPlayerTurn(Deck deck, Player player) {
        while (player.isNotBust() && inputToHit(player)) {
            player.drawCard(deck);
            OutputView.printUserCard(player);
        }
    }

    private static void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.shouldReceiveCard()) {
            dealer.drawCard(deck);
            OutputView.printDealerTurn(dealer);
        }
    }
}
