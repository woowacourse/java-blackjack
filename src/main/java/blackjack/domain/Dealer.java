package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer implements Person {
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isHit() {
        int totalScore = calculateScore();
        return totalScore < 17;
    }

    @Override
    public int calculateScore() {
        int totalScore = cards.stream()
                .map(card -> Collections.max(card.getScore()))
                .reduce(0, Integer::sum);

        if (totalScore > 21 && hasACE()) {
            return totalScore - 10;
        }
        return totalScore;
    }

    private boolean hasACE() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumberToString().equals(CardNumber.ACE.getNumber()));
    }

    @Override
    public List<Card> showCards() {
        return List.of(cards.get(0));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
