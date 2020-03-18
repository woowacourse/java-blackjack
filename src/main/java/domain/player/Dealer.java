package domain.player;

import domain.money.Money;
import domain.card.Card;
import domain.card.CardCalculator;

import java.util.List;

public class Dealer extends User {
    private static final int ADDITIONAL_INSERT_CARD_STANDARD = 16;

    public Dealer(List<Card> userCardDeck) {
        super(userCardDeck);
        this.name = "딜러";
        this.money = new Money(0);
    }

    public boolean isAdditionalCard(Card card) {
        if (isUnderSixteen()) {
            drawCard(card);
            return true;
        }
        return false;
    }

    @Override
    public void drawCard(Card card) {
        if (CardCalculator.sumCardDeck(this.cards) <= ADDITIONAL_INSERT_CARD_STANDARD) {
            this.cards.add(card);
        }
        validateDuplicateCard();
    }
}
