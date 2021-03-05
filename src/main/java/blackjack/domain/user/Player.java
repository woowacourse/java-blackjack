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

    public Player(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
        }
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore(final int gameOverScore) {
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

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }

    public boolean isGameOver(final int gameOverScore) {
        int score = calculateScore(gameOverScore);
        return (score > gameOverScore);
    }

    public void addFirstCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeAll() {
        cards.clear();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    private boolean belowScore(final int score, final int gameOverScore) {
        return score <= gameOverScore;
    }

    private boolean emptyAceCard(final int aceCount) {
        return aceCount > 0;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }
}
