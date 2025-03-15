package participant;

import score.MatchResultType;

import java.util.Objects;

public class Player extends Participant {

    private static final int HIT_CONDITION = 21;

    private final Nickname nickname;
    private MatchResultType matchResultType;

    private Player(String nickname) {
        this.nickname = new Nickname(nickname);
    }

    public static Player from(String nickname) {
        return new Player(nickname);
    }

    public void updateResult(MatchResultType matchResultType) {
        this.matchResultType = matchResultType;
    }

    public MatchResultType getMatchType() {
        return matchResultType;
    }

    @Override
    public boolean canHit() {
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
