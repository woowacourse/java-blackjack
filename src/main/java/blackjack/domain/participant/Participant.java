package blackjack.domain.participant;

import blackjack.domain.Name;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;
import java.util.stream.IntStream;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Cards cards;
    private final Name name;
    private Status status;

    public Participant(String name) {
        this.cards = new Cards();
        this.name = new Name(name);
        this.status = Status.HIT;
    }

    public void prepareGame(Deck deck) {
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

        if (cards.getStatus() == Status.BUST) {
            status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name.getValue();
    }

    public Status getStatus() {
        return status;
    }
}
