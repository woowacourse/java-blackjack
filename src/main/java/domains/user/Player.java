package domains.user;

import domains.card.Deck;
import domains.result.ResultType;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;
import domains.user.name.PlayerName;

import java.util.Objects;

public class Player extends User {
    private static final String YES = "y";
    private static final String NO = "n";

    private PlayerName name;
    private BettingMoney bettingMoney;

    public Player(PlayerName name, String bettingMoney, Hands hands) {
        super(hands);
        this.name = name;
        this.bettingMoney = new BettingMoney(bettingMoney);
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

    public ProfitMoney calculateProfitMoney(ResultType resultType) {
        return this.bettingMoney.multiply(resultType.getProfitRate());
    }

    public String getName() {
        return name.toString();
    }
}
