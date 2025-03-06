package blackjack.model;

public enum ParticipantResult {
    WIN,
    LOSE,
    DRAW,
    ;

    public static ParticipantResult of(Dealer dealer, Participant participant) {
        if (participant.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();

        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        if (dealerPoint < participantPoint) {
            return WIN;
        }
        return DRAW;
    }



}
