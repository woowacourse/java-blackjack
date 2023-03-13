package domain.participant;

import domain.card.Cards;
import domain.money.BetAmount;

public class Player extends Participant {
    private static final String FORBIDDEN_NAME_MESSAGE = "\"딜러\"라는 이름은 사용하실 수 없습니다.";

    protected final BetAmount betAmount;

    private Player(ParticipantName participantName, BetAmount betAmount) {
        super(participantName);
        this.betAmount = betAmount;
    }

    public static Player of(ParticipantName participantName, BetAmount betAmount) {
        validateAllowedName(participantName);
        return new Player(participantName, betAmount);
    }
    private static void validateAllowedName(ParticipantName participantName) {
        ParticipantName dealerName = ParticipantName.getDealerName();
        if (participantName.equals(dealerName)) {
            throw new IllegalArgumentException(FORBIDDEN_NAME_MESSAGE);
        }
    }

    @Override
    public Cards getInitialOpeningCards() {
        return getCards();
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return gameState.isAbleToReceiveCard();
    }
}
