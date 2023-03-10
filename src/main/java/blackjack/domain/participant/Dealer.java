package blackjack.domain.participant;

import blackjack.domain.Bank;
import blackjack.domain.Money;
import blackjack.domain.card.Deck;

public final class Dealer extends Participant {

    private static final String name = "딜러";

    private final Bank bank;
    private final Deck deck;

    public Dealer() {
        super();
        this.bank = new Bank();
        this.deck = new Deck();
    }

    public void saveBettingMoney(final Player player, final Money money) {
        this.bank.betMoney(player, money);
    }

    public String getName() {
        return this.name;
    }

    public Bank getBank() {
        return bank;
    }

    public Deck getDeck() {
        return deck;
    }
}
