package domain;

public class RoundHistory {

    private final Hand hand;
    private final BettingResult bettingResult;

    public RoundHistory(Hand hand, BettingResult bettingResult) {
        this.hand = hand;
        this.bettingResult = bettingResult;
    }
}
