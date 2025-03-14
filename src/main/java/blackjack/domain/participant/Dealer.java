package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    public Dealer(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public boolean canHit() {
        int hitScore = getScore().intValue();
        return hitScore <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public List<Card> showStartCards() {
        Card firstCard = cardHand.getCards().getFirst();
        return List.of(firstCard);
    }

    public GameResult informResultTo(Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (this.isBust()) {
            return GameResult.WIN;
        }

        if (this.isBlackjack() && player.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK_WIN;
        }

        if (player.getScore().isHigherThan(this.getScore())) {
            return GameResult.WIN;
        }
        if (this.getScore().isHigherThan(player.getScore())) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public int calculateProfit(Map<Player, Integer> playersProfit) {
        int totalPlayersProfit = playersProfit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return -totalPlayersProfit;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
