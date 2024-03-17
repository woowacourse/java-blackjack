package model.result;

import java.util.List;
import model.participant.Dealer;
import model.participant.Players;

public class ParticipantScores {

    private final ParticipantScore dealerScore;
    private final List<ParticipantScore> playerScores;

    public ParticipantScores(ParticipantScore dealerScore, List<ParticipantScore> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public static ParticipantScores of(Dealer dealer, Players players) {
        ParticipantScore dealerScore = ParticipantScore.from(dealer);
        List<ParticipantScore> playerScores = createPlayerScores(players);
        return new ParticipantScores(dealerScore, playerScores);
    }

    private static List<ParticipantScore> createPlayerScores(Players players) {
        return players.getPlayers()
            .stream()
            .map(ParticipantScore::from)
            .toList();
    }

    public ParticipantScore getDealerScore() {
        return dealerScore;
    }

    public List<ParticipantScore> getPlayerScores() {
        return playerScores;
    }
}
