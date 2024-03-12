package blackjack.domain.participants;


import blackjack.domain.card.Card;
import java.util.ArrayList;

public class Player implements GameParticipant {

    public static final int MAX_SCORE = 21;

    private final Name name;
    private final Hands hands;

    public Player(Name name) {
        this.name = name;
        this.hands = new Hands(new ArrayList<>());
    }

    public boolean isBlackjack() { // TODO 상태 계산에도 활용할 필요가 있나?? 딜러도 필요한가??
        return hands.size() == 2 && hands.calculateScore() == MAX_SCORE;
    }

    @Override
    public void receiveHands(Hands newHands) {
        this.hands.receiveHands(newHands);
    }

    @Override
    public void hit(Card card) {
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

    @Override
    public boolean isBurst() {
        return calculateScore() > MAX_SCORE;
    }

    @Override
    public int getHandsSize() {
        return hands.size();
    }

    public Name getName() {
        return name;
    }

    public Hands getHands() {
        return hands;
    }
}
