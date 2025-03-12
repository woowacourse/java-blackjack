package model.participant;

import model.score.MatchType;

import java.util.Objects;

public class Player extends Participant {

    private static final int HIT_CONDITION = 21;

    private final Nickname nickname;
    private MatchType matchType;

    private Player(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public static Player from(String nickname) {
        return new Player(nickname);
    }

    public void updateResult(MatchType matchType) {
        this.matchType = matchType;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    @Override
    public boolean isHit() {
        return cards.isHit(HIT_CONDITION);
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
