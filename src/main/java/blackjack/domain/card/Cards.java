package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalSum = calculateScoreWithAceAsMinimum();
        if (hasAceCard()) {
            totalSum += 10;
            if (totalSum > 21) {
                totalSum -= 10;
            }
        }
        return totalSum;
    }

    private int calculateScoreWithAceAsMinimum() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private boolean hasAceCard() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public boolean isBlackjack() {
        return calculateScore() == 21;
    }

    public boolean isBurst() {
        return calculateScore() > 21;
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getFirstCardName() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
        }
        final Card firstCard = cards.get(0);
        return firstCard.getCardName();
    }

}
