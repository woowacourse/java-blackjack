package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

abstract public class Player {

    private static final int INITIAL_CARD_SIZE = 2;
    private static final Pattern NON_SPECIAL_CHARACTERS = Pattern.compile("^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\\s]*$");

    private final String name;
    private final Cards cards;

    public Player(final String name) {
        checkNameBlank(name);
        checkNameSpecialCharacters(name);
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public Player(final String name, final List<Card> cards) {
        checkNameBlank(name);
        checkInitialCardsSize(cards);
        checkNameSpecialCharacters(name);
        this.name = name;
        this.cards = new Cards(cards);
    }

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

    private void checkInitialCardsSize(final List<Card> cards) {
        if (cards.size() != INITIAL_CARD_SIZE) {
            throw new IllegalArgumentException("[ERROR] 가장 처음에는 카드를 " + INITIAL_CARD_SIZE + "장씩 나눠줘야 합니다.");
        }
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public int getTotalScore() {
        return cards.getTotalScore();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    abstract public boolean canAddCard();
}
