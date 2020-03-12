package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Objects;

public abstract class User {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    private static final String MAX_PLAYER_NAME_ERR_MSG = "플레이어 이름은 최대 %자입니다.";
    private String name;
    private Cards cards;

    public User() {
        this.name = "";
        this.cards = new Cards();
    }

    public User(String name) {
        validateNotNull(name);
        validateNameLength(name);
        cards = new Cards();
    }

    private void validateNotNull(String name) {
        Objects.requireNonNull(name, String.format(NULL_ERR_MSG, "사용자 이름"));
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(MAX_PLAYER_NAME_ERR_MSG, MAX_NAME_LENGTH));
        }
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        Objects.requireNonNull(name, String.format(NULL_ERR_MSG, "카드"));
        cards.add(card);
    }

    public String showCards() {
        return String.join(" ,", cards.getInfo());
    }

    public Cards getCards() {
        return cards;
    }

    public int computeSum() {
        return cards.computeSum();
    }

    public abstract boolean canReceiveMoreCard();
}
