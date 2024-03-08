package blackjack.domain;

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
        // 21이하인 경우 -> 딜러보다 크거나 21이면 플레이어 승
        // 21 초과하는 경우 -> 패배
        return new Result(playersScore.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Entry::getKey,
                                player -> match(dealerScore, player.getValue()),
                                (previous, next) -> next,
                                LinkedHashMap::new)));
    }

    private ResultStatus match(int dealerScore, int playerScore) {
        final int gap = dealerScore - playerScore;
        if (gap > 0) {
            return ResultStatus.LOSE;
        }
        if (gap == 0) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.WIN;
    }
}
