package blackjack.domain.player;

import java.util.Arrays;
import java.util.List;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Drawable;
import blackjack.domain.card.HoldCards;

public class Player {

    public static final int BLACKJACK_NUMBER = 21;

    private final HoldCards holdCards;
    private final Name name;

    public Player(Name name, HoldCards holdCards) {
        this.name = name;
        this.holdCards = holdCards;
    }

    public void drawCard(Drawable drawable) {
        holdCards.add(drawable.draw());
    }

    public boolean isBust() {
        return holdCards.getTotalNumber() > BLACKJACK_NUMBER;
    }

    public boolean isBlackjack() {
        return holdCards.isInitSize() && holdCards.getTotalNumber() == BLACKJACK_NUMBER;
    }

    public int getTotalNumber() {
        return holdCards.getTotalNumber();
    }

    public Score compete(Dealer dealer) {
        return Arrays.stream(Score.values())
            .filter(score -> score.match(this, dealer))
            .findAny().orElse(Score.LOSE);
    }

    public Player copy() {
        return new Player(name, holdCards.copy());
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHoldCards() {
        return holdCards.getCards();
    }
}
