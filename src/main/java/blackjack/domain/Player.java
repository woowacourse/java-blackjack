package blackjack.domain;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;
import blackjack.domain.result.Result;

import java.util.Objects;

public class Player {
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String MAX_PLAYER_NAME_ERR_MSG = "플레이어 이름은 최대 5자입니다.";
    public static final int MAX_NAME_LENGTH = 5;

    private String name;
    private Cards cards;
    private Result result;

    public Player(String name) {
        Objects.requireNonNull(name, NULL_ERR_MSG);

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(MAX_PLAYER_NAME_ERR_MSG);
        }

        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public boolean exceedMaxSum() {
        if (cards.getSum() > Cards.MAX_SUM) {
            return true;
        }
        return false;
    }

    public int getSum() {
        return cards.getSum();
    }

    public void createResult(Dealer dealer) {
        this.result = Result.getResult(getSum(), dealer.getSum());
    }

    public Result getResult() {
        return result;
    }

    public String showCards() {
        return String.join(" ,", cards.getInfo());
    }

    public Cards getCards() {
        return cards;
    }
}
