package blackjack.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    public static final String RESULT_DRAW = "무";
    private static final String NAME_SPLITTER = ",";
    public static final String NEW_LINE = "\n";
    public static final String COMMA_SPACE = ", ";
    public static final String WIN = "win";
    public static final String LOSE = "lose";
    public static final String DRAW = "draw";
    public static final String RESULT_LOSE = "패";
    public static final String RESULT_WIN = "승";

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
        for (String name : value.split(NAME_SPLITTER)) {
            Player player = new Player(name);
            splitPlayers.add(player);
        }
        return splitPlayers;
    }

    public void giveCards(Deck deck) {
        dealer.receiveCard(deck.dealCard());
        for (Gamer gamer : players) {
            gamer.receiveCard(deck.dealCard());
        }
    }

    public Boolean startTurn(Deck deck) {
        int numDeck = deck.getSize();
        for (Gamer gamer : players) {
            checkDrawCard(gamer, deck);
        }
        if (dealer.canReceiveCard()) {
            dealer.continueDraw(deck);
        }
        return isUseDeck(numDeck, deck);
    }

    private Boolean isUseDeck(int numDeck, Deck deck) {
        return !(numDeck == deck.getSize());
    }

    private void checkDrawCard(Gamer gamer, Deck deck) {
        if (gamer.canReceiveCard()) {
            gamer.continueDraw(deck);
        }
    }

    public String getPlayersCards() {
        StringBuilder playerInfo = new StringBuilder();
        playerInfo.append(dealer.getInfo()).append(NEW_LINE);
        for (Gamer gamer : players) {
            playerInfo.append(gamer.getInfo()).append(NEW_LINE);
        }
        return playerInfo.toString();
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public String getPlayerNames() {
        return players.stream().map(Gamer::getName).collect(Collectors.joining(COMMA_SPACE));
    }

    public List<Gamer> getAllParticipant() {
        List<Gamer> allPlayers = new ArrayList<>();
        allPlayers.add(dealer);
        allPlayers.addAll(this.players);
        return allPlayers;
    }

    public List<Player> getAllPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    public Map<String, Integer> calculateResult() {
        Map<String, Integer> result = new HashMap<>();
        result.put(WIN, 0);
        result.put(LOSE, 0);
        result.put(DRAW, 0);
        int dealerValue = dealer.calculateJudgingPoint();
        for (Player gamer : players) {
            if (gamer.calculateJudgingPoint() > Gamer.HIGHEST_POINT) {
                gamer.matchResult(RESULT_LOSE);
                result.put(WIN, result.get(WIN) + 1);
                continue;
            }
            if (gamer.calculateJudgingPoint() > dealerValue || dealerValue > 21) {
                gamer.matchResult(RESULT_WIN);
                result.put(LOSE, result.get(LOSE) + 1);
                continue;
            }
            if (gamer.calculateJudgingPoint() < dealerValue) {
                gamer.matchResult(RESULT_LOSE);
                result.put(WIN, result.get(WIN) + 1);
                continue;
            }
            result.put(DRAW, result.get(DRAW) + 1);
            gamer.matchResult(RESULT_DRAW);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players) && Objects.equals(dealer, players1.dealer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, dealer);
    }

}
