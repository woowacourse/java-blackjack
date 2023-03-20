package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.bank.Bank;
import domain.bank.Money;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.User;

public class Game {

    private final Deck deck;
    private final Participants participants;
    private final Bank bank;

    public Game(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
        this.bank = new Bank();
    }

    public void dealTwice() {
        for (int i = 0; i < 2; i++) {
            participants.dealFrom(deck);
        }
    }

    public void dealTo(Player player) {
        Player foundPlayer = participants.find(player);
        foundPlayer.drawFrom(deck);
    }

    public void bet(User user, Money money) {
        bank.bet(user, money);
    }

    public void evaluate() {
        for (User user : participants.getUsers()) {
            bank.evaluate(user, getResultOf(user));
        }
    }

    public Result getResultOf(User user) {
        Player foundPlayer = participants.find(user);
        Dealer dealer = participants.getDealer();
        return foundPlayer.competeWith(dealer);
    }

    public List<Result> getDealerResults() {
        Dealer dealer = participants.getDealer();
        List<User> users = participants.getUsers();
        return users.stream()
                .map(dealer::competeWith)
                .collect(Collectors.toList());
    }

    public Money getProfitOf(User user) {
        return bank.getProfitOf(user);
    }

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public int getDealerProfit() {
        return bank.getProfit().getValue();
    }
}