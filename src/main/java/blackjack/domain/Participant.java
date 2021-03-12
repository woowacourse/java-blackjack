package blackjack.domain;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final String INVALID_PARTICIPANT_NAME = "플레이어 이름은 양쪽 공백을 제외한 1글자 이상이어야 합니다.";
    private static final int INVALID_NAME_LENGTH = 0;

    private final String name;
    private final Cards cards;
    private BetAmount betAmount;

    protected Participant(String name) {
        validateName(name);
        this.name = name.trim();
        this.cards = new Cards();
        this.betAmount = BetAmount.ZERO;
    }

    private void validateName(String name) {
        int trimNameLength = Objects.requireNonNull(name)
                                    .trim()
                                    .length();
        if (trimNameLength == INVALID_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_PARTICIPANT_NAME);
        }
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void receiveCards(Cards receivedCards) {
        cards.addAll(receivedCards);
    }

    public abstract boolean isAbleToReceiveCard();
}
