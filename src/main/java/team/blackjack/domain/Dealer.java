package team.blackjack.domain;

import team.blackjack.domain.rule.DefaultBlackjackRule;

public class Dealer implements Participant {
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Card draw(Deck deck) {
        return deck.draw();
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getScore() {
        return DefaultBlackjackRule.calculateBestScore(this.getHand().getCards());
    }

    public boolean shouldHit() {
        return DefaultBlackjackRule.isDealerMustDraw(getScore());
    }

    @Override
    public void hit(Card card) {
        this.hand.addCard(card);
    }
}
