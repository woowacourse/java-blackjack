package domain;

import java.util.List;

public class Player {

    public static final int STANDARD_SUM_OF_BLACKJACK = 21;
    Name name;
    Cards cards;
    Money money;

    public Player(Name name, Cards cards, Money money) {
        this.name = name;
        this.cards = cards;
        this.money = money;
    }

    public boolean isBlackjack() {
        return cards.calculateSum() == STANDARD_SUM_OF_BLACKJACK;
    }

    public boolean canReceiveOneMoreCard() {
        return cards.calculateSum() < STANDARD_SUM_OF_BLACKJACK;
    }

    public void pickCard(CardDeck cardDeck) {
        Card card = cardDeck.pickCard();
        cards.addCard(card);
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    //
//    public static final int MAX_NAME_LENGTH = 5;
//
//    public Player(String name, Cards cards) {
//        super(name, cards);
//        validateNameLength(name);
//    }
//
//    private void validateNameLength(String name) {
//        if (name.length() > MAX_NAME_LENGTH) {
//            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
//        }
//    }
//
//    @Override
//    public boolean canAddCard() {
//        return cards.getScore() < Cards.BLACKJACK_NUMBER;
//    }
}
