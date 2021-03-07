package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    protected final List<Card> cards = new ArrayList<>();
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    protected List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean isGameOver(int gameOverScore) {
        int score = calculateScore(gameOverScore);
        return (score > gameOverScore);
    }

    public void removeAll() {    // 문제 있음
        cards.clear();
    }
}
