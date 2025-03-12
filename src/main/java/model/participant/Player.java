package model.participant;

import model.score.MatchResult;

import java.util.Objects;

public class Player extends Participant {

    private final Nickname nickname;
    private MatchResult matchType;

    private Player(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public static Player from(String nickname) {
        return new Player(nickname);
    }

    public void updateResult(MatchResult matchType) {
        this.matchType = matchType;
    }

    public MatchResult getMatchType() {
        return matchType;
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
