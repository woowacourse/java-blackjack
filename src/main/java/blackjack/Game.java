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

    private void dealerTurn(Dealer dealer) {
        while (dealer.canHit()) {
            dealer.putCard(dealer.draw());
            OutputView.printDealerDraw(dealer);
        }
        OutputView.printDealerStand(dealer);
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
        boolean isDealerBust = dealerScore > 21;
        boolean isPlayerBust = playerScore > 21;
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

    private ResultStatus matchWhenDealerAlive(int gap) {
        if (gap > 0) {
            return ResultStatus.LOSE;
        }
        if (gap == 0) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.WIN;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
