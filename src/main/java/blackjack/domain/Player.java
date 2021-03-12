package blackjack.domain;

import blackjack.utils.CardDeck;
import blackjack.utils.IllegalNameException;

import java.util.List;
import java.util.regex.Pattern;

public class Player extends Participant {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");
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
        return sumCards() <= BLACKJACK;
    }

    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
    }

    public double calculateProfitFromState(Dealer dealer) {
        if (isBust() || (isHit() && dealer.isBlackjack()) || (isHit() && dealer.isHit() && sumCardsForResult() < dealer.sumCardsForResult())) {
            return -betMoney;
        }
        if (isBlackjack() && (dealer.isHit() || dealer.isBust())) {
            return 1.5 * betMoney;
        }
        if ((isHit() && dealer.isBust()) || (isHit() && dealer.isHit() && sumCardsForResult() > dealer.sumCardsForResult())) {
            return betMoney;
        }
        if ((isBlackjack() && dealer.isBlackjack()) || (isHit() && dealer.isHit() && sumCardsForResult() == dealer.sumCardsForResult())) {
            return 0;
        }
        return 0;
    }
}
