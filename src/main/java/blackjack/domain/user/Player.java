package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player implements User {
    protected final List<Card> cards = new ArrayList<>();
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void removeAll() {
        cards.clear();
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    public boolean isGameOver(int gameOverScore) {
        int score = calculateScore(gameOverScore);
        return (score > gameOverScore);
    }
}
