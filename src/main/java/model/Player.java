package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private final Nickname nickname;
    private final List<Card> hands;

    private Player(String nickname) {
        super(nickname);
        this.nickname = new Nickname(nickname);
        this.hands = new ArrayList<>();
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
    public int sum() {
        return super.sum();
    }
}
