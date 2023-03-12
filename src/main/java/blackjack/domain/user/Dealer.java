package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Result;
import blackjack.domain.game.Score;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Dealer extends User {

    private static final int INIT_CARD_SHOW_LIMIT = 1;
    private static final Score minScore = new Score(16);

    public Dealer() {
        super(DEALER_NAME);
    }

    public List<Card> getDealerOneCards() {
        return getCards()
                .stream()
                .limit(INIT_CARD_SHOW_LIMIT)
                .collect(Collectors.toList());
    }

    public boolean isSatisfiedMinScore() {
        return !minScore.isMoreThen(getScore());
    }

    public Map<User, Result> getResult(final Players players) {
        final Map<User, Result> gameResult = initResult(players);

        for (Player player : players.getPlayers()) {
            updateResult(gameResult, player);
        }

        return gameResult;
    }

    private void updateResult(final Map<User, Result> gameResult, final Player targetPlayer) {
        updateMatchingResult(player -> player.getScore().isBust() && isBust(), gameResult, targetPlayer,
                GameResult.LOSE, GameResult.LOSE);
        updateMatchingResult(player -> !player.isBust() && player.getScore().equals(getScore()), gameResult,
                targetPlayer,
                GameResult.DRAW, GameResult.DRAW);
        updateMatchingResult(player -> player.getScore().isMoreThen(getScore()), gameResult, targetPlayer,
                GameResult.WIN, GameResult.LOSE);
        updateMatchingResult(player -> !player.getScore().isMoreThen(getScore()), gameResult, targetPlayer,
                GameResult.LOSE, GameResult.WIN);
    }

    private void updateMatchingResult(final Predicate<Player> predicate, final Map<User, Result> gameResult,
                                      final Player player, final GameResult playerResult,
                                      final GameResult dealerResult) {
        if (predicate.test(player)) {
            gameResult.put(player, gameResult.get(player).updateResult(playerResult));
            gameResult.put(this, gameResult.get(this).updateResult(dealerResult));
        }
    }

    private Map<User, Result> initResult(final Players players) {
        final Map<User, Result> gameResult = new LinkedHashMap<>();
        gameResult.put(this, new Result());

        for (Player player : players.getPlayers()) {
            gameResult.put(player, new Result());
        }

        return gameResult;
    }
}
