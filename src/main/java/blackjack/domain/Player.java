package blackjack.domain;

public class Player extends Participant{

    private static final int JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM = 11;
    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;


    public Player(PlayerName playerName) {
        super(playerName);
    }

    @Override
    public int calculateCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (playerHasAceCard() && totalSumAceCardValueOne <= JUDGE_ACE_CARD_VALUE_ELEVEN_MAX_SUM) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return totalSumAceCardValueOne;
    }

    private int calculateCardNumberAceCardValueOne() {
        return getReceivedCards().stream()
            .mapToInt(card -> card.getCardNumber().value)
            .sum();
    }

    private boolean playerHasAceCard() {
        return getReceivedCards().stream()
            .anyMatch(card -> card.getCardNumber().equals(CardNumber.ACE));
    }
}
