package participant;

import card.Card;
import card.Cards;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_NUMBER = 21;

    private final String nickname;
    private final int betAmount;
    private final Cards cards;

    public Participant(String nickname, int betAmount) {
        this.nickname = nickname;
        this.betAmount = betAmount;
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
