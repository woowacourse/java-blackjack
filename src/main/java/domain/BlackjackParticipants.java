package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackParticipants {

    private final Dealer dealer;
    private final Players players;

    public BlackjackParticipants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public BlackjackParticipants() {
        this(Dealer.createEmpty(), new Players());
    }

    public void registerPlayers(List<Player> players) {
        this.players.addAll(players);
    }

    public Player findPlayerByName(String playerName) {
        return players.findPlayerByName(playerName);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> findAllPlayerNames() {
        return players.getAllPlayersName();
    }

    public ProfitResults calculateProfitResults() {
        return players.calculateProfitResults(dealer.getHand());
    }
}
