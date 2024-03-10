package blackjack.domain.participants;


import blackjack.domain.Card;
import blackjack.domain.Hands;
import java.util.ArrayList;

public class Player implements GameParticipant{

    private static final int MAX_SCORE = 21;

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
    public void hit(Card card) {
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
    public boolean canHit() {
        return calculateScore() < MAX_SCORE;
    }

    public boolean isNotOver(int boundaryScore) { // TODO delete
        return hands.calculateScore() < boundaryScore;
    }

    public boolean isWin(int dealerScore) {
        int score = hands.calculateScore();
        if (score > MAX_SCORE) {
            return false;
        }
        if (dealerScore > MAX_SCORE) {
            return true;
        }
        return dealerScore < score;
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
