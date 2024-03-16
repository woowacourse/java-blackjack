package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Dealer extends Player {

    private static final int DEALER_HIT_MAGINOT_VALUE = 16;
    private static final int BLACKJACK_SCORE = 21;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;

    public Dealer(Deck deck) {
        super(DEALER_NAME);
        this.deck = deck;
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public Card draw() {
        return deck.draw();
    }

    public Result judgePlayersResult(Players players) {
        int dealerScore = calculate();
        Map<Player, Integer> playersScores = players.calculate();

        return new Result(playersScores.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        player -> getPlayerResult(dealerScore, player.getValue()),
                        (previous, next) -> next,
                        LinkedHashMap::new)));
    }

    @Override
    public boolean canHit() {
        return calculate() <= DEALER_HIT_MAGINOT_VALUE;
    }

    private ResultStatus getPlayerResult(int dealerScore, int playerScore) {
        if (isBustScore(playerScore)) {
            return ResultStatus.LOSE;
        }
        // TODO: 블랙잭인 경우 처리 추리
        if (isBustScore(dealerScore) || playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore < dealerScore) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.DRAW;
    }

    private boolean isBustScore(int score) {
        return score > BLACKJACK_SCORE;
    }
}
