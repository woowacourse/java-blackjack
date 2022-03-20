package blackjack.domain.gamer.role;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Money;
import blackjack.domain.result.Match;

public final class Player extends Role{
    private static final double BLACKJACK_RATIO = 1.5;

    private final Money betMoney;

    public Player(String name) {
        super(name);
        this.betMoney = new Money();
    }

    public static List<Player> of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    @Override
    public void addCard(Card card) {
        if (isBust()) {
            return;
        }

        cardGroup.addCard(card);
    }

    @Override
    public boolean isAddable() {
        return cardGroup.isAddable();
    }

    public void addMoney(int amount) {
        betMoney.add(amount);
    }

    public int getProfitMoney(Match match) {
        return (int) (betMoney.getAmount() * profitRatio(match));
    }

    private double profitRatio(Match match) {
        if (match.equals(Match.WIN) && cardGroup.isBlackJack()) {
            return BLACKJACK_RATIO;
        }
        if (match.equals(Match.WIN)) {
            return 1;
        }
        if (match.equals(Match.LOSE)) {
            return -1;
        }
        return 0;
    }
}
