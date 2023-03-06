package domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final List<Player> players) {
        return players.stream()
            .collect(Collectors.toMap(player -> player,
                player -> decide(player.getScore(), dealer.getScore()), (a, b) -> b, LinkedHashMap::new));
    }

    private Result decide(final Score score, final Score comparedScore) {
        if (isVictory(score, comparedScore)) {
            return new Result(1, 0, 0);
        }
        if (isDraw(score, comparedScore)) {
            return new Result(0, 1, 0);
        }
        return new Result(0, 0, 1);
    }

    private boolean isVictory(final Score score, final Score comparedScore) {
        return !score.isBust()
            && (comparedScore.isBust() || score.getValue() > comparedScore.getValue());
    }

    private boolean isDraw(final Score score, final Score comparedScore) {
        return score.isBust()
            && comparedScore.isBust() || score.getValue() == comparedScore.getValue();
    }

    public Result makeDealerResult(final Dealer dealer, final List<Player> players) {
        final List<Score> scores = players.stream()
            .map(Player::getScore)
            .collect(Collectors.toList());
        return decide(dealer.getScore(), scores);
    }

    private Result decide(final Score score, final List<Score> comparedScores) {
        Result result = new Result(0, 0, 0);
        comparedScores.forEach(comparedScore -> addResult(score, comparedScore, result));
        return result;
    }

    private void addResult(final Score score, final Score comparedScore, final Result result) {
        if (isDraw(score, comparedScore)) {
            result.addDraw();
            return;
        }
        if (isVictory(score, comparedScore)) {
            result.addVictory();
            return;
        }
        result.addDefeat();
    }
}
