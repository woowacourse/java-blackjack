package model.user;

import model.card.Deck;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Participants {

    private final List<Player> players;
    private final Dealer dealer;

    private Participants(final List<String> playersName, final Dealer dealer) {
        this.players = createPlayers(playersName);
        this.dealer = dealer;
    }

    private static List<Player> createPlayers(List<String> playersName) {
        return playersName.stream()
                .map(Player::new)
                .collect(toList());
    }

    public static Participants from(final List<String> playersName) {
        return new Participants(playersName, new Dealer());
    }

    public List<Result> getFinalResult(final Dealer dealer) {
        return createFinalResultWithoutDealer(dealer.calculateTotalValue());
    }

    private List<Result> createFinalResultWithoutDealer(final int dealerTotalValue) {
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
