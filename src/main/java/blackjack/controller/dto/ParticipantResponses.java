package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;

public class ParticipantResponses {

    public static List<ParticipantResponse> listOfPlayer(final BlackJackGame blackJackGame) {
        final List<ParticipantResponse> players = new ArrayList<>();
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();

        playerNames.forEach(playerName -> players.add(ParticipantResponse.ofPlayer(playerName, blackJackGame)));
        return players;
    }
}
