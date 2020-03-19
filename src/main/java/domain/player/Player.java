package domain.player;

import domain.card.Card;
import domain.money.BattingMoney;

import java.util.List;

public class Player extends User {
    public Player(String name, List<Card> userCardDeck) {
        super(userCardDeck);
        validatePlayerName(name);
        this.name = name;
    }

    private void validatePlayerName(String name) {
        if (name == null) {
            throw new NullPointerException("플레이어 이름이 null 입니다.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름이 비었습니다.");
        }
    }

    public Player(String name, List<Card> userCardDeck, double money) {
        this(name, userCardDeck);
        this.money = new BattingMoney(money);
    }

    @Override
    public boolean isDrawCard() {
        if (isBlackJack()) {
            return false;
        }
        return isUnderWinningCount();
    }

    public String getName() {
        return name;
    }
}
