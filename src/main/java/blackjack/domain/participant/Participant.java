package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Status;
import java.util.stream.IntStream;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Cards cards;
    private Status status;

    public Participant() {
        this.cards = new Cards();
        this.status = Status.HIT;
    }

    public void initCards(Deck deck) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .mapToObj(i -> deck.drawCard())
                .forEach(cards::add);
    }

    public boolean isHit() {
        return status == Status.HIT;
    }

    public int getScore() {
        return cards.sumValue();
    }

    public void hit(Deck deck) {
        cards.add(deck.drawCard());

        if (cards.isBust()) {
            status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return cards.isBlackjack(INITIAL_CARD_COUNT);
    }

    public abstract String getName();
}
