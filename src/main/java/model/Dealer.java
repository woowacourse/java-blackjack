package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private final String nickname;
    private final List<Card> hands;
    private int sum;
    private int aceCount;

    private Dealer(String nickname) {
        super(nickname);
        this.nickname = nickname;
        this.hands = new ArrayList<>();
        this.sum = 0;
        this.aceCount = 0;
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
    public boolean isNotBust() {
        return super.isNotBust();
    }

    @Override
    public List<Card> getHands() {
        return super.getHands();
    }

    @Override
    public int getSum() {
        return super.getSum();
    }

    public boolean isNotUp() {
        return sum < 17;
    }
}
