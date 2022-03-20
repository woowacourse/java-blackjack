package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.Name;
import java.util.List;

public class Dealer extends Participant {

    public static final String NAME = "딜러";
    private static final int MORE_CARD_STANDARD = 16;

    private final HoldCards holdCards;

    public Dealer(HoldCards holdCards) {
        super(Name.DEALER);
        this.holdCards = holdCards;
    }

    public boolean canHit() {
        return holdCards.countBestNumber() <= MORE_CARD_STANDARD;
    }

    public void addCard(Card card) {
        this.holdCards.addCard(card);
    }

    public boolean isBust() {
        return holdCards.countBestNumber() > CardNumber.BLACK_JACK_NUMBER;
    }

    public boolean isBlackjack() {
        return holdCards.isBlackjack();
    }

    @Override
    public int getScore() {
        return holdCards.countBestNumber();
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public List<Card> getCards() {
        return holdCards.getCards();
    }
}
