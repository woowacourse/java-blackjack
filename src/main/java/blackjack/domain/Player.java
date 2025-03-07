package blackjack.domain;

public class Player {

    private final Nickname nickname;

    public Player(Nickname nickname) {
        this.nickname = nickname;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public String getNicknameValue() {
        return nickname.getValue();
    }
}
