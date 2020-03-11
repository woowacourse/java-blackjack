package blackjack.domain;

import blackjack.exception.UserNameEmptyException;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Card> cards = new ArrayList<>();

    public User(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException("유저의 이름은 공백일 수 없습니다.");
        }
        this.name = name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getCardsSize() {
        return this.cards.size();
    }
}
