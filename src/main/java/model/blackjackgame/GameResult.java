package model.blackjackgame;

import java.util.List;
import model.dealer.Dealer;
import model.player.Players;

public class GameResult {

    private final GameScore dealerScore;
    private final List<GameScore> playersScore;

    private GameResult(GameScore dealerScore, List<GameScore> playersScore) {
        this.dealerScore = dealerScore;
        this.playersScore = playersScore;
    }

    public static GameResult of(Dealer dealer, Players players) {
        GameScore dealerScore = GameScore.from(dealer);
        List<GameScore> playersScore = players.getPlayers()
            .stream()
            .map(GameScore::from)
            .toList();
        return new GameResult(dealerScore, playersScore);
    }

    public int findDealerScore() {
        return dealerScore.getScore();
    }

    public int findPlayerScore(String playerName) {
        return playersScore.stream()
            .filter(playerScore -> playerScore.getName().equals(playerName))
            .map(GameScore::getScore)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하는 플레이어가 없습니다."));
    }
}
