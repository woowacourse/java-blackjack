package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private final List<Gamer> players;

    public Players(String value, Dealer dealer) {
        this.players = splitPlayers(value);
        players.add(dealer);
    }

    //팩토리 -> new 사용한 시점부터 메모리 할당 -> 할당실패
    //정적 static -> 메모리 할당을 안하고 접근 -> 동시에 여러개를 만들때

    private List<Gamer> splitPlayers(String value) {
        List<Gamer> splitPlayers = new ArrayList<>();
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

    public String getDealerNames() {
        return players.stream().filter(gamer -> gamer.getClass().equals(Dealer.class)).map(Gamer::getName).collect(Collectors.joining(", "));
    }

    public String getPlayerNames() {
        return players.stream().filter(gamer -> gamer.getClass().equals(Player.class)).map(Gamer::getName).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
