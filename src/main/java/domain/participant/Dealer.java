package domain.participant;

import domain.Answer;
import domain.Profit;
import domain.card.CardDeck;

public class Dealer extends Participant {

    public static final int INIT_HANDS_SIZE = 2;
    public static final int THRESHOLD = 16;
    public static final String NAME = "딜러";

    private final CardDeck cardDeck;

    public Dealer(final CardDeck cardDeck, final Hands hands) {
        super(new Name(NAME), hands);
        this.cardDeck = cardDeck;
    }

    public static Dealer from(final CardDeck cardDeck) {
        return new Dealer(cardDeck, Hands.createEmptyHands());
    }

    public void initHands(final Players players) {
        for (int i = 0; i < INIT_HANDS_SIZE; i++) {
            players.forEach(player -> player.add(cardDeck.pop()));
            super.add(cardDeck.pop());
        }
    }

    public void deal(final Player player, final Answer answer) {
        if (answer.isHit()) {
            player.add(cardDeck.pop());
        }
    }

    public void deal() {
        while (canDeal()) {
            super.add(cardDeck.pop());
        }
    }

    public int countAddedHands() {
        return handsSize() - INIT_HANDS_SIZE;
    }

    public Profit calculateProfitBy(final Players players) {
        final long result = players.calculateProfits(this).values().stream()
                .map(profit -> profit.getValue() * (-1))
                .mapToLong(Long::longValue)
                .sum();

        return new Profit(result);
    }

    @Override
    public boolean canDeal() {
        return handsSum() <= THRESHOLD;
    }
}
