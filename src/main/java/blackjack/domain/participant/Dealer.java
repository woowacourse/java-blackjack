package blackjack.domain.participant;

import blackjack.domain.Bank;
import blackjack.domain.card.Deck;

public class Dealer extends Participant {

    private static final String name = "딜러";

    private final Bank bank;
    private final Deck deck;

    public Dealer() {
        super();
        this.bank = new Bank();
        this.deck = new Deck();
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
