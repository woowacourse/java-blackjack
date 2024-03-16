package dto;

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
        List<ParticipantScore> playerScores = players.getPlayers()
            .stream()
            .map(ParticipantScore::from)
            .toList();
        return new ParticipantScores(dealerScore, playerScores);
    }

    public ParticipantScore getDealerScore() {
        return dealerScore;
    }

    public List<ParticipantScore> getPlayerScores() {
        return playerScores;
    }
}
