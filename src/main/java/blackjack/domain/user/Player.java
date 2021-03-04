package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Player {
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private static final String PLAYER_WRONG_NAME_EXCEPTION_MESSAGE = "이름을 잘못 입력하였습니다. (입력값 : %s)";
    private final List<Card> cards = new ArrayList<>();
    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
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

        while (emptyAceCard(aceCount) && !belowScore(score, gameOverScore)) {
            score = Number.ACE.useSecondScore(score);
        }
        return score;
    }

    private boolean belowScore(int score, int gameOverScore) {
        return score <= gameOverScore;
    }

    private boolean emptyAceCard(int aceCount) {
        return aceCount > 0;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public String getName() {
        return name;
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }
}
