package domain.game;

import domain.participant.Participant;

import java.util.function.BiPredicate;

public enum GameReferee {
    FIRST_TURN_DEALER_WIN(GameReferee::isFirstDealerWin),
    FIRST_TURN_PLAYER_WIN(GameReferee::isFirstPlayerWin),
    IS_DEALER_WIN(GameReferee::isDealerWin),
    IS_PLAYER_WIN(GameReferee::isPlayerWin),
    IS_ALL_BLACKJACK(GameReferee::isAllBlackJack);

    private final BiPredicate<Participant, Participant> referee;

    GameReferee(final BiPredicate<Participant, Participant> referee) {
        this.referee = referee;
    }

    private static boolean isFirstDealerWin(final Participant dealer, final Participant player) {
        return dealer.isBlackJack() && !player.isBlackJack();
    }

    private static boolean isFirstPlayerWin(final Participant dealer, final Participant player) {
        return !dealer.isBlackJack() && player.isBlackJack();
    }

    private static boolean isDealerWin(final Participant dealer, final Participant player) {
        return dealer.isBlackJack()
                || dealer.isBust() && player.isBust()
                || !dealer.isBust() && dealer.calculateScore() > player.calculateScore();
    }

    private static boolean isPlayerWin(final Participant dealer, final Participant player) {
        return player.isBlackJack()
                || dealer.calculateScore() < player.calculateScore();
    }

    private static boolean isAllBlackJack(final Participant dealer, final Participant player) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    public BiPredicate<Participant, Participant> getReferee() {
        return referee;
    }
}

