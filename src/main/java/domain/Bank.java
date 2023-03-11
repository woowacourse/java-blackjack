package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/11
 */
public class Bank {

    private final Map<Player, Money> accountBook;

    public Bank(final List<Player> players, final List<Money> monies) {
        validate(players, monies);
        this.accountBook = makeAccountBook(players, monies);
    }

    private Map<Player, Money> makeAccountBook(final List<Player> players, final List<Money> monies) {
        Map<Player, Money> accountBook = new LinkedHashMap<>();
        for (int i = 0; i < players.size(); i++) {
            accountBook.put(players.get(i), monies.get(i));
        }
        return accountBook;
    }

    private void validate(final List<Player> players, final List<Money> monies) {
        if (players.size() != monies.size()) {
            throw new IllegalArgumentException("플레이어와 돈 갯수가 맞지 않습니다.");
        }
    }

    public Money withdrawOfPlayer(final Player player) {
        validateExist(player);
        return accountBook.get(player);
    }

    private void validateExist(final Player player) {
        if (!accountBook.containsKey(player)) {
            throw new IllegalArgumentException("플레이어를 확인해주세요.");
        }
    }

    public void multiplyInterestOfPlayer(final Player player, final double profit) {
        Money money = accountBook.get(player);
        accountBook.put(player, money.calculateMoneyByProfit(profit));
    }

    public List<Integer> getCalculatedProfitMonies() {
        return accountBook.values().stream()
                .map(money -> money.money)
                .collect(Collectors.toList());
    }
}
