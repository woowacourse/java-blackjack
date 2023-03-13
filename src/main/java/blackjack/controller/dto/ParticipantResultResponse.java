package blackjack.controller.dto;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.PlayerName;
import java.util.ArrayList;
import java.util.List;

public class ParticipantResultResponse {

    private final String name;
    private final int profit;

    private ParticipantResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static ParticipantResultResponse ofPlayer(final PlayerName playerName, final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(playerName.getValue(), blackJackGame.getPlayerProfit(playerName));
    }

    public static ParticipantResultResponse ofDealer(final BlackJackGame blackJackGame) {
        return new ParticipantResultResponse(blackJackGame.getDealerName(), blackJackGame.getDealerProfit());
    }

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

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
