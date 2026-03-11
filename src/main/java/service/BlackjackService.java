package service;

import domain.Game;
import domain.enums.Result;
import dto.CardDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackService {

    public Map<String, List<CardDto>> makePlayerCardDtos(Game game) {
        Map<String, List<CardDto>> playerCards = new LinkedHashMap<>();
        for (String name : game.getAllPlayerNames()) {
            playerCards.put(name, CardDto.fromCards(game.getPlayerCards(name)));
        }
        return playerCards;
    }

    public Map<String, Result> makePlayerResults(Game game) {
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (String name : game.getAllPlayerNames()) {
            playerResults.put(name, game.getPlayerResult(name));
        }
        return playerResults;
    }
}
