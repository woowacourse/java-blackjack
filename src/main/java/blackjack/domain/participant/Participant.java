package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Objects;

public abstract class Participant {
    private static final String DEALER_NAME = "딜러";
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String PLAYER_NAME_ERR_MSG = "플레이어 이름은 0자 이상, %d자 이하입니다.";
    private static final int MAX_NAME_LENGTH = 5;

    protected String name;
    protected Cards cards = new Cards();

    public Participant() {
        this.name = DEALER_NAME;
    }

    public Participant(String name) {
        Objects.requireNonNull(name, NULL_ERR_MSG);

        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(PLAYER_NAME_ERR_MSG, MAX_NAME_LENGTH));
        }
        this.name = name;
    }

    public abstract boolean canGetMoreCard();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int computeScore() {
        return cards.computeScore();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
