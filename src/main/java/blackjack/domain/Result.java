package blackjack.domain;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Participant, Integer> playersRevenue;
    private final Participants participants;

    public Result(Participants participants) {
        this.participants = participants;
        this.playersRevenue = new LinkedHashMap<>();
    }

    public void calculatePlayersRevenue() {
        participants.getPlayers()
            .forEach(player -> playersRevenue.put(player, calculatePlayerRevenue(player)));
    }

    private int calculatePlayerRevenue(Participant player) {
        return (int) (calculateOdds(player) * player.getBetting());
    }

    private double calculateOdds(Participant player) {
        return Odds.getOddsRatio(player, participants.getDealer());
    }

    public int getDealerRevenue() {
        return playersRevenue.values()
            .stream()
            .mapToInt(i -> i)
            .sum() * -1;
    }

    public int getPlayerRevenue(Participant player) {
        return playersRevenue.get(player);
    }
}
