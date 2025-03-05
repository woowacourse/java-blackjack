package blackjack.domain.gamer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return cards.sum() > 21;
    }

    public int getSumOfCards() {
        return cards.sum();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void initialize(Deck deck) {
        drawCard(deck);
        drawCard(deck);
    }

    public void drawCard(Deck deck) {
        cards.add(deck.draw());
    }

    public Map<RoundResult, Integer> getFinalResult(List<Gamer> otherGamers) {
        Map<RoundResult, Integer> result = new HashMap<>();
        for (var otherGamer : otherGamers) {
            RoundResult thisResult = RoundResult.judgeResult(this, otherGamer);
            result.put(thisResult, result.getOrDefault(thisResult, 0) + 1);
        }
        return result;
    }

    public abstract boolean canReceiveAdditionalCards();
}
