package response;

import java.util.List;
import object.card.Card;
import object.game.BlackJackBoard;
import object.participant.Participant;

public record BlackJackResultResponse(String nickname, List<String> cardNames, int totalScore) {

    public static BlackJackResultResponse makeResponseOf(Participant participant, BlackJackBoard blackJackBoard) {
        String participantNickname = participant.getNickname();
        List<String> ParticipantCardNames = blackJackBoard.getCardDeckOf(participant)
                .getCards().stream()
                .map(Card::getName)
                .toList();

        return new BlackJackResultResponse(participantNickname, ParticipantCardNames,
                blackJackBoard.getScoreOf(participant).getScore());
    }
}


