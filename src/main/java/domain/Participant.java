package domain;

import domain.vo.CardInfo;
import domain.vo.NameAndCardInfos;
import java.util.List;

abstract class Participant {

    private final String name;
    protected final Hand hand;

    Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    protected abstract boolean isPlayable();

    void draw() {
        if (isPlayable()) {
            hand.drawCard();
        }
    }

    List<CardInfo> cardInfos() {
        return hand.cardInfos();
    }

    boolean isBusted() {
        return hand.isBusted();
    }

    int scoreSum() {
        return hand.scoreSum();
    }

    String name() {
        return name;
    }

    NameAndCardInfos infos() {
        return new NameAndCardInfos(name, cardInfos());
    }
}
