package model.participants;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardType;
import model.card.Cards;

public class Participant {

    private static final int BLACKJACK_NUMBER = 21;

    protected final String name;
    protected final Cards cards;

    public Participant(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean isBlackjack() {
        return cards.calculateTotalNumbers() == BLACKJACK_NUMBER;
    }

    public boolean isBust() {
        return cards.calculateTotalNumbers() > BLACKJACK_NUMBER;
    }

    public Participant withAdditionalCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Participant(name, addedCards);
    }

    public Participant withAdditionalCards(List<Card> cardsElement) {
        Cards addedCards = cards.addAll(cardsElement);
        return new Participant(name, addedCards);
    }

    public List<CardType> captureCardType() {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < cardsSize(); i++) {
            cardTypes.add(new CardType(cards.getCards().get(i)));
        }
        return cardTypes;
    }

    public int cardsSize() {
        return cards.size();
    }

    public int totalNumber() {
        return cards.calculateTotalNumbers();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
