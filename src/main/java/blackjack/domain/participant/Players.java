package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;
    private final Gamer dealer;

    public Players(List<String> value, Dealer dealer) {
        this.players = createPlayers(value);
        this.dealer = dealer;
    }

    private List<Player> createPlayers(List<String> value) {
        List<Player> splitPlayers = new ArrayList<>();
        for (String name : value) {
            splitPlayers.add(new Player(name));
        }
        return splitPlayers;
    }

    public void giveCards() {
        dealer.receiveCard(Deck.dealCard());
        for (Gamer gamer : players) {
            gamer.receiveCard(Deck.dealCard());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public String getPlayerNames() {
        return players.stream().map(Gamer::getName).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players) && Objects.equals(dealer, players1.dealer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, dealer);
    }
}
