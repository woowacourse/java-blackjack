package blackjack.domain;

import java.util.List;

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
}
