package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.result.WinDrawLose;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Player extends Participant {

    private static final Pattern MONEY_VALID_REGEX = Pattern.compile("^[1-9][0-9]*$");

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";
    private static final String ERROR_MESSAGE_INVALID_MONEY = "배팅 금액은 자연수여야 합니다.";

    private final int money;

    public Player(String name, String money, Set<Card> cards) {
        super(name, cards);
        validateProhibitName(name);
        validateValidProfit(money);
        this.money = Integer.parseInt(money);
    }

    public Player(String name, String money) {
        this(name, money, new HashSet<>());
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    private void validateValidProfit(String money) {
        if (!MONEY_VALID_REGEX.matcher(money).matches()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_MONEY);
        }
    }

    public double calculateProfit(Dealer dealer) {
        return WinDrawLose.calculateWinDrawLose(this, dealer).getProfitRatio() * money;
    }

    @Override
    public boolean hasNextTurn() {
        return !isBust();
    }
}
