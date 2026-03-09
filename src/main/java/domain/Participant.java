package domain;

import domain.vo.CardInfo;
import domain.vo.NameAndCardInfos;
import java.util.List;

public abstract class Participant {

    private final String name;
    protected final Hand hand;

    public Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    protected abstract boolean isPlayable();

    public void draw() {
        if (isPlayable()) {
            hand.drawCard();
        }
    }

    List<CardInfo> cardInfos() {
        return hand.cardInfos();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public int scoreSum() {
        return hand.scoreSum();
    }

    public String name() {
        return name;
    }

    NameAndCardInfos infos() {
        return new NameAndCardInfos(name, cardInfos());
    }
}
