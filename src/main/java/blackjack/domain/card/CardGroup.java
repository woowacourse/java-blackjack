package blackjack.domain.card;

import blackjack.domain.result.Score;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {

    private final List<Card> cards = new ArrayList<>();

    public CardGroup(final Card firstCard, final Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public void add(final Card newCard) {
        cards.add(newCard);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Score getScore() {
        int scoreValue = getTotalValue();
        final int aceCount = getAceCount();

        for (int tryCount = 0; tryCount < aceCount && scoreValue > Score.BLACKJACK_NUMBER; tryCount++) {
            scoreValue += Score.ACE_OFFSET;
        }

        return new Score(scoreValue, cards.size());
    }

    private int getTotalValue() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE)
                .count();
    }
}
