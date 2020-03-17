package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    protected Cards cards;
    protected String name;

    public User(String name, List<Card> cards) {
        if ((name == null || name.trim().length() == 0)
                || (cards == null || cards.isEmpty())) {
            throw new IllegalArgumentException("플레이어의 이름 또는 카드를 입력하지 않았습니다.");
        }

        this.cards = Cards.of(cards);
        this.name = name;
    }

    public void hitCard(Card card) {
        if (card == null) {
            throw new NullPointerException("입력한 카드가 없습니다.");
        }
        this.cards.addCard(card);
    }

    public boolean isBlackJack() {
        return this.cards.isBlackJack();
    }

    public int sumCardNumber() {
        return this.cards.getCardsSum();
    }

    public Cards getCard() {
        return this.cards;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getCardNumbers() {
        List<String> cardNumbers = new ArrayList<>(this.cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.toList()));
        return Collections.unmodifiableList(cardNumbers);
    }
}
