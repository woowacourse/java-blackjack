package first.domain.card.possessable;

import first.domain.card.Card;
import first.domain.card.providable.CardProvidable;
import first.domain.score.BlackJackScoreManager;
import first.domain.score.Calculatable;
import first.domain.score.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandCards implements CardPossessable {
    private static final String DELIMITER = ", ";
    private static final int FIRST_INDEX = 0;

    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
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
    public Calculatable calculateDefaultSum() {
        return new Score(cards.stream()
                .mapToInt(Card::extractScore)
                .sum());
    }

    @Override
    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public Card getOneCard() {
        return cards.get(FIRST_INDEX);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
