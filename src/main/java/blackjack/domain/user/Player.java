package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

import java.util.Objects;

public class Player {
    private static final String NULL_ERR_MSG = "플레이어의 이름이 없습니다.";
    private static final String PLAYER_NAME_ERR_MSG = "플레이어 이름은 0자 이상, %d자 이하입니다.";
    private static final int MAX_NAME_LENGTH = 5;

    private String name;
    private Cards cards;
    private Result result;

    public Player(String name) {
        Objects.requireNonNull(name, NULL_ERR_MSG);

        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(PLAYER_NAME_ERR_MSG, MAX_NAME_LENGTH));
        }

        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean exceedMaxSum() {
        if (cards.computeScore() > Cards.MAX_SUM) {
            return true;
        }
        return false;
    }

    public int getSum() {
        return cards.computeScore();
    }

    public void createResult(Dealer dealer) {
        this.result = Result.getResult(getSum(), dealer.getSum());
    }

    public String showCards() {
        return String.join(" ,", cards.showCardsInfo());
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public Result getResult() {
        return result;
    }
}
