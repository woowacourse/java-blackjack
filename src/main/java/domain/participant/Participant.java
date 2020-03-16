package domain.participant;

import java.util.Objects;

import domain.ParticipantCards;
import domain.card.Card;
import domain.card.CardDeck;

public class Participant implements ParticipantInterface {
    private static final int INITIAL_CARD_NUMBER = 2;
    private static final int MAX_SCORE = 21;

    String name;
    ParticipantCards cards;

    Participant(String name) {
        this.name = name;
        this.cards = new ParticipantCards();
    }

    public void firstDraw(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public void receive(CardDeck cardDeck) {
        cards.add(cardDeck.draw());
    }

    void receiveForTest(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    boolean isBust() {
        return (this.calculateScore() > MAX_SCORE);
    }

    public String getName() {
        return this.name;
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
        Player player = (Player)o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
