package team.blackjack.domain;

import java.util.List;
import team.blackjack.config.AppConfig;
import team.blackjack.domain.rule.BlackjackRule;

public class Dealer implements Participant {
    private final BlackjackRule blackjackRule;
    private final Hand hand;

    public Dealer() {
        this.blackjackRule = AppConfig.getInstance().blackjackRule();
        this.hand = new Hand();
    }

    public Hand getHand() {
        return this.hand;
    }

    @Override
    public int getScore() {
        return blackjackRule.calculateBestScore(this.getHand().getCards());
    }

    public boolean shouldHit() {
        return blackjackRule.isDealerMustDraw(getScore());
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
