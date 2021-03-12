package blackjack.domain.participant;

import blackjack.domain.card.Card;
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
        return isBlackjack() || isHit();
    }

    public void setBetMoney(int betMoney) {
        this.betMoney = betMoney;
    }

    public double calculateProfitFromState(Dealer dealer) {
        if (playerLoses(dealer)) {
            return -betMoney;
        }
        if (playerWinsByBlackjack(dealer)) {
            return 1.5 * betMoney;
        }
        if (playerWinsNormally(dealer)) {
            return betMoney;
        }

        return 0;
    }

    private boolean playerWinsNormally(Dealer dealer) {
        return (isHit() && dealer.isBust()) ||
                (isHit() && dealer.isHit() && getState().softHandSum() > dealer.getState().softHandSum());
    }

    private boolean playerWinsByBlackjack(Dealer dealer) {
        return isBlackjack() &&
                (dealer.isHit() || dealer.isBust());
    }

    private boolean playerLoses(Dealer dealer) {
        return isBust() ||
                (isHit() && dealer.isBlackjack()) ||
                (isHit() && dealer.isHit() && getState().softHandSum() < dealer.getState().softHandSum());
    }
}
