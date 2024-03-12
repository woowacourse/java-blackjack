package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;
import java.util.List;

public class Player {
    private static final String INVALID_NAME_LENGTH = "참여자 이름은 한 글자 이상이다";
    private static final int BLACKJACK_CONDITION = 21;

    private final String name;
    private final Cards cards;

    public Player(final String name, final CardGenerator cardGenerator) {
        validateName(name);
        this.name = name;
        this.cards = new Cards(cardGenerator);
    }

    private void validateName(final String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public int calculateCardsTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean canDraw() {
        int cardsTotal = cards.calculateTotalScore();
        return cardsTotal <= BLACKJACK_CONDITION;
    }

    public void draw(final CardGenerator cardGenerator) {
        cards.addCard(cardGenerator);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }
}
