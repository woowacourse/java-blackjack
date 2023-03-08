package model.user;

import model.card.Deck;

import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<Player> players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants from(final List<Player> players) {
        return new Participants(players, new Dealer());
    }

    public List<Score> getFinalResult() {
        return createFinalResultWithoutDealer(dealer.getTotalValue());
    }

    private List<Score> createFinalResultWithoutDealer(final int dealerTotalValue) {
        return players.stream()
                .map(player -> player.judgeResult(dealerTotalValue))
                .collect(toUnmodifiableList());
    }

    public void receiveInitialCards(final Deck deck) {
        players.forEach(player -> player.receiveInitialCards(deck));
        dealer.receiveInitialCards(deck);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
