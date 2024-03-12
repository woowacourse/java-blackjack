package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.domain.ResultStatus;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Game {

    private static final int BLACKJACK_SCORE = 21;
    private final Dealer dealer;
    private final Players players;


    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void play() {
        initializeHands();

        List<Player> players = this.players.getPlayers();
        OutputView.printInitialHand(dealer, players);

        playerTurn(players, dealer);
        dealerTurn(dealer);

        OutputView.printHandWithScore(dealer, players);
        OutputView.printResult(calculateResult(), dealer);
    }

    private void initializeHands() {
        dealer.shuffleDeck();
        initializeHand(dealer);
        for (Player player : players.getPlayers()) {
            initializeHand(player);
        }
    }

    private void initializeHand(Player player) {
        player.initializeHand(dealer.draw(), dealer.draw());
    }

    private Result calculateResult() {
        int dealerScore = dealer.calculate();
        Map<Player, Integer> playersScore = players.calculate();

        return new Result(playersScore.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        player -> match(dealerScore, player.getValue()),
                        (previous, next) -> next,
                        LinkedHashMap::new)));
    }

    private ResultStatus match(int dealerScore, int playerScore) {
        boolean isDealerBust = dealerScore > BLACKJACK_SCORE;
        boolean isPlayerBust = playerScore > BLACKJACK_SCORE;
        if (isDealerBust && isPlayerBust) {
            return ResultStatus.DRAW;
        }
        if (isDealerBust) {
            return ResultStatus.WIN;
        }
        if (isPlayerBust) {
            return ResultStatus.LOSE;
        }
        final int gap = dealerScore - playerScore;
        return matchWhenDealerAlive(gap);
    }

    private static ResultStatus matchWhenDealerAlive(int gap) {
        if (gap > 0) {
            return ResultStatus.LOSE;
        }
        if (gap == 0) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.WIN;
    }

    private void dealerTurn(Dealer dealer) {
        while (dealer.canHit()) {
            dealer.putCard(dealer.draw());
            OutputView.printDealerDraw(dealer);
        }
    }

    private void playerTurn(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            hitOrStand(dealer, player);
        }
    }

    private void hitOrStand(Dealer dealer, Player player) {
        while (player.canHit() && InputView.readHitOrStand(player)) {
            player.putCard(dealer.draw());
            OutputView.printTotalHand(player);
            ifBust(player);
        }
    }

    private void ifBust(Player player) {
        if (!player.canHit()) {
            OutputView.printBust();
        }
    }
}
