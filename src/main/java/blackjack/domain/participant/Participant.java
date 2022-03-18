package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import java.util.stream.IntStream;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    protected final Cards cards;
    protected PlayerStatus playerStatus;

    public Participant() {
        this.cards = new Cards();
        this.playerStatus = PlayerStatus.HIT;
    }

    public void initCards(Deck deck) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .mapToObj(i -> deck.drawCard())
                .forEach(cards::add);
    }

    public int getScore() {
        return cards.sumValue();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return playerStatus == PlayerStatus.BLACKJACK;
    }

    protected boolean checkBlackjack() {
        return cards.isBlackjack(INITIAL_CARD_COUNT);
    }

    public abstract void hit(Deck deck);

    public abstract String getName();

    public abstract boolean isDrawable();
}
