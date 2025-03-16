package response;

import java.util.Map;
import object.game.GameResult;
import object.participant.Participant;

public record BattleResultResponse(String nickname, Map<GameResult, Integer> battleResult) {

    public static BattleResultResponse makeResponseFrom(Participant participant) {
        String participantNickname = participant.getNickname();
        Map<GameResult, Integer> battleResult = participant.getGameRecord();

        return new BattleResultResponse(participantNickname, battleResult);
    }
}
