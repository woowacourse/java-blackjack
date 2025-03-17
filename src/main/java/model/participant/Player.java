package model.participant;

import model.Money;
import model.score.MatchResult;

import java.util.Objects;
import java.util.function.Supplier;

public class Player extends Participant {

    private final Nickname nickname;
    private final Money batingMoney;
    private final Supplier<Boolean> intentSupplier; // Intent를 동적으로 받는 Supplier 추가

    public Player(Nickname nickname, Money batingMoney, Supplier<Boolean> intentSupplier) {
        this.nickname = nickname;
        this.batingMoney = batingMoney;
        this.intentSupplier = intentSupplier;
    }

    @Override
    public boolean satisfiedCondition() {
        return ableToDraw() && agreeIntent();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < BLACK_JACK_SCORE;
    }

    private boolean agreeIntent() {
        return intentSupplier.get();
    }

    public int calculateEarnings(Dealer dealer) {
        MatchResult result = compareToScore(dealer);

        switch (result) {
            case WIN:
                return batingMoney.getValue();
            case BLACKJACK:
                return (int) (batingMoney.getValue() * 1.5);
            case LOSE:
            case BUST:
                return -1 * batingMoney.getValue();
            case PUSH:
                return 0;
            default:
                throw new IllegalStateException("존재할 수 없는 결과 입니다.");
        }
    }

    // TODO: DL: 상태패턴: https://www.notion.so/DL-compare-1b6cfbb673e4804685c7f7f9f3ce9504?pvs=4
    private MatchResult compareToScore(Dealer dealer) {
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
