package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Player implements User {
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private static final String PLAYER_WRONG_NAME_EXCEPTION_MESSAGE = "이름을 잘못 입력하였습니다. (입력값 : %s)";

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
        }
    }

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
        return (score > gameOverScore);
    }
}
