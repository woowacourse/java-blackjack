package domain.participant;

import domain.card.Card;
import java.util.List;

public class ParticipantCards {
    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<String> getCardsInfo() {
        return cards.stream()
                .map(Card::getCardInfo)
                .toList();
    }

    public int calculateScore() {
        int sum = 0;
        int aceCount = 0;
        for (Card card : cards) {
            sum += card.getScore();
            if (card.isAce()) {
                aceCount++;
            }
        }
        while (aceCount > 0 && sum > 21) {
            sum -= 10;
            aceCount--;
        }
        return sum;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCardSize() {
        return cards.size();
    }
}
