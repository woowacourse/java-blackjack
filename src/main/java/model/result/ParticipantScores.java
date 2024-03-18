package model.result;

import java.util.List;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Players;

public class ParticipantScores {

    private final ScoreDto dealerScore;
    private final List<ScoreDto> playerScores;

    public ParticipantScores(ScoreDto dealerScore, List<ScoreDto> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public static ParticipantScores of(Dealer dealer, Players players) {
        ScoreDto dealerScore = createScore(dealer);
        List<ScoreDto> playerScores = createPlayerScores(players);
        return new ParticipantScores(dealerScore, playerScores);
    }

    private static List<ScoreDto> createPlayerScores(Players players) {
        return players.getPlayers()
            .stream()
            .map(ParticipantScores::createScore)
            .toList();
    }

    private static ScoreDto createScore(Participant participant) {
        CardDto card = new CardDto(participant.getName(), participant.getCardsInfo());
        return new ScoreDto(card, participant.score());
    }

    public ScoreDto getDealerScore() {
        return dealerScore;
    }

    public List<ScoreDto> getPlayerScores() {
        return playerScores;
    }
}
