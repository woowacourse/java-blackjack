package domain.service;

import domain.model.Dealer;
import domain.model.Player;
import domain.model.Players;
import domain.vo.Result;
import domain.vo.Score;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackResultMaker {

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final Players players) {
        return IntStream.range(0, players.count())
            .mapToObj(players::get)
            .collect(
                Collectors.toMap(player -> player, player -> decide(player.getScore(), dealer.getScore()), (a, b) -> b,
                    LinkedHashMap::new));
    }

    private Result decide(final Score score, final Score comparedScore) {
        if (isVictory(score, comparedScore)) {
            return Result.victory();
        }
        if (isDraw(score, comparedScore)) {
            return Result.draw();
        }
        return Result.defeat();
    }

    private boolean isVictory(final Score score, final Score comparedScore) {
        return score.isStand() && (comparedScore.isBust() || score.getValue() > comparedScore.getValue());
    }

    private boolean isDraw(final Score score, final Score comparedScore) {
        return score.isBust() && comparedScore.isBust() || score.getValue() == comparedScore.getValue();
    }

    public Result makeDealerResult(final Dealer dealer, final Players players) {
        List<Score> scores = players.getScores();
        return decide(dealer.getScore(), scores);
    }

    private Result decide(final Score score, final List<Score> comparedScores) {
        Result result = Result.empty();
        for (Score comparedScore : comparedScores) {
            result = addResult(score, result, comparedScore);
        }
        return result;
    }

    private Result addResult(final Score score, final Result oldResult, final Score comparedScore) {
        if (isVictory(score, comparedScore)) {
            return oldResult.addVictory();
        }
        if (isDraw(score, comparedScore)) {
            return oldResult.addDraw();
        }
        return oldResult.addDefeat();
    }
}
