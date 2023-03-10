package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.Map;

public final class ResultGame {

    private final Map<Participant, WinTieLose> playersResult;

    private ResultGame(final Map<Participant, WinTieLose> playersResult) {
        this.playersResult = playersResult;
    }

    public static ResultGame from(final Map<Participant, WinTieLose> playersResult, final Participants participants) {
        ResultGame resultGame = new ResultGame(playersResult);
        resultGame.calculateResult(participants);

        return resultGame;
    }

    public void calculateResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        final List<Participant> players = participants.getPlayers();

        final Score dealerScore = dealer.getScore();
        for (final Participant player : players) {
            playersResult.put(player, WinTieLose.resultPlayer(player.getScore(), dealerScore));
        }
    }

    public int getDealerCount(final WinTieLose expected) {
        return (int) playersResult.values()
                .stream()
                .filter(winTieLose -> winTieLose.equals(expected.reverse()))
                .count();
    }

    public WinTieLose getPlayerResult(final Participant player) {
        return playersResult.get(player);
    }
}
