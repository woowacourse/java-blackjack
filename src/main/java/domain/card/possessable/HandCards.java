package domain.card.possessable;

import domain.BlackJackScoreManager;
import domain.card.Card;
import domain.card.providable.CardProvidable;

import java.util.ArrayList;
import java.util.List;

public class HandCards implements CardPossessable {
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::extractScore)
                .sum();
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        cards.add(cardProvidable.giveCard());
    }

    @Override
    public int calculateScore() {
        return BlackJackScoreManager.calculate(this);
    }

    @Override
    public int getCardAmount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }
}
