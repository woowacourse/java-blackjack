package domain.card;

import domain.participant.Score;
import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void receiveInitialCards(final List<Card> initialCards) {
        if (initialCards.size() != 2) {
            throw new IllegalArgumentException("초기 카드는 2장을 받아야 합니다.");
        }
        cards.addAll(initialCards);
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }


    public boolean isBust() {
        Score score = new Score(calculateDefaultScore());
        return score.isBust();
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
