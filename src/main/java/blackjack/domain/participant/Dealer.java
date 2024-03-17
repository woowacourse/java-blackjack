package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.Result;
import blackjack.domain.game.ResultStatus;
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
        int betting = player.getBetting();
        int playerScore = player.calculate();
        if (player.isBust()) {
            return calculateEarning(betting, ResultStatus.LOSE);
        }
        if (playerScore == dealerScore) {
            return calculateEarning(betting, ResultStatus.DRAW);
        }
        if (player.isBlackjack()) {
            return calculateEarning(betting, ResultStatus.BLACKJACK);
        }
        if (this.isBust() || playerScore > dealerScore) {
            return calculateEarning(betting, ResultStatus.WIN);
        }
        return calculateEarning(betting, ResultStatus.LOSE);
    }

    private int calculateEarning(int betting, ResultStatus status) {
        return (int) (betting * status.getMultiplier());
    }
}
