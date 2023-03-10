package domain.participant;

import domain.card.Cards;

public class Player extends Participant {
    private static final String FORBIDDEN_NAME_MESSAGE = "\"딜러\"라는 이름은 사용하실 수 없습니다.";

    private Player(ParticipantName participantName) {
        super(participantName);
    }

    public static Player from(ParticipantName participantName) {
        validateAllowedName(participantName);
        return new Player(participantName);
    }
    private static void validateAllowedName(ParticipantName participantName) {
        ParticipantName dealerName = ParticipantName.getDealerName();
        if (participantName.equals(dealerName)) {
            throw new IllegalArgumentException(FORBIDDEN_NAME_MESSAGE);
        }
    }

    @Override
    public Cards getInitialOpeningCards() {
        return cards;
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return !isBusted();
    }

}
