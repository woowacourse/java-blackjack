package blackjack.domain;

public class Dealer extends Participant {

    private static final int CALIBRATED_ACE_CARD_ELEVEN_VALUE = 10;
    private static final int DEALER_HIT_BASED_NUMBER = 16;
    private static final int FIRST_CARD_COUNT = 2;


    public Dealer(ParticipantName participantName) {
        super(participantName);
    }

    public int calculateDealerCardNumber() {
        int totalSumAceCardValueOne = calculateCardNumberAceCardValueOne();
        if (hasAceCard() && getReceivedCards().size() == FIRST_CARD_COUNT) {
            return totalSumAceCardValueOne + CALIBRATED_ACE_CARD_ELEVEN_VALUE;
        }
        return calculateCardNumber();
    }

    public WinningResult judgeWinOrLose(final Player player) {
        int myValue = calculateDealerCardNumber();
        int otherPlayer = player.calculateCardNumber();
        return getWinningResult(player, myValue, otherPlayer);
    }

    private WinningResult getWinningResult(final Player player, final int myValue, final int playerValue) {
        if (playerValue > WinningResult.WIN_MAX_NUMBER || myValue > WinningResult.WIN_MAX_NUMBER) {
            return WinningResult.calculateByBurst(playerValue);
        }
        if (judgeBlackjack() || player.judgeBlackjack()) {
            return WinningResult.calculateByBlackjack(player.judgeBlackjack(), judgeBlackjack());
        }
        return WinningResult.calculateByNumber(playerValue, myValue);
    }

    @Override
    public boolean decideHit() {
        return calculateCardNumber() <= DEALER_HIT_BASED_NUMBER;
    }
}
