package Game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

import static view.InputView.readIsHit;

public class Blackjack {
    private Map<Gambler, Integer> result;

    public Blackjack(){
        result = new LinkedHashMap<>();
    }

    private static final String DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void start(Players players, Dealer dealer) {
        playersHitOrStand(players);
        dealerHitOrStand(dealer);
        result = getResult(dealer, players);
    }

    private void playersHitOrStand(Players players) {
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

    private void dealerHitOrStand(Dealer dealer) {
        while (dealer.getScore() <= dealer.getPickBoundary()) {
            dealerHit(dealer);
        }
    }

    private void dealerHit(Dealer dealer) {
        dealer.pickCard();
        System.out.println(DEALER_HIT);
    }

    private Map<Gambler, Integer> getResult(Dealer dealer, Players players) {
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        return calculateWinCount(dealer, players, result);
    }

    private Map<Gambler, Integer> calculateWinCount(Dealer dealer, Players players, Map<Gambler, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(dealer, result, player);
        }
        return result;
    }

    private void decideWinner(Dealer dealer, Map<Gambler, Integer> result, Player player) {
        if (isPlayerWin(dealer, player)) {
            result.put(player, 1);
        }

        if (isDealerWin(dealer, player)) {
            result.put(player, 0);
            result.replace(dealer, result.get(dealer) + 1);
        }
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return (dealerScore <= playerScore && !player.isBustedGambler()
                || (dealer.isBustedGambler() && !player.isBustedGambler()));
    }

    private boolean isDealerWin(Dealer dealer, Player player) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        return dealerScore > playerScore || player.isBustedGambler();
    }

    public Map<Gambler, Integer> getResult() {
        return result;
    }
}
