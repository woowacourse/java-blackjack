package blackjack.model.cards;

import java.util.ArrayList;
import java.util.List;

public final class Cards {
    private final List<Card> cards;
    private Score score;

    public Cards() {
        this(List.of(), new Score(0));
    }

    private Cards(List<Card> cards, Score score) {
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }


    public void add(Card card) {
        cards.add(card);
        score = score.add(card.getScore());
    }

    public void add(List<Card> cardsToAdd) {
        cardsToAdd.forEach(this::add);
    }

    public boolean isBusted() {
        return score.isBusted();
    }

    public Score getCardsScore() {
        if (hasAce()) {
            return score.getScoreWhenHasAce();
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
