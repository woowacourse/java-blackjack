package domain;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private static final int BLACKJACK_SCORE = 21;

    private final Participant participant;

    public GameRule(final Participant participant) {
        this.participant = participant;
    }

    public List<Boolean> judge() {
        if (isBusted(participant.dealer())) {
            return judgePlayersIfDealerBusted();
        }
        boolean isDealerBlackJack = isBlackJack(participant.dealer());
        List<Boolean> gameResult = new ArrayList<>();
        if (isDealerBlackJack) {
            return getGameResultIfDealerIsBlackJack(gameResult);
        }
        for (Player player : participant.players()) {
            checkWinnerIfDealerIsNotBlackJack(player, gameResult);
        }
        return gameResult;
    }

    private List<Boolean> getGameResultIfDealerIsBlackJack(final List<Boolean> gameResult) {
        for (Player player : participant.players()) {
            gameResult.add(checkWinnerIfPlayerIsBlackJack(player));
        }
        return gameResult;
    }

    public boolean isNotDealerBlackJack() {
        Dealer dealer = participant.dealer();
        return !dealer.isBlackJack(BLACKJACK_SCORE);
    }

    private boolean checkWinnerIfPlayerIsBlackJack(final Player player) {
        return isBlackJack(player) && !hasMoreCard(player);
    }

    private void checkWinnerIfDealerIsNotBlackJack(final Player player, final List<Boolean> gameResult) {
        if (isNotBustedOrBlackJack(player)) {
            gameResult.add(true);
            return;
        }
        if (hasSameScore(player)) {
            gameResult.add(hasMoreCard(player));
            return;
        }
        gameResult.add(hasMoreScore(player));
    }

    private boolean hasSameScore(final Player player) {
        return player.hasSameScore(participant.dealer());
    }

    private boolean isNotBustedOrBlackJack(final Player player) {
        return !isBusted(player) || isBlackJack(player);
    }

    private boolean hasMoreScore(final Player player) {
        return player.hasMoreScore(participant.dealer());
    }

    private boolean hasMoreCard(final Player player) {
        return player.hasMoreCard(participant.dealer());
    }

    private boolean isBlackJack(final Player player) {
        return player.calculateScore(BLACKJACK_SCORE) == BLACKJACK_SCORE;
    }

    public List<Boolean> judgePlayersIfDealerBusted() {
        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            if (isBusted(player)) {
                gameResult.add(false);
            } else {
                gameResult.add(true);
            }
        }
        return gameResult;
    }

    private boolean isBusted(final Player player) {
        return player.calculateScore(BLACKJACK_SCORE) > BLACKJACK_SCORE;
    }

    public List<Boolean> compareScore() {
        Dealer dealer = participant.dealer();
        int dealerScore = dealer.calculateScore(BLACKJACK_SCORE);

        List<Boolean> isWinner = new ArrayList<>();
        List<Player> players = participant.players();

        return players.stream()
                .map(
                        player -> isWinner.add(doesPlayerWin(player.calculateScore(BLACKJACK_SCORE), dealerScore))
                ).toList();
    }

    private boolean doesPlayerWin(final int playerScore, final int dealerScore) {
        return playerScore >= dealerScore;
    }
}
