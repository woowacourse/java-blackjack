package model.game.action;

public interface CheckAction {

    boolean isNotBlackjack();

    boolean isBlackjack();

    boolean isNotBurst();

    boolean isBurst();
}
