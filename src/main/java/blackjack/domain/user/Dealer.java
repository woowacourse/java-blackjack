package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.Result;
import blackjack.domain.game.Score;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Dealer extends User {

    private static final String NAME = "딜러";
    private static final int INIT_CARD_SHOW_LIMIT = 1;
    private static final Score MIN_SCORE = new Score(16);

    public Dealer() {
        super(NAME);
    }

    public List<Card> getDealerOneCards() {
        return getCards()
                .stream()
                .limit(INIT_CARD_SHOW_LIMIT)
                .collect(Collectors.toList());
    }

    public boolean isSatisfiedMinScore() {
        return !MIN_SCORE.isMoreThen(getScore());
    }

    public Map<User, Result> getResult(final Players players) {
        final Map<User, Result> gameResult = initResult(players);

        for (Player player : players.getPlayers()) {
            updateResult(gameResult, player);
        }

        return gameResult;
    }

    private void updateResult(final Map<User, Result> gameResult, final Player targetPlayer) {
        gameResult.put(targetPlayer, gameResult.get(targetPlayer).updateResult(targetPlayer.getResultByUser(this)));
        gameResult.put(this, gameResult.get(targetPlayer).updateResult(this.getResultByUser(targetPlayer)));
    }

    private Map<User, Result> initResult(final Players players) {
        final Map<User, Result> gameResult = new LinkedHashMap<>();
        gameResult.put(this, new Result());

        for (Player player : players.getPlayers()) {
            gameResult.put(player, new Result());
        }

        return gameResult;
    }

    public static String getDealerName() {
        return NAME;
    }
}
