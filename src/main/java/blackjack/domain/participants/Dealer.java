package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.money.Money;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;

    private Cards cards;
    private Money money;

    public Dealer() {
        this.cards = new Cards();
        this.money = Money.zero();
    }

    public int addedCardCount() {
        return cards.addedCardCount();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public void draw(Deck deck) {
        cards.add(deck.pop());
    }

    // 테스트용
    public void draw(Card card) {
        cards.add(card);
    }

    @Override
    public int score() {
        return cards.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck);
        }
    }

    private boolean needsMoreCard() {
        return cards.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public int cardCount() {
        return cards.size();
    }

    @Override
    public String cards() {
        return cards.toString();
    }

    @Override
    public void bet(final String amount) {
        this.money = Money.create(amount);

    }

    @Override
    public void earn(final Money money) {
        this.money = this.money.add(money);
    }

    @Override
    public void lose(final Money money) {
        this.money = this.money.subtract(money);
    }

    @Override
    public void loseAll() {
        money = money.getOpposite();
    }

    @Override
    public Money getMoney() {
        return money;
    }
}
