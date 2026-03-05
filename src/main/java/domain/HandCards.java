package domain;

import static domain.CardRank.ACE;
import static domain.CardRank.JACK;
import static domain.CardRank.KING;
import static domain.CardRank.QUEEN;
import static domain.CardRank.TEN;

import java.util.List;

public class HandCards {
    private final List<Card> handCards;

    public HandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }

    public List<Card> getHandCards() {
        return List.copyOf(handCards);
    }

    public boolean isBlackjack() {
        List<CardRank> cardRanks = handCards.stream()
                .map(Card::getCardRank)
                .toList();

        return cardRanks.contains(ACE) &&
                (cardRanks.contains(TEN) || cardRanks.contains(JACK)
                        || cardRanks.contains(QUEEN) || cardRanks.contains(KING));
    }
}