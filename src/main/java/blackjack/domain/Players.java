package blackjack.domain;


import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private static final String NAME_SPLITTER = ",";
    public static final String COUPLER_COMMA_SPACE = ", ";

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
        OutputView.printDealerInfo(this.dealer);
        for (Gamer gamer : players) {
            OutputView.printPlayerInfo(gamer);
        }
        return playerInfo.toString();
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public String getPlayerNames() {
        return players.stream().map(Gamer::getName)
            .collect(Collectors.joining(COUPLER_COMMA_SPACE));
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

    public Map<String, Integer> judgeResult() {
        Map<String, Integer> matchResult = new HashMap<>();
        int dealerValue = dealer.getPoint();

        resultInit(matchResult);

        for (Player gamer : players) {
            gamer.judgeVictory(matchResult, dealerValue);
        }
        return matchResult;
    }

    private void resultInit(Map<String, Integer> result) {

        result.put(Result.WIN.name(), 0);
        result.put(Result.LOSE.name(), 0);
        result.put(Result.DRAW.name(), 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Players)) {
            return false;
        }
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players) &&
            Objects.equals(dealer, players1.dealer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, dealer);
    }
}
