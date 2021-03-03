package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Player {
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format("이름을 잘못 입력하였습니다. (입력값 : %s)", name));
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addFirstCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeAll() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isGameOver(int gameOverScore) {
        int score = calculateScore(gameOverScore);
        return (score > gameOverScore);
    }

    public int calculateScore(int gameOverScore) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = 0;
        if (score > gameOverScore) {
            aceCount = findAceCount();
        }

        for (int i = 0; i < aceCount; i++) {
            score = score - 11;
            score = score + 1;
            if (score <= gameOverScore) {
                break;
            }
        }
        return score;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public String getName() {
        return name;
    }
}
