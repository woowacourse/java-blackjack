package model.participant;

import model.score.MatchResult;

import java.util.Objects;

public class Player extends Participant {

    private final Nickname nickname;

    private Player(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public static Player from(String nickname) {
        return new Player(nickname);
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

    public MatchResult matchFrom(Dealer dealer) {
        return MatchResult.fromCompare(compareToScore(dealer));
    }

    // TODO: DL: 승패 판단 compare 반환값: https://www.notion.so/DL-compare-1b6cfbb673e4804685c7f7f9f3ce9504?pvs=4
    private int compareToScore(Dealer dealer) {
        if (isBust()) {
            return -1;
        }

        if (dealer.isBust()) {
            return 1;
        }

        return this.getScore() - dealer.getScore();
    }
}
