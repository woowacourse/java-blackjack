package domain;

import java.util.List;

public class Dealer {

    private static final int INITIAL_DEALER_MONEY = 0;
    private static final int DEALER_MINIMUM_HAND_COUNT = 17;
    private final Hand hand;
    private final Money money;
    private final Deck deck;

    Dealer(Deck deck, Money money) {
        this.hand = Hand.of(deck.draw(), deck.draw());
        this.money = money;
        this.deck = deck;
    }

    public static Dealer initiallizeDealer(Deck deck){
        return new Dealer(deck,Money.makeMoneyInt(INITIAL_DEALER_MONEY));
    }
    public TrumpCard retrieveFirstCard() {
        if (hand.getCards().size() != 2) {
            throw new IllegalStateException("딜러는 2장의 카드를 가지고 있어야 합니다.");
        }

        return hand.getCards().getFirst();
    }

    public List<TrumpCard> getCards() {
        return hand.getCards();
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

    public boolean isHitable() {
        return hand.calculateTotalScore() < DEALER_MINIMUM_HAND_COUNT;
    }

    public void processBetting(int earnMoney) {
        money.earnMoney(earnMoney);
    }

    public int getTotalMoney() {
        return money.getEarnMoney();
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (isHitable()) {
            hand.addCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }
}
