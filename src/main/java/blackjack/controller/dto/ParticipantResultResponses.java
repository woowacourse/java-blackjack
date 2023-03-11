package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;

public class ParticipantResultResponses {

    public static List<ParticipantResultResponse> listOfParticipants(final BlackJackGame blackJackGame) {
        final List<ParticipantResultResponse> participants = new ArrayList<>();
        participants.add(ParticipantResultResponse.ofDealer(blackJackGame));
        participants.addAll(listOfPlayers(blackJackGame));
        return participants;
    }

    private static List<ParticipantResultResponse> listOfPlayers(final BlackJackGame blackJackGame) {
        final List<ParticipantResultResponse> players = new ArrayList<>();
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();
        playerNames.forEach(playerName -> players.add(ParticipantResultResponse.ofPlayer(playerName, blackJackGame)));
        return players;
    }
}
