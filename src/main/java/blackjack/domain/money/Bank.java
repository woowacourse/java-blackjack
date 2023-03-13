package blackjack.domain.money;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.WIN;

import blackjack.domain.result.Result;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Bank {

    private static final double BLACKJACK_WIN_RATE = 1.5;
    private static final double WIN_RATE = 2.0;
    private static final int EMPTY_MONEY = 0;

    private final Map<Player, Money> bank;

    public Bank(final Map<Player, Money> bank) {
        this.bank = bank;
    }

    public Bank() {
        this.bank = new HashMap<>();
    }

    public Bank betMoney(final Player player, final Money money) {
        Money defaultMoney = bank.getOrDefault(player, new Money(0));
        bank.put(player, defaultMoney.add(money));

        return new Bank(bank);
    }

    public Money findMoney(final Player player) {
        return bank.get(player);
    }

    public Money exchange(final Player player, final Result result) {
        Money findMoney = bank.get(player);

        return evaluate(player, result, findMoney);
    }

    private Money evaluate(final Player player, final Result result, final Money findMoney) {
        if (isBlackJackWin(player, result)) {
            return multiply(findMoney, BLACKJACK_WIN_RATE);
        }
        if (result == WIN) {
            return multiply(findMoney, WIN_RATE);
        }
        if (result == DRAW) {
            return findMoney;
        }
        return new Money(EMPTY_MONEY);
    }

    private Money multiply(final Money findMoney, double ratio) {
        return new Money((int) (findMoney.getMoney() * ratio));
    }

    private boolean isBlackJackWin(final Player player, final Result result) {
        return result == WIN && player.isBlackjack();
    }

    public Money totalMoney() {
        int sum = bank.values().stream()
                .mapToInt(Money::getMoney)
                .sum();
        return new Money(sum);
    }

    public Map<Player, Money> getBank() {
        return Collections.unmodifiableMap(bank);
    }
}
