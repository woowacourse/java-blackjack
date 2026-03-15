package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class ParticipantCards {
    private final Cards cards;
    private int changeAvailableAceCount;

    public ParticipantCards(Cards cards) {
        this.cards = cards;
        this.changeAvailableAceCount = 0;
    }

    public List<String> getCardsInfo() {
        return cards.getCardsInfo();
    }

    public int calculateScore() {
        int sum = cards.sumScore();
        int availableAceCount = changeAvailableAceCount;
        while (availableAceCount > 0 && sum > 21) {
            sum -= 10;
            availableAceCount -= 1;
        }
        return sum;
    }

    public void addCard(Card card) {
        if (card.isAce()) {
            changeAvailableAceCount += 1;
        }
        cards.add(card);
    }

    public int getCardSize() {
        return cards.getSize();
    }
}
