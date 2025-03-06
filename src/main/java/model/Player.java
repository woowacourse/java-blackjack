package model;

import java.util.List;
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
    public String getNickname() {
        return nickname.getValue();
    }

    @Override
    public void addCards(List<Card> findCards) {
        super.addCards(findCards);
    }

    @Override
    public List<Card> getHands() {
        return super.getHands();
    }

    @Override
    public int getSum() {
        return super.getSum();
    }

    @Override
    public boolean isNotBust() {
        return super.isNotBust();
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
