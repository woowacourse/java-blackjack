package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Deck deck;
    private final Participants participants;

    public Game(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
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

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}