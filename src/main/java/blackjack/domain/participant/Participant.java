package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.hand = Hand.from(cards);
    }

    public void drawCard(final Card card) {
        hand.add(card);
    }

    public Score getScore() {
        return hand.calculateScore();
    }

    public abstract boolean canHit();

    public abstract boolean isDealer();

    public int handSize() {
        return hand.size();
    }

    public Card getCard(final int index) {
        return hand.getCards().get(index);
    }

    public List<String> getCardNames() {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
