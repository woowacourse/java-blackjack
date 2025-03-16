package domain;

import java.util.List;

public class Player {

    private final String name;
    private final Hand hand;
    private final Money money;
    private final Deck deck;

    Player(String name, Deck deck, Money money) {
        validate(name);
        this.name = name;
        this.deck = deck;
        this.hand = Hand.of(deck.draw(), deck.draw());
        this.money = money;
    }

    public static Player of(String name, Deck deck, Money money) {
        return new Player(name, deck, money);
    }

    private void validate(String name) {
        validateNotNull(name);
    }

    private void validateNotNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어는 이름과 손패를 가져야합니다.");
        }
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
    }

    public void addCard() {
        hand.addCard(deck.draw());
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getTotalScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void processBetting(double rate) {
        money.processBetting(rate);
    }

    public int getSeedMoney(){
        return money.getSeedMoney();
    }
    public int getEarnMoney() {
        return money.getEarnMoney();
    }
}
