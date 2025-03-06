package blackjack.domain.gamer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.RoundResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.deck.Deck;

public abstract class Gamer {

    protected final Cards cards = new Cards();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getCardCount() {
        return cards.count();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getSumOfCards() {
        return cards.sum();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void initialize(Deck deck) {
        drawCard(deck, 2);
    }

    public void drawCard(Deck deck) {
        drawCard(deck, 1);
    }

    public void drawCard(Deck deck, int count) {
        for (int i = 0; i < count; i++) {
            cards.add(deck.draw());
        }
    }

    public Map<RoundResult, Integer> getFinalResult(List<Gamer> otherGamers) {
        return otherGamers.stream()
            .map(otherGamer -> RoundResult.judgeResult(this, otherGamer))
            .collect(Collectors.toMap(
                result -> result,
                result -> 1,
                Integer::sum));
    }

    public abstract boolean canReceiveAdditionalCards();
}
