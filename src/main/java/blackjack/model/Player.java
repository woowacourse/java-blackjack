package blackjack.model;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    public Player(Name name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public String getName() {
        return name.get();
    }

    public BlackjackResult calculateResult(Hand dealerHand) {
        if (hand.isBust()) {
            return BlackjackResult.LOSE;
        }
        if (dealerHand.isBust()) {
            return BlackjackResult.WIN;
        }

        int playerScore = hand.calculateScore();
        int dealerScore = dealerHand.calculateScore();
        if (playerScore > dealerScore) {
            return BlackjackResult.WIN;
        }
        if (playerScore == dealerScore) {
            return BlackjackResult.PUSH;
        }

        return BlackjackResult.LOSE;
    }
}
