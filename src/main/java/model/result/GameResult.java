package model.result;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import model.game.CardsScore;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class GameResult {

    private final CardsScore dealerScore;
    private final Map<String, CardsScore> playerScores;

    public GameResult(CardsScore dealerScore, Map<String, CardsScore> playerScores) {
        this.dealerScore = dealerScore;
        this.playerScores = playerScores;
    }

    public static GameResult of(Dealer dealer, Players players) {
        CardsScore dealerScore = CardsScore.from(dealer.getCards());
        Map<String, CardsScore> playersScore = players.getPlayers()
            .stream()
            .collect(toMap(Player::getName, player -> CardsScore.from(player.getCards())));
        return new GameResult(dealerScore, playersScore);
    }

    public CardsScore getDealerScore() {
        return dealerScore;
    }

    public Map<String, CardsScore> getPlayerScores() {
        return playerScores;
    }
}
