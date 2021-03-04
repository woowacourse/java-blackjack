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

    public int getWinCount() {
        return (int) results.stream()
            .filter(it -> it == Result.WIN)
            .count();
    }

    public int getLoseCount() {
        return (int) results.stream()
            .filter(it -> it == Result.LOSE)
            .count();
    }

    public int getDrawCount() {
        return (int) results.stream()
            .filter(it -> it == Result.DRAW)
            .count();
    }
}
