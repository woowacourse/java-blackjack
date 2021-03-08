package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

import java.util.List;

public abstract class Gamer {

    protected final Hands hands = new Hands();
    private final String name;

    protected Gamer(String name) {
        this.name = name;
    }

    public abstract List<Card> showOpenHands();

    public List<Card> showHands() {
        return hands.toList();
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return hands.calculate();
    }

    public void isPossibleForPick() {
        if (hands.isSumNotInLimit()) {
            throw new IllegalArgumentException("[ERROR] 총 합이 21을 넘으므로 더 이상 뽑을 수 없습니다.");
        }
    }

    public boolean hasBlackjack() {
        return hands.isBlackjack();
    }

    public void receiveCard(Card card) {
        hands.addCard(card);
    }

    public void initHands(List<Card> makeInitialHands) {
        hands.initialize(makeInitialHands);
    }
}
