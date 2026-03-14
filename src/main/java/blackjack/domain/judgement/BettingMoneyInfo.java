package blackjack.domain.judgement;

import blackjack.domain.participant.Name;
import java.util.Map;

public class BettingMoneyInfo {

    private final Map<Name, BettingMoney> bettingMoneyInfo;

    public BettingMoneyInfo(Map<Name, BettingMoney> bettingMoneyInfo) {
        this.bettingMoneyInfo = bettingMoneyInfo;
    }

    public BettingMoney findMoneyByName(Name nickname) {
        return bettingMoneyInfo.get(nickname);
    }


}
