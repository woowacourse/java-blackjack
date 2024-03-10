package blackjack.domain.participants;


import blackjack.domain.Card;
import blackjack.domain.Hands;
import java.util.ArrayList;

public class Player implements GameParticipant{

    private static final int MAX_RECEIVE_SCORE = 21;

    private final Name name;
    private Hands hands;

    public Player(Name name) {
        this.name = name;
    }

    @Override
    public void receiveHands(Hands hands) {
        this.hands = hands;
    }

    @Override
    public void receiveCard(Card card) {
        if (hands == null) {
            hands = new Hands(new ArrayList<>());
        }
        hands.addCard(card);
    }

    @Override
    public int calculateScore() {
        return hands.calculateScore();
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() < MAX_RECEIVE_SCORE;
    }

    public boolean isNotOver(int boundaryScore) { // TODO delete
        return hands.calculateScore() < boundaryScore;
    }

    public boolean isWin(int dealerScore) {
        return hands.calculateScore() > dealerScore;
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
