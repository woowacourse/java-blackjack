package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Status;
import java.util.stream.IntStream;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;
    private static final int BLACKJACK_STANDARD = 21;

    private final Cards cards;
    private final Name name;
    private final Money money;
    private Status status;

    public Participant(String name) {
        this.cards = new Cards();
        this.name = new Name(name);
        this.status = Status.HIT;
        this.money = new Money();
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
    }

    public void initCards(Deck deck) {
        IntStream.range(0, INITIAL_CARD_COUNT)
                .mapToObj(i -> deck.drawCard())
                .forEach(cards::add);
    }

    public boolean isHit() {
        return status == Status.HIT;
    }

    public int getScore() {
        return cards.sumValue();
    }

    public void hit(Deck deck) {
        cards.add(deck.drawCard());

        if (cards.isBust()) {
            status = Status.BUST;
        }
    }

    public void stay() {
        status = Status.STAY;
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name.getValue();
    }

    public boolean isBlackjack() {
        return cards.sumValue() == BLACKJACK_STANDARD && cards.getValue().size() == 2;
    }

    public void updateBlackjackPrize() {
        money.multiply();
    }

    public void updatePushPrize() {
        money.toZero();
    }

    public void updateNegativePrize() {
        money.toNegative();
    }

    public int getPrize() {
        return money.getValue();
    }
}
