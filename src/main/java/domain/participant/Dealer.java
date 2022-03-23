package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;
import utils.ExceptionMessages;

public final class Dealer extends Participant {

    private static final int CONVERT_POSITIVE = -1;
    private static final int MAX_CARD_SUM = 16;
    private static final int FIRST_CARD_INDEX = 0;
    private static final String NAME = "딜러";

    public Dealer() {
        super(Name.from(NAME));
    }

    public int calculateIncome(List<Integer> playerIncomes) {
        int playerIncomeSum = playerIncomes.stream()
            .mapToInt(playerIncome -> playerIncome)
            .sum();
        return playerIncomeSum * CONVERT_POSITIVE;
    }

    public Card getFirstCard() {
        return cards.getCardByIndex(FIRST_CARD_INDEX);
    }

    @Override
    public void hit(Deck deck) {
        if (!canHit()) {
            throw new IllegalStateException(ExceptionMessages.DEALER_CAN_NOT_HIT_ERROR);
        }
        cards.addCard(deck.handOut());
    }

    @Override
    public boolean canHit() {
        return !isBlackJack() && cards.calculateSum() <= MAX_CARD_SUM;
    }
}
