package domain.participant;

import static domain.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected final Name name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
    }

    public abstract List<String> getInitialCards();

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    public void playTurn(Deck deck) {
        Card hitCard = deck.drawCard();
        hand.receiveCard(hitCard);
    }

    public List<String> getCardNames() {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculate();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Participant that)) {
            return false;
        }

        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
