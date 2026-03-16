package blackjack.domain.judgement;

import blackjack.domain.participant.Nickname;
import java.util.Map;

public class BettingMoneyInfo {

    private final Map<Nickname, BettingMoney> bettingMoneyInfo;

    public BettingMoneyInfo(Map<Nickname, BettingMoney> bettingMoneyInfo) {
        this.bettingMoneyInfo = bettingMoneyInfo;
    }

    public BettingMoney findMoneyByName(Nickname nickname) {
        return bettingMoneyInfo.get(nickname);
    }


}
