package model;

import java.util.ArrayList;
import java.util.List;

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
