package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String PLAYER_NAME_ERR_MSG = "플레이어 이름은 0자 이상, %d자 이하입니다.";
    private static final int MAX_NAME_LENGTH = 5;

    protected String name;
    protected Cards cards = new Cards();

    public Participant(String name) {
        Objects.requireNonNull(name, NULL_ERR_MSG);
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(PLAYER_NAME_ERR_MSG, MAX_NAME_LENGTH));
        }
    }

    public abstract boolean canGetMoreCard();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> showCards() {
        return cards.showCardsInfo();
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
