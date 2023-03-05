package domain;

import java.util.List;

public class Game {

    private final Deck deck;
    private final Participants participants;

    public Game(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public void dealCardsTwice() {
        for (int i = 0; i < 2; i++) {
            participants.dealCardsFrom(deck);
        }
    }

    public void dealCardTo(User user) {
        User foundUser = participants.find(user);
        foundUser.addCard(deck.draw());
    }

    public boolean dealCardToDealer() {
        Dealer dealer = participants.getDealer();
        if (dealer.canHit()) {
            dealer.addCard(deck.draw());
            return true;
        }
        return false;
    }

    public Result getResultOf(User user) {
        User foundUser = participants.find(user);
        Dealer dealer = participants.getDealer();
        return foundUser.competeWith(dealer);
    }

    public List<Result> getDealerResults() {
        return participants.getDealerResults();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public List<User> getUsers() {
        return participants.getUsers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
