package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class AbstractPlayer implements Player {
    private static final String NAME_INPUT_ERROR_MESSAGE = "참가자의 이름으로 공백이나 빈 문자열은 입력할 수 없습니다.";

    private final String name;
    private final Cards cards;

    public AbstractPlayer(String name, Cards cards) {
        validate(name);
        this.name = name;
        this.cards = cards;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(NAME_INPUT_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean isBust() {
        return cards.calculateScore() > 21;
    }

    @Override
    public boolean isBlackjack() {
        return cards.calculateScore() == 21;
    }

    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public String getName() {
        return name;
    }
}
