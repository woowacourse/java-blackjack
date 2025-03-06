package blackjack.domain;

public class Player {

    private final Nickname nickname;

    public Player(Nickname nickname) {
        this.nickname = nickname;
    }

    public static Player createDealer() {
        return new Player(Nickname.createDealerNickname());
    }

    public Nickname getNickname() {
        return nickname;
    }

    public boolean isDealer() {
        return nickname.equals(Nickname.createDealerNickname());
    }
}
