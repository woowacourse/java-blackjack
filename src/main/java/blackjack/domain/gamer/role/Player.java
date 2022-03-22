package blackjack.domain.gamer.role;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.result.Match;

public final class Player extends Role{
    private static final double BLACKJACK_RATIO = 1.5;

    public Player(String name) {
        super(name);
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

    public void initializeBettingMoney(int amount) {
        bettingMoney = BettingMoney.of(amount);
    }

    @Override
    public void initializeTotalMoney(Match match, int money) {
        bettingMoney = bettingMoney.initializeTotalMoney(match, (int)(money * profitRatio(match)));
    }

    private double profitRatio(Match match) {
        if (match.equals(Match.WIN) && cardGroup.isBlackJack()) {
            return BLACKJACK_RATIO;
        }
        return 1;
    }

    public int getInitialMoney() {
        return bettingMoney.getInitialMoney();
    }

    public int getProfitMoney(Match match) {
        return (int)(bettingMoney.getInitialMoney() * profitRatio(match));
    }
}
