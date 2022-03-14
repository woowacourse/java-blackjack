package blackjack.domain.participant;

import static blackjack.domain.card.HoldingCard.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldingCard;
import java.util.List;

public abstract class Participant {
    protected final String name;
    protected final HoldingCard holdingCard;

    public Participant(String name, List<Card> cards) {
        validateEmptyName(name);
        this.name = name;
        this.holdingCard = new HoldingCard(cards);
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public void receiveCard(Card card) {
        holdingCard.add(card);
    }

    public String getName() {
        return name;
    }

    public HoldingCard getHoldingCard() {
        return holdingCard;
    }

    public boolean isBust() {
        return holdingCard.isBust();
    }

    public abstract boolean isFinished();

    public int calculateScore() {
        int score = holdingCard.computeTotalScore();
        if (score > BLACK_JACK_SCORE) {
            return 0;
        }
        return score;
    }
}
