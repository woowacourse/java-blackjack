package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Result;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private final List<Result> results;
    private static final int GET_ONE_MORE_CARD_NORM = 16;

    public Dealer(String name) {
        super(name);
        results = new ArrayList<>();
    }

    public boolean canDraw() {
        return cards.getScore() <= GET_ONE_MORE_CARD_NORM;
    }

    public void matchCards(Cards playerCards) {
        results.add(playerCards.getOtherCardsCompareResult(cards));
    }

    public int getResultCount(Result result) {
        return (int) results.stream()
            .filter(it -> it == result)
            .count();
    }

}
