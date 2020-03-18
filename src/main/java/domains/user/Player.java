package domains.user;

import domains.card.Deck;
import domains.result.ResultType;
import domains.user.money.BettingMoney;
import domains.user.name.PlayerName;

import java.util.Objects;

public class Player extends User {
    private static final String YES = "y";
    private static final String NO = "n";

    private PlayerName name;
    private BettingMoney bettingMoney;

    public Player(PlayerName name, String bettingMoney, Hands hands) {
        this.name = name;
        this.bettingMoney = new BettingMoney(bettingMoney);
        this.hands = hands;
        this.blackJack = hands.isBlackJack();
    }

    public Player(PlayerName name, String bettingMoney, Deck deck) {
        this(name, bettingMoney, new Hands(deck));
    }

    public boolean needMoreCard(String answer, Deck deck) {
        checkNullOrEmpty(answer);
        checkYesOrNo(answer);

        if (answer.equals(YES)) {
            hit(deck);
            return true;
        }
        return false;
    }

    private void checkNullOrEmpty(String answer) {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new InvalidPlayerException(InvalidPlayerException.NULL_OR_EMPTY);
        }
    }

    private void checkYesOrNo(String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return;
        }
        throw new InvalidPlayerException(InvalidPlayerException.INVALID_INPUT);
    }

    public ResultType checkResultType(Dealer dealer) {
        if (this.isBlackJack() && !dealer.isBlackJack()) {
            return ResultType.BLACKJACK;
        }
        if (this.isBurst() && !dealer.isBurst()) {
            return ResultType.LOSE;
        }
        if (dealer.isBurst() && !this.isBurst()) {
            return ResultType.WIN;
        }
        if (this.score() > dealer.score()) {
            return ResultType.WIN;
        }
        if (this.score() < dealer.score()) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    public String getName() {
        return name.toString();
    }

    public BettingMoney getBettingMoney() {
        return this.bettingMoney;
    }
}
