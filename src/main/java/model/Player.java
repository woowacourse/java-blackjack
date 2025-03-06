package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private final Nickname nickname;
    private final List<Card> hands;
    private int sum;
    private int aceCount;

    private Player(String nickname) {
        super(nickname);
        this.nickname = new Nickname(nickname);
        this.hands = new ArrayList<>();
        this.sum = 0;
        this.aceCount = 0;
    }

    public static Player from(String nickname) {
        return new Player(nickname);
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
