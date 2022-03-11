package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.blackjack.Score;
import blackjack.model.cards.Cards;
import blackjack.model.cards.ScoreCards;

public class Player {

    public static final Score HIT_BOUNDARY = new Score(21);

    protected final ScoreCards cards;
    private final Cards openCards;
    private final String name;

    public Player(String name, Card card1, Card card2, Card... cards) {
        this(name, Cards.of(card1, card2), Cards.of(card1, card2, cards));
    }

    protected Player(String name, Cards openCards, Cards cards) {
        this.name = name;
        this.openCards = openCards;
        this.cards = Cards.bestScoreCards(cards);
    }

    public String name() {
        return name;
    }

    public Cards openCards() {
        return Cards.toUnmodifiable(openCards);
    }

    public Cards cards() {
        return Cards.toUnmodifiable(cards);
    }

    public void take(Card card) {
        if (!isHittable()) {
            throw new IllegalStateException("카드를 더 이상 발급 받을 수 없습니다.");
        }
        cards.take(card);
    }

    public Score score() {
        return cards.score();
    }

    public boolean isBust() {
        return score().isBust();
    }

    public boolean isHittable() {
        return cards.lessThan(HIT_BOUNDARY);
    }
}
