package domain.participant;

import domain.blackjackgame.ParticipantGameResult;
import exception.BlackJackException;

public class BlackjackBet {

    private static final String INVALID_BET_MONEY = "베팅 금액은 1원부터입니다.";

    private final int money;

    public BlackjackBet(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw new BlackJackException(INVALID_BET_MONEY);
        }
    }

    public double calculateEarnMoney(BlackjackHands calculateParticipantCardSum,
                                     BlackjackHands againstParticipantCardSum) {
        int cardSum = calculateParticipantCardSum.calculateCardSum();
        ParticipantGameResult participantGameResult = ParticipantGameResult.of(cardSum,
                againstParticipantCardSum.calculateCardSum(), calculateParticipantCardSum.isBlackjack());
        return participantGameResult.calculateEarnMoney(money);
    }

    public double betMoney() {
        return money;
    }
}
