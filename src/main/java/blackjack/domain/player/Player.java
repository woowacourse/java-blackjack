package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class Player {

    private static final Pattern NON_SPECIAL_CHARACTERS = Pattern.compile("^[0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣\\s]*$");

    private final String name;
    private final Cards cards;

    public Player(final String name) {
        checkNameBlank(name);
        checkNameSpecialCharacters(name);

        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    abstract public boolean canAddCard();

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
}
