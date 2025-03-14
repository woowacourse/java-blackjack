package blackjack.participant;

import blackjack.GameRule;
import blackjack.card.Card;
import blackjack.card.Cards;

public abstract class GameParticipant {

    protected final Cards hand;
    private final Nickname nickname;

    protected GameParticipant(Nickname nickname, Cards hand) {
        this.nickname = nickname;
        this.hand = hand;
    }

    public abstract boolean shouldHit();

    public abstract void showHand();

    public void drawCard(Card card) {
        hand.add(card);
    }

    public int calculateSumOfCards() {
        return hand.calculateSum();
    }

    public boolean isBust() {
        return GameRule.isBust(hand.calculateSum());
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Cards getHand() {
        return hand;
    }
}
