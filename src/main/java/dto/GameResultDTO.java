package dto;

import domain.blackjack.EarningMoney;
import java.util.List;

public class GameResultDTO {
    private final List<String> playersName;
    private final List<EarningMoney> playersEarningMoney;
    private final EarningMoney dealerEarningMoney;

    public GameResultDTO(List<String> playersName, List<EarningMoney> playersEarningMoney,
                         EarningMoney dealerEarningMoney) {
        this.playersName = playersName;
        this.playersEarningMoney = playersEarningMoney;
        this.dealerEarningMoney = dealerEarningMoney;
    }

    public List<String> getPlayersName() {
        return playersName;
    }

    public List<EarningMoney> getPlayersEarnMoney() {
        return playersEarningMoney;
    }

    public EarningMoney getDealerEarningMoney() {
        return dealerEarningMoney;
    }
}
