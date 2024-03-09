package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Game {

    private final Dealer dealer;
    private final Players players;


    public Game(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void play() {
        initializeHand();

        List<Player> players = getPlayers();
        OutputView.printInitialHand(dealer, players);

        playerTurn(players, dealer);
        dealerTurn(dealer);

        OutputView.printHandWithScore(dealer, players);
        OutputView.printResult(calculateResult(), dealer);
    }

    public void initializeHand() {
        dealer.shuffle();
        for (Player player : players.getPlayers()) {
            initHand(player);
        }
        initHand(dealer);
    }

    private void initHand(Player player) {
        List<Card> cards = this.dealer.doubleDraw();
        for (Card card : cards) {
            player.putCard(card);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Result calculateResult() {
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
        boolean isDealerBurst = dealerScore > 21;
        boolean isPlayerBurst = playerScore > 21;
        if (isDealerBurst && isPlayerBurst) {
            return ResultStatus.DRAW;
        }
        if (isDealerBurst) {
            return ResultStatus.WIN;
        }
        if (isPlayerBurst) {
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
        OutputView.printDealerStand(dealer);
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
            ifBurst(player);
        }
    }

    private void ifBurst(Player player) {
        if (!player.canHit()) {
            OutputView.printBurst();
        }
    }
}
