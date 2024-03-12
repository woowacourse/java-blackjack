package view.gameresult;

import domain.blackjack.GameResult;
import dto.PlayerGameResultDTO;

class PlayerGameResultOutputGenerator {
    static String generate(PlayerGameResultDTO playerGameResultDTO) {
        String gamerName = playerGameResultDTO.getGamerName();
        GameResult gameResult = playerGameResultDTO.getGameResult();
        ViewGameResult viewGameResult = ViewGameResult.of(gameResult);
        return "%s: %s".formatted(gamerName, viewGameResult.getOutput());
    }
}
