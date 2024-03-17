package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

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
        Map<Player, Integer> playersEarnings = new LinkedHashMap<>();

        int dealerScore = calculate();
        for (Player player : players.getPlayers()) {
            playersEarnings.put(player, getPlayerResult(dealerScore, player));
        }
        return new Result(playersEarnings);
    }

    @Override
    public boolean canHit() {
        return calculate() <= DEALER_HIT_MAGINOT_VALUE;
    }

    private int getPlayerResult(int dealerScore, Player player) {
        int playerScore = player.calculate();
        int betting = player.getBetting();
        if (isBustScore(playerScore)) {
            return getEarning(betting, ResultStatus.LOSE);
        }
        if (playerScore == dealerScore) {
            return getEarning(betting, ResultStatus.DRAW);
        }
        if (player.isBlackjack()) {
            return getEarning(betting, ResultStatus.BLACKJACK);
        }
        if (isBustScore(dealerScore) || playerScore > dealerScore) {
            return getEarning(betting, ResultStatus.WIN);
        }
        return getEarning(betting, ResultStatus.LOSE);
    }

    private int getEarning(int betting, ResultStatus status) {
        return (int) (betting * status.getMultiplier());
    }

    private boolean isBustScore(int score) {
        return score > BLACKJACK_SCORE;
    }
}
