package blackjack.domain;

import java.util.List;

public class Player {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateEmpty(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateEmpty(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(2)
                .forEach(cards::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(1)
                .forEach(cards::addCard);
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getValues();
    }
}
