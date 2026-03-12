package domain.gameplaying;

import domain.common.BlackJackRule;
import domain.common.CardInfo;
import domain.common.NameAndCardInfos;
import java.util.List;

abstract class Participant {

    protected static final String DEALER_NAME = "딜러";

    private final String name;
    protected final Hand hand;

    Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    protected abstract boolean isPlayable();

    void draw() {
        requirePlayableHand();
        hand.drawCard();
    }

    void drawInitialCards() {
        for (int i = 0; i < BlackJackRule.INITIAL_CARD_COUNT.value(); i++) {
            draw();
        }
    }

    NameAndCardInfos infos() {
        return new NameAndCardInfos(name, cardInfos());
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

    List<CardInfo> cardInfos() {
        return hand.cardInfos();
    }

    private void requirePlayableHand() {
        if (!isPlayable()) {
            throw new IllegalStateException(String.format("%s는 더 이상 드로우 할 수 없습니다.", name));
        }
    }
}
