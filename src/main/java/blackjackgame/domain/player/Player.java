package blackjackgame.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjackgame.domain.card.Card;

public abstract class Player {
    private static final int ACE_BONUS_SCORE = 10;
    private static final int MAX_BASIC_SCORE = 11;

    private final List<Card> cards;

    public Player() {
        this.cards = new ArrayList<>();
    }

    public abstract boolean canHit();

    public abstract String getName();

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
