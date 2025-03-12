package model.participant;

import static constant.BlackjackConstant.BUST_NUMBER;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.Cards;

public abstract class Participant {

    private final String nickname;
    private final Cards cards;

    public Participant(String nickname) {
        this.nickname = nickname;
        this.cards = new Cards();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.calculateNearestTotal() > BUST_NUMBER;
    }

    public int score() {
        return cards.calculateNearestTotal();
    }

    public String getNickname() {
        return nickname;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public abstract boolean canReceiveCard();
}
