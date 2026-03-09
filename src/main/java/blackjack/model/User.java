package blackjack.model;


import java.util.List;
import java.util.regex.Pattern;

public class User {
    private static final String PLAYER_NAME_REGREX = "^[a-zA-Z가-힣]*$";

    private final String name;
    private final Hand hand;

    public User(String name) {
        validate(name, PLAYER_NAME_REGREX);
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public List<Card> cards() {
        return hand.getCards();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public GameSummary toSummary() {
        return new GameSummary(
                this,
                getScore(),
                isBust(),
                isBlackjack()
        );
    }

    private void validate(String input, String regrex) {
        validateEmpty(input);
        validateRegrex(input, regrex);
    }

    private void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백일 수 없습니다.");
        }
    }

    private void validateRegrex(String input, String regrex) {
        if (!Pattern.matches(regrex, input)) {
            throw new IllegalArgumentException("플레이어의 이름은 영어 or 한글로만 이루어질 수 있습니다.");
        }
    }
}

