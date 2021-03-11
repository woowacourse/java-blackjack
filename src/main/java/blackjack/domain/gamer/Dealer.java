package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Dealer extends Gamer {

    public Dealer(Hands hands) {
        this("딜러", hands);
    }

    private Dealer(String name, Hands hands) {
        super(name, hands);
    }

    public boolean checkBoundary() {
        return hands.calculate().dealerAbleToAdd();
    }

    public boolean isBusted() {
        return calculateScore().isBusted();
    }

    public boolean isMaxScore() {
        return calculateScore().isMaxScore();
    }

    public boolean isUnderMaxScore() {
        return calculateScore().isUnderMaxScore();
    }

    public int getScore() {
        return calculateScore().getValue();
    }

    @Override
    public List<Card> showOpenHands() {
        return hands.cardsOf(1);
    }
}
