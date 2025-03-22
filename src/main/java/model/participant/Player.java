package model.participant;

import model.score.MatchResult;

import java.util.Objects;
import java.util.function.Supplier;

public class Player extends Participant {

    private final Nickname nickname;
    private final Supplier<Boolean> intentSupplier;

    public Player(Nickname nickname, Supplier<Boolean> intentSupplier) {
        this.nickname = nickname;
        this.intentSupplier = intentSupplier;
    }

    @Override
    public boolean canHit() {
        return ableToDraw() && agreeIntent();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < BLACK_JACK_SCORE;
    }

    private boolean agreeIntent() {
        return intentSupplier.get();
    }

    public MatchResult compareToScore(Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return MatchResult.PUSH;
        }
        if (isBlackjack()) {
            return MatchResult.BLACKJACK;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        if (isBust()) {
            return MatchResult.BUST;
        }
        if (getScore() > dealer.getScore()) {
            return MatchResult.WIN;
        }
        if (getScore() < dealer.getScore()) {
            return MatchResult.LOSE;
        }
        return MatchResult.PUSH;
    }

    @Override
    public String getNickname() {
        return nickname.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(nickname, player.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}
