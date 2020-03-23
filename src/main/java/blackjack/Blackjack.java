package blackjack;

import blackjack.domain.playing.HitOrStay;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.Dealer;
import blackjack.domain.playing.user.Player;
import blackjack.domain.playing.user.Players;
import blackjack.domain.playing.user.exception.HitOrStayException;
import blackjack.domain.result.ProfitResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        Players players = Players.of(InputView.inputPlayerProperties());
        Dealer dealer = Dealer.create();
        Deck deck = Deck.createWithShuffle();

        dealer.drawCardsAtFirst(deck);
        players.drawCardsAtFirst(deck);

        OutputView.printInitialInfo(dealer, players);

        playAllPlayersTurn(players, deck);
        playDealerTurn(dealer, deck);

        OutputView.printFinalInfo(dealer, players);

        ProfitResult profitResult = ProfitResult.of(players, dealer);
        OutputView.printProfitResult(profitResult);
    }


    private static void playAllPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(deck, player);
        }
    }

    private static void playPlayerTurn(Deck deck, Player player) {
        while (playerCanHit(player)) {
            player.drawCardsInTurn(deck);
            OutputView.printUserCard(player);
        }
    }

    private static boolean playerCanHit(Player player) {
        return player.isNotBlackjack() && player.isNotBust() && inputToHit(player);
    }

    private static boolean inputToHit(Player player) {
        try {
            HitOrStay hitOrStay = HitOrStay.of(InputView.inputToHitOrStay(player));
            return hitOrStay.isToHit();
        } catch (HitOrStayException e) {
            OutputView.printExceptionMessage(e);
            return inputToHit(player);
        }
    }

    private static void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.shouldDrawCard()) {
            dealer.drawCardsInTurn(deck);
            OutputView.printDealerTurn(dealer);
        }
    }
}
