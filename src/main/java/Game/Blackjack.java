package Game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import view.InputView;
import view.OutputView;

import java.util.Map;

import static view.InputView.readIsHit;

public class Blackjack {

    private static final String DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private final Players players;
    private final Dealer dealer;
    private Result result;

    public Blackjack(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void play() {
        playersHitOrStand();
        dealerHitOrStand();
        this.result = new Result(players, dealer);
    }

    private void playersHitOrStand() {
        for (Player player : players.getPlayers()) {
            playerHitOrStand(player);
        }
    }

    private void playerHitOrStand(Player player) {
        boolean isHit;
        do {
            isHit = getIsHit(player);
            playerHit(player, isHit);
            OutputView.printSingleGambler(player);
            isHit = isPickAble(player, isHit);
        } while (isHit);
    }

    private boolean isPickAble(Player player, boolean isHit) {
        if (player.isBustedGambler()) {
            isHit = false;
        }
        return isHit;
    }

    private boolean getIsHit(Player player) {
        boolean isHit;
        try {
            isHit = readIsHit(player);
        } catch (RuntimeException exception) {
            InputView.printErrorMessage(exception);
            isHit = getIsHit(player);
        }
        return isHit;
    }

    private void playerHit(Player player, boolean isHit) {
        if (isHit) {
            player.pickCard();
        }
    }

    private void dealerHitOrStand() {
        while (dealer.getScore() <= dealer.getPickBoundary()) {
            dealerHit();
        }
    }

    private void dealerHit() {
        dealer.pickCard();
        System.out.println(DEALER_HIT);
    }

    public Map<Gambler, Integer> getResultMap() {
        return result.getResult();
    }
}
