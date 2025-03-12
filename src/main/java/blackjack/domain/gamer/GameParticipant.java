package blackjack.domain.gamer;

import blackjack.domain.GameRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.GameResult;

import java.util.function.Consumer;

public abstract class GameParticipant {

    protected final Cards hand;
    private final Nickname nickname;
    private final Consumer<GameParticipant> handDisplay;

    protected GameParticipant(Nickname nickname, Cards hand, Consumer<GameParticipant> handDisplay) {
        this.nickname = nickname;
        this.hand = hand;
        this.handDisplay = handDisplay;
    }

    public abstract boolean shouldHit();

    public void drawCard(Card card) {
        hand.add(card);
    }

    public int calculateSumOfCards() {
        return hand.calculateSum();
    }

    public GameResult judgeResult(GameParticipant villain) {
        return GameResult.of(this, villain);
    }

    public boolean isBust() {
        return GameRule.isBust(hand.calculateSum());
    }

    public boolean isDealer() {
        return this instanceof Dealer;
    }

    public boolean isPlayer() {
        return this instanceof Player;
    }

    public void showHand() {
        this.handDisplay.accept(this);
    }

    public Nickname getNickname() {
        return nickname;
    }

    public Cards getHand() {
        return hand;
    }
}
