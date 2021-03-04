package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private List<Result> results;

    public Dealer(String name) {
        super(name);
        results = new ArrayList<>();
    }

    public boolean canDraw() {
        return cards.getScore() <= 16;
    }

    public void matchCards(Cards otherCards) {
        results.add(otherCards.compare(cards));
    }

    public int getResultCount(Result result) {
        return (int) results.stream()
            .filter(it -> it == result)
            .count();
    }

}
