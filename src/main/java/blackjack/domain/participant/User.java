package blackjack.domain.participant;


import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.regex.Pattern;

public class User {
    private static final Pattern PLAYER_NAME_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+$");

    private final String name;
    private final Hand hand;

    public User(String name) {
        validate(name);
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

    private void validate(String input) {
        validateBlank(input);
        validatePattern(input);
    }

    private void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백일 수 없습니다.");
        }
    }

    private void validatePattern(String input) {
        if (!PLAYER_NAME_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("플레이어의 이름은 영어 or 한글로만 이루어질 수 있습니다.");
        }
    }

}

