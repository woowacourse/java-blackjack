package blackjack.domain;

public class Dealer extends Participant {

    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int DEALER_HIT_BASED_NUMBER = 16;
    private static final int FIRST_CARD_COUNT = 2;
    public static final String DEALERNAME = "딜러";


    public Dealer() {
        super(new ParticipantName(DEALERNAME));
    }

    public int calculateDealerCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (hasAceCard() && isFirstTwoHit()) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return calculateCardNumber();
    }

    private boolean isFirstTwoHit() {
        return getCardsCount() == FIRST_CARD_COUNT;
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() <= DEALER_HIT_BASED_NUMBER;
    }
}
