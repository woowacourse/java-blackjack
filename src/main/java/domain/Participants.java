package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/07
 */
public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(final List<Participant> participants) {
        this.dealer = (Dealer) participants.get(0);
        this.players = convertPlayers(participants);
    }

    private Players convertPlayers(final List<Participant> participants) {
        List<Player> players = IntStream.range(1, participants.size())
                .mapToObj(i -> (Player) participants.get(i))
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<String> getPlayerNames() {
        return players.getNames();
    }

    public List<Cards> getPlayerCards() {
        return players.getCardss();
    }

    public Players getPlayers() {
        return players;
    }

    public List<Integer> getWinningResult() {
        List<Integer> winningResult = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.getPlayer(index)));
        }
        return winningResult;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayersToList() {
        return players.getPlayers();
    }
}
