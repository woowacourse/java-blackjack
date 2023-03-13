package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;

import java.util.List;

public abstract class Participant {

    private static final int INITIAL_CARDS_SIZE = 2;
    public static final int STANDARD_SUM_OF_BLACKJACK = 21;

    protected final Name name;
    protected final Cards cards;
    protected final Money money;

    protected Participant(Name name, Cards cards, Money money) {
        validateCardsSize(cards);
        this.name = name;
        this.cards = cards;
        this.money = money;
    }

    private void validateCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("초기 카드는 2장이어야 합니다.");
        }
    }

    public boolean isBlackjack() {
        return cards.calculateInitialSum() == STANDARD_SUM_OF_BLACKJACK;
    }

    public void pickCard(CardDeck cardDeck) {
        Card card = cardDeck.pickCard();
        cards.addCard(card);
    }

    public int getSumOfCards() {
        return cards.calculateSum();
    }

    public boolean isBurst() {
        return cards.calculateSum() > STANDARD_SUM_OF_BLACKJACK;
    }

    public abstract boolean canReceiveOneMoreCard();

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
