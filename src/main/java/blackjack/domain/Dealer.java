package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Dealer extends Participant {

    private static final int DEALER_HIT_MAGINOT_VALUE = 16;
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
        Map<Player, Integer> playersScores = players.calculate();
        Map<Player, Integer> playersEarnings = new LinkedHashMap<>();

        int dealerScore = calculate();
        // TODO: VO 생성? 리팩토링
        for (Entry<Player, Integer> playerScore : playersScores.entrySet()) {
            Player player = playerScore.getKey();
            int playerBetting = player.getBetting();
            ResultStatus status = getPlayerResult(dealerScore, playerScore.getValue());
            playersEarnings.put(player, (int) (playerBetting * status.getMultiplier()));
        }
        return new Result(playersEarnings);
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
