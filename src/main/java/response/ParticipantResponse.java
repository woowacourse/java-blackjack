package response;

import java.util.List;
import object.game.BlackJackBoard;
import object.participant.Participant;

public record ParticipantResponse(String dealerName, List<String> playerNames) {

    public static ParticipantResponse makeResponseFrom(BlackJackBoard blackJackBoard) {
        String dealerName = blackJackBoard.getDealer().getNickname();
        List<String> playerNames = blackJackBoard.getParticipants().stream().map(Participant::getNickname).toList();

        return new ParticipantResponse(dealerName, playerNames);
    }
}
