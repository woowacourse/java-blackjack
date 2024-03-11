package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;

import java.util.List;

public class Player {
    private static final String INVALID_NAME_LENGTH = "참여자 이름은 한 글자 이상이다";
    private static final String INVALID_PLAYER_NAME = "딜러";

    private final String name;
    private final Cards cards;

    public Player(final String name, final CardGenerator cardGenerator) {
        validateName(name);
        this.name = name;
        this.cards = new Cards(cardGenerator);
    }

    private void validateName(final String name) {
        if (name.isEmpty() || name.equals(INVALID_PLAYER_NAME)) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public int calculateCardsTotalScore() {
        return cards.calculateCardsTotalScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public boolean canHit() {
        return !cards.isBust();
    }

    public void hit(final CardGenerator cardGenerator) {
        cards.addCard(cardGenerator);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }
}
