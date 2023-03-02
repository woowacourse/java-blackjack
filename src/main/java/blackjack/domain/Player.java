package blackjack.domain;

public class Player extends Participant{

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int BLACKJACK_MAX_NUMBER = 21;


    public Player(ParticipantName participantName) {
        super(participantName);
    }

    @Override
    public int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (participantHasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    @Override
    boolean decideHit() {
        return calculateCardNumber() < BLACKJACK_MAX_NUMBER;
    }
}
