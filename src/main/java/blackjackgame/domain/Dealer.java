package blackjackgame.domain;

import java.util.ArrayList;

public class Dealer extends Player {

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    // TODO : 메서드 네이밍 고민 후 수정하기
    public boolean isPick() {
        return getScore() <= 16;
    }

    public String getName() {
        return "딜러";
    }
}
