package blackjack.domain.player;

import blackjack.domain.Rule;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Player {

    private static final Pattern NON_SPECIAL_CHARACTERS = Pattern.compile("^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\\s]*$");
    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final String name;
    private final Cards cards;

    public Player(final String name) {
        checkNameBlank(name);
        checkNameSpecialCharacters(name);

        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public abstract boolean canTakeCard();

    private void checkNameSpecialCharacters(String name) {
        if (!NON_SPECIAL_CHARACTERS.matcher(name).matches()) {
            throw new IllegalArgumentException("[ERROR] 이름에는 특수문자가 들어갈 수 없습니다.");
        }
    }

    private void checkNameBlank(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    public void takeCard(final Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isBust() {
        return cards.calculateTotalScore() > Rule.WINNING_SCORE.getValue();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackjack() {
        return cards.getSize() == BLACKJACK_CARDS_SIZE &&
                cards.calculateTotalScore() == Rule.WINNING_SCORE.getValue();
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
