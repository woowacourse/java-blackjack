package domain.card;

import domain.participant.Score;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int INITIAL_CARDS_SIZE = 2;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void receiveInitialCards(final List<Card> initialCards) {
        validateInitialCardsSize(initialCards);
        cards.addAll(initialCards);
    }

    private static void validateInitialCardsSize(final List<Card> initialCards) {
        if (initialCards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("초기 카드는 2장을 받아야 합니다.");
        }
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public Score calculateScore() {
        boolean hasAce = hasAce();
        Score defaultScore = new Score(calculateDefaultScore());
        boolean canAddBonusScore = defaultScore.canAddBonusScore();

        if (hasAce && canAddBonusScore) {
            defaultScore = defaultScore.addBonusScore();
        }
        return defaultScore;
    }

    private int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(Card::getDefaultScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
