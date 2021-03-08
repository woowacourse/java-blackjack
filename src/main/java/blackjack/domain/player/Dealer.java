package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.GameResult;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int GET_ONE_MORE_CARD_NORM = 16;
    private final List<GameResult> gameResults;

    public Dealer(String name) {
        super(name);
        gameResults = new ArrayList<>();
    }

    public boolean canDraw() {
        return cards.getScore() <= GET_ONE_MORE_CARD_NORM;
    }

    public int getResultCount(GameResult gameResult) {
        return (int) gameResults.stream()
            .filter(it -> it == gameResult)
            .count();
    }

    public void addGameResult(GameResult gameResult) {
        gameResults.add(gameResult);
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }
}
