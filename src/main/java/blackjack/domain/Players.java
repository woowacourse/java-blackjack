package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

import static blackjack.domain.Gamer.COMMA_DELIMITER;

public class Players {
    public static final String COLON_DELIMITER = ": ";
    private final List<Player> players;
    private final Gamer dealer;

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
        for (String name : value.split(COMMA_DELIMITER)) {
            Player player = new Player(name);
            splitPlayers.add(player);
        }
        return splitPlayers;
    }

    public void giveCards() {
        dealer.receiveCard(Deck.dealCard());
        for (Gamer gamer : players) {
            gamer.receiveCard(Deck.dealCard());
        }
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

    public List<Player> getPlayers() {
        return players;
    }

    public Map<String, Integer> calculateResult() {
        Map<String, Integer> result = new HashMap<>();
        result.put("win", 0);
        result.put("lose", 0);
        result.put("draw", 0);
        int dealerValue = dealer.calculateJudgingPoint();
        for (Player gamer : players) {
            if (gamer.calculateJudgingPoint() > 21) {
                gamer.matchResult("패");
                result.put("win", result.get("win") + 1);
                continue;
            }
            if (gamer.calculateJudgingPoint() > dealerValue || dealerValue > 21) {
                gamer.matchResult("승");
                result.put("lose", result.get("lose") + 1);
                continue;
            }
            if (gamer.calculateJudgingPoint() < dealerValue) {
                gamer.matchResult("패");
                result.put("win", result.get("win") + 1);
                continue;
            }
            result.put("draw", result.get("draw") + 1);
            gamer.matchResult("무");
        }
        return result;
    }

    public void makeEachPlayerResult() {
        for (Player player : players) {
            System.out.println(player.getName() + COLON_DELIMITER + player.getResult());
        }
    }
}
