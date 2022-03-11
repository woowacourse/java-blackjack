package blackjack.domain;

import blackjack.dto.ParticipantDto;
import blackjack.dto.ScoreResultDto;
import java.util.List;
import java.util.Map;

public class GameMachine {

    public static BlackJackGame createBlackJackGame(List<String> playerNames) {
        return new BlackJackGame(playerNames);
    }

    public static DrawCommand createDrawCommand(String inputValue) {
        return DrawCommand.from(inputValue);
    }

    public static List<ParticipantDto> createPlayerFinalCardsAndScore(BlackJackGame blackJackGame) {
        return blackJackGame.getParticipantsDto();
    }

    public static ScoreResultDto createFinalScore(BlackJackGame blackJackGame) {
        Map<GameResult, Integer> dealerResult = blackJackGame.getDealerResult();
        Map<String, GameResult> playersResult = blackJackGame.getPlayersResult();
        return ScoreResultDto.from(dealerResult, playersResult);
    }
}
