package blackjack;

import blackjack.domain.playing.HitOrStay;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.Dealer;
import blackjack.domain.playing.user.Player;
import blackjack.domain.playing.user.Players;
import blackjack.domain.playing.user.exception.HitOrStayException;
import blackjack.domain.result.BettingMoney;
import blackjack.domain.result.BettingMoneyRepository;
import blackjack.domain.result.Exception.BettingMoneyException;
import blackjack.domain.result.ProfitResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Blackjack {
    public static void main(String[] args) {
        Players players = Players.of(InputView.inputPlayerNames());
        createBettingResult(players);

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

    private static void createBettingResult(Players players) {
        for (Player player : players.getPlayers()) {
            BettingMoney bettingMoney = inputBettingMoney(player);
            BettingMoneyRepository.save(player, bettingMoney);
        }
    }

    private static BettingMoney inputBettingMoney(Player player) {
        try {
            return BettingMoney.from(InputView.inputBettingMoney(player));
        } catch (BettingMoneyException e) {
            OutputView.printExceptionMessage(e);
            return inputBettingMoney(player);
        }
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
