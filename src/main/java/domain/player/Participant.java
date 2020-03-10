package domain.player;

import java.util.Objects;

import domain.ParticipantCards;
import domain.card.CardDeck;

public class Participant implements ParticipantInterface {
    public static final int INITIAL_CARD_NUMBER = 2;

    protected String name;
    protected ParticipantCards cards;

    public void firstDraw(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public String getName() {
        return this.name;
    }

    public void receive(CardDeck cardDeck) {
        cards.add(cardDeck.draw());
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public String toString() {
        return name + "카드: " + cards.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User)o;
        return Objects.equals(name, user.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
