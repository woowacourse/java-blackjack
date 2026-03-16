package domain.gameplaying;

import domain.CardInfo;
import domain.PlayedGameResult;
import java.util.List;

abstract class Participant {

    protected static final String DEALER_NAME = "딜러";
    protected static int INITIAL_CARD_COUNT = 2;

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
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            draw();
        }
    }

    PlayedGameResult infos() {
        return PlayedGameResult.from(name, this.cardInfos(), this.scoreSum());
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
