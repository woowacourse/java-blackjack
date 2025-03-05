package blackjack.model;

public enum ParticipantResult {
    WIN(),
    LOSE(),
    DRAW(),
    ;

    public static ParticipantResult of(Dealer dealer, Participant participant) {
        if (participant.isBust()) {
            return ParticipantResult.LOSE;
        }
        if (dealer.isBust()) {
            return ParticipantResult.WIN;
        }
        int dealerPoint = dealer.calculatePoint();
        int participantPoint = participant.calculatePoint();
        if (dealerPoint > participantPoint) {
            return ParticipantResult.LOSE;
        }
        if (dealerPoint < participantPoint) {
            return ParticipantResult.WIN;
        }
        return ParticipantResult.DRAW;
    }



}
