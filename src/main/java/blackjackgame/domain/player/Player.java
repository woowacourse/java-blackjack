package blackjackgame.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjackgame.domain.card.Card;

public abstract class Player {
    public static final int FIRST_CARDS_COUNT = 2;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int MAX_BASIC_SCORE = 11;

    private final List<Card> cards;

    public Player(List<Card> cards) {
        validateFirstCards(cards);
        this.cards = cards;
    }

    private void validateFirstCards(List<Card> cards) {
        if (cards.size() != FIRST_CARDS_COUNT) {
            throw new IllegalArgumentException("시작시 카드는 두장만 분배되어야 합니다.");
        }
    }

    protected abstract boolean canHit();

    protected abstract String getName();

    public final int getScore() {
        int totalScore = cards.stream()
            .mapToInt(Card::getScore)
            .sum();
        boolean hasAce = cards.stream()
            .anyMatch(Card::isAce);

        if (hasAce && totalScore <= MAX_BASIC_SCORE) {
            totalScore += ACE_BONUS_SCORE;
        }
        return totalScore;
    }

    public final void addCard(final Card card) {
        cards.add(card);
    }

    public final int getSize() {
        return cards.size();
    }

    public final List<List<String>> getCards() {
        List<List<String>> playerCards = new ArrayList<>();
        for (final Card card : cards) {
            List<String> playerCard = new ArrayList<>();
            playerCard.add(card.getValue());
            playerCard.add(card.getSymbol());
            playerCards.add(playerCard);
        }
        return Collections.unmodifiableList(playerCards);
    }
}
