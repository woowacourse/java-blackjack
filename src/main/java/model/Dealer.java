package model;

public class Dealer extends Participant {

    private final String nickname;

    private Dealer(String nickname) {
        this.nickname = nickname;
    }

    public static Dealer of() {
        return new Dealer("딜러");
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public ResultType compareTo(Player player) {
        return ResultType.of(score.compareTo(player.score));
    }

    public boolean isNotUp() {
        return score.getValue() < 17;
    }
}
