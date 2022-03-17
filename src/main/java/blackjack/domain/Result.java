package blackjack.domain;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private static final int BLACK_JACK_SCORE = 21;

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
        return Odds.getOddsRatio(isWin(player.getScore(), participants.getDealer().getScore()), isBlackJack(player));
    }

    private boolean isWin(int playerScore, int dealerScore) {
        if (playerScore > BLACK_JACK_SCORE) {
            return false;
        }
        if (dealerScore > BLACK_JACK_SCORE) {
            return true;
        }
        return dealerScore < playerScore;
    }

    private boolean isBlackJack(Participant player) {
        return player.getScore() == BLACK_JACK_SCORE;
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
