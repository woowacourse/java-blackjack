package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Participant implements Player {

    private final String name;
    private final Cards cards;

    public Participant(final List<Card> cards, final String name) {
        validateEmpty(name);
        this.cards = new Cards(cards);
        this.name = name;
    }

    private void validateEmpty(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 이름은 비어있을 수 없습니다.");
        }
    }

    @Override
    public void addCard(final Card card) {
        cards.addCard(card);
    }

    @Override
    public int calculateScore() {
        final int scoreByAceOne = cards.calculateScoreByAceOne();
        final int scoreByAceEleven = cards.calculateScoreByAceEleven();

        if (scoreByAceEleven <= MAX_SCORE) {
            return scoreByAceEleven;
        }
        return scoreByAceOne;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }
}
