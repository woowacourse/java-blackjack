package model.participant;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.Cards;

public abstract class Participant {

    public static final int PARTICIPANT_MAX_NUMBER_FOR_BUST = 21;
    private final String nickname;
    private final Cards cards;

    public Participant(String nickname) {
        this.nickname = nickname;
        this.cards = new Cards();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void receiveCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public boolean isBust() {
        return cards.calculateScore() > PARTICIPANT_MAX_NUMBER_FOR_BUST;
    }

    public int score() {
        return cards.calculateScore();
    }

    public abstract boolean canReceiveCard();

    public String getNickname() {
        return nickname;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
