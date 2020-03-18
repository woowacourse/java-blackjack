package domain.player;

import domain.money.BattingMoney;
import domain.card.Card;

import java.util.List;

public class Player extends User {
    public Player(String name, List<Card> userCardDeck) {
        super(userCardDeck);
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("플레이어 이름이 null 입니다.");
        }
    }

    public Player(String name, List<Card> userCardDeck, double money) {
        this(name, userCardDeck);
        this.money = new BattingMoney(money);
    }

    @Override
    public void drawCard(Card card) {
        this.cards.add(card);
        validateDuplicateCard();
    }

    public String getName() {
        return name;
    }
}
