package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public abstract class Participant {

    private final ParticipantName name;
    private final Cards cards;

    public Participant(String name) {
        this.name = new ParticipantName(name);
        this.cards = new Cards();
    }

    public final void draw(Card card) {
        cards.add(card);
    }

    public final int getScore() {
        return cards.calculateSumOfCards();
    }

    public final boolean isBurst() {
        return cards.isBurst();
    }

    public final boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public final String getFirstCardName() {
        return cards.getFirstCardName();
    }

    public final String getName() {
        return name.name();
    }

    public final List<String> getCardsName() {
        return cards.getCardsName();
    }

    public abstract boolean canDraw();

}
