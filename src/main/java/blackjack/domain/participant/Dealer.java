package blackjack.domain.participant;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Bank;
import blackjack.domain.Money;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.stream.IntStream;

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

    public void settingCards(final Players players) {
        List<Hand> hands = IntStream.range(0, players.size())
                .mapToObj(x -> new Hand(deck.drawTwoCard()))
                .collect(toList());

        players.distributeHands(hands);
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
