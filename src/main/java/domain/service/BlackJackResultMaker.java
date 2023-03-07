package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.vo.Result;
import domain.vo.Score;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final List<Player> players) {
        return players.stream().collect(
            Collectors.toMap(player -> player, player -> decide(player.getScore(), dealer.getScore()), (a, b) -> b,
                LinkedHashMap::new));
    }

    private Result decide(final Score score, final Score comparedScore) {
        if (isVictory(score, comparedScore)) {
            return Result.of(1, 0, 0);
        }
        if (isDraw(score, comparedScore)) {
            return Result.of(0, 1, 0);
        }
        return Result.of(0, 0, 1);
    }

    private boolean isVictory(final Score score, final Score comparedScore) {
        return !score.isBust() && (comparedScore.isBust() || score.getValue() > comparedScore.getValue());
    }

    private boolean isDraw(final Score score, final Score comparedScore) {
        return score.isBust() && comparedScore.isBust() || score.getValue() == comparedScore.getValue();
    }

    public Result makeDealerResult(final Dealer dealer, final List<Player> players) {
        final List<Score> scores = players.stream().map(Player::getScore).collect(Collectors.toList());
        return decide(dealer.getScore(), scores);
    }

    private Result decide(final Score score, final List<Score> comparedScores) {
        Result result = Result.of(0, 0, 0);
        for (Score comparedScore : comparedScores) {
            result = addResult(score, result, comparedScore);
        }
        return result;
    }

    private Result addResult(final Score score, final Result oldResult, final Score comparedScore) {
        if (isVictory(score, comparedScore)) {
            return Result.of(oldResult.getVictory() + 1, oldResult.getDraw(), oldResult.getDefeat());
        }
        if (isDraw(score, comparedScore)) {
            return Result.of(oldResult.getVictory(), oldResult.getDraw() + 1, oldResult.getDefeat());
        }
        return Result.of(oldResult.getVictory(), oldResult.getDraw(), oldResult.getDefeat() + 1);
    }
}
