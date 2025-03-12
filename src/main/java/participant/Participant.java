package participant;

import card.Card;
import card.Cards;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BUST_NUMBER = 21;

    private final String nickname;
    private final Cards cards;
    private final int betAmount;

    public Participant(String nickname, int betAmount) {
        this.nickname = nickname;
        this.cards = new Cards();
        this.betAmount = betAmount;
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
