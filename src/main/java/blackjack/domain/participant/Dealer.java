package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;
import blackjack.strategy.ShuffleStrategy;
import java.util.stream.IntStream;

public class Dealer extends Gamer {

    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;
    public static final int DEALER_BOUND = 16;

    private final Deck deck;

    private Dealer(final Deck deck) {
        super();

        this.deck = deck;
    }

    public static Dealer create(final ShuffleStrategy shuffleStrategy) {
        return new Dealer(new Deck(shuffleStrategy));
    }

    public boolean requestExtraCard() {
        if (canReceiveCard()) {
            draw(1);
            return true;
        }
        return false;
    }

    @Override
    boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }

    @Override
    public String getProfit() {
        return profit.toString();
    }

    public void draw(final int count) {
        IntStream.range(0, count)
                .forEach(i -> hand.add(deck.draw()));
    }

    public void initialDeal() {
        draw(BLACKJACK_INIT_CARD_AMOUNT);
    }

    public Card draw() {
        return deck.draw();
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    public boolean isSameScore(final long score) {
        return hand.calculateScore() == score;
    }

    public Profit getDealerProfit() {
        return profit;
    }
}
