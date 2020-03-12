package domain.card.possessable;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.score.BlackJackScoreManager;
import domain.score.Calculatable;
import domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCards implements CardPossessable {
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Calculatable calculateDefaultSum() {
        return new Score(cards.stream()
                .mapToInt(Card::extractScore)
                .sum());
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        cards.add(cardProvidable.giveCard());
    }

    @Override
    public Calculatable calculateScore() {
        return BlackJackScoreManager.calculate(this);
    }

    @Override
    public int getCardAmount() {
        return cards.size();
    }

    @Override
    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public Card getOneCard() {
        return cards.get(0);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(", "));
    }
}
