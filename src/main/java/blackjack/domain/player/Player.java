package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class Player {

    protected Cards cards;
    protected String name;

    public Player(String name) {
        validateEmpty(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    public abstract boolean acceptableCard();

    public int calculateFinalScore() {
        return cards.calculateFinalScore();
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public String getName() {
        return this.name;
    }

}
