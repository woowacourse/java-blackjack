package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public abstract class Player {
    protected Cards cards;
    protected String name;

    public Player(String name, List<Card> cards) {
        if ((name == null || name.trim().length() == 0)
                || (cards == null || cards.size() == 0)) {
            throw new NullPointerException("플레이어의 이름 또는 카드를 입력하지 않았습니다.");
        }

        this.cards = Cards.of(cards);
        this.name = name;
    }

    public void hitCard(Card card) {
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

    public String getCardNumber() {
        return this.cards.toString();
    }
}
