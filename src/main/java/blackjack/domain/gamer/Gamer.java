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

    // TODO: 연산로직을 Cards이 이동
    public boolean isBust() {
        return cards.sum() > Cards.MAX_SUM_OF_CARDS;
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

    // TODO stream 적용
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
