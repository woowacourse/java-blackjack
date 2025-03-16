package model.participant;

import model.Money;
import model.score.MatchResult;

import java.util.Objects;

public class Player extends Participant {

    private final Nickname nickname;
    private final Money batingMoney;

    public Player(Nickname nickname, Money batingMoney) {
        this.nickname = nickname;
        this.batingMoney = batingMoney;
    }

    @Override
    public boolean ableToDraw() {
        return getScore() < BLACK_JACK_SCORE;
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
                throw new IllegalStateException("Unexpected result: " + result);
        }
    }

    // TODO: DL: 승패 판단 compare 반환값: https://www.notion.so/DL-compare-1b6cfbb673e4804685c7f7f9f3ce9504?pvs=4
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
}
