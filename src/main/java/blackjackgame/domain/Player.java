package blackjackgame.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    List<Card> cards;

    public int getScore() {
        int totalScore = 0;
        boolean hasAce = false;
        for (Card card : cards) {
            hasAce |= (card.getValue().equals(CardValue.ACE.getValue()));
            totalScore += card.getScore();
        }

        if (hasAce && totalScore <= 11) {
            totalScore += 10;
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    public List<List<String>> getCards() {
        List<List<String>> playerCards = new ArrayList<>();
        for (Card card : cards) {
            List<String> playerCard = new ArrayList<>();
            playerCard.add(card.getValue());
            playerCard.add(card.getSymbol());
            playerCards.add(playerCard);
        }
        return playerCards;
    }

    public abstract boolean isPick();

    public abstract String getName();
}
