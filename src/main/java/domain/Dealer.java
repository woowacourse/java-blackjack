package domain;

import domain.generator.CardNumberGenerator;

public class Dealer extends Participant {

    private static final int MINIMUM_SUM_OF_DEALERS_CARD = 17;

    public Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer createInitialDealer(CardNumberGenerator cardNumberGenerator) {
        return new Dealer(Cards.pickInitialCards(cardNumberGenerator));
    }

    public boolean isSumUnderStandard() {
        return sumOfParticipantCards() < MINIMUM_SUM_OF_DEALERS_CARD;
    }
}
