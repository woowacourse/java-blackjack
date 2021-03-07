package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements User {
    public static final int TURN_OVER_COUNT = 16;

    private final String name = "딜러";
    private final List<Card> cards = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean isGameOver(final int gameOverScore) {
        int score = getScore();
        return (score > TURN_OVER_COUNT || score > gameOverScore);
    }
}
