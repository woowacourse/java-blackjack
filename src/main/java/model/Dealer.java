package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private final String nickname;
    private final List<Card> hands;

    private Dealer(String nickname) {
        super(nickname);
        this.nickname = nickname;
        this.hands = new ArrayList<>();
    }

    public static Dealer of() {
        return new Dealer("딜러");
    }

    @Override
    public String getNickname() {
        return nickname;
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
