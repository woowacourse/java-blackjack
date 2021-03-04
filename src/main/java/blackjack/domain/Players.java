package blackjack.domain;


import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Gamer> players;

    public Players(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Players(String value, Dealer dealer) {
        this.players = splitPlayers(value);
        this.dealer = dealer;
    }

    private List<Player> splitPlayers(String value) {
        List<Player> splitPlayers = new ArrayList<>();
        for (String name : value.split(",")) {
            Player player = new Player(name);
            splitPlayers.add(player);
        }
        return splitPlayers;
    }

    public void giveCards(Deck deck) {
        for(Gamer gamer : players) {
            gamer.receiveCard(deck.dealCard());
        }
    }

    public Boolean startTurn(Deck deck) {
        boolean continueTurn = false;
        for (Gamer gamer : players) {
            if (!gamer.canReceiveCard()) {
                continue;
            }
            if (gamer.continueDraw(deck)) {
                continueTurn = true;
            }
        }
        if (dealer.canReceiveCard()) {
            continueTurn = true;
            dealer.continueDraw(deck);
        }
        return continueTurn;
    }

    public String getPlayersCards() {
        StringBuilder playerInfo = new StringBuilder();
        playerInfo.append(dealer.getInfo()).append("\n");
        for (Gamer gamer : players) {
            playerInfo.append(gamer.getInfo()).append("\n");
        }
        return playerInfo.toString();
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
