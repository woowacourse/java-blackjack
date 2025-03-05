package blackjack.model;

public class ResultAnalyzer {

    private final Dealer dealer;

    public ResultAnalyzer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String analyze(Participant participant) {
        if (participant.getReceivedCards().isBust()) {
            return "딜러승";
        }
        if (dealer.getReceivedCards().isBust()) {
            return "참가자승";
        }
        int dealerPoint = dealer.getReceivedCards().calculateTotalPoint();
        int participantPoint = participant.getReceivedCards().calculateTotalPoint();
        if (dealerPoint > participantPoint) {
            return "딜러승";
        }
        if (dealerPoint < participantPoint) {
            return "참가자승";
        }
        return "무승부";
    }
}
