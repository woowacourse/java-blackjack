package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.utils.CardDeck;
import blackjack.domain.exception.IllegalNameException;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Participant {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");
    public static final int ZERO_PROFIT = 0;
    private int betMoney;

    public Player(String name, CardDeck cardDeck) {
        this(name, cardDeck.initCards());
    }

    public Player(String name, List<Card> cards) {
        super(process(name), cards);
        betMoney = 0;
    }

    private static String process(String nameValue) {
        validateNullOrEmpty(nameValue);
        nameValue = nameValue.trim();
        validateName(nameValue);
        return nameValue;
    }

    private static void validateName(String nameValue) {
        if (!NAME_PATTERN.matcher(nameValue).matches()) {
            throw new IllegalNameException("이름은 알파벳 대소문자로 이루어져야 합니다.");
        }
    }

    private static void validateNullOrEmpty(String nameValue) {
        if (nameValue == null || nameValue.isEmpty()) {
            throw new IllegalNameException("이름이 null이거나 빈 문자열일 수 없습니다.");
        }
    }

    @Override
    public boolean isAvailableToTake() {
        return isBlackjack() || isHit();
    }

    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
    }

    public double calculateProfitFromState(Dealer dealer) {
        if (isBust()) {
            return getState().profit(betMoney);
        }
        if (playerLosesByHit(dealer)) {
            return -getState().profit(betMoney);
        }
        if (playerDraws(dealer)) {
            return ZERO_PROFIT;
        }
        return getState().profit(betMoney);
    }

    private boolean playerDraws(Dealer dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return true;
        }
        if (!isHit() || !dealer.isHit()) {
            return false;
        }
        return getState().softHandSum() == dealer.getState().softHandSum();
    }

    private boolean playerLosesByHit(Dealer dealer) {
        if (isBlackjack()) {
            return false;
        }
        if (dealer.isBust()) {
            return false;
        }
        if (dealer.isBlackjack()) {
            return true;
        }
        return getState().softHandSum() < dealer.getState().softHandSum();
    }
}
