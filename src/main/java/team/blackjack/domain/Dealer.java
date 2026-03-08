package team.blackjack.domain;

import java.util.List;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class Dealer implements Participant {
    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
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

    public List<String> getAllCards(){
        return hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }
}
