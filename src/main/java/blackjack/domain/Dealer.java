package blackjack.domain;

public class Dealer extends Participant{

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int  DEALER_HIT_BASED_NUMBER= 16;

    public Dealer(ParticipantName participantName) {
        super(participantName);
    }

    @Override
    int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (participantHasAceCard() && getReceivedCards().size() == 2) {
            return totalSumAceCardValueOne + 10;
        }
        if (participantHasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    private int totalSum() {
        return getReceivedCards().stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }
}
