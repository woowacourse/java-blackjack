package blackJack.domain;

import blackJack.domain.User.Player;
import blackJack.domain.User.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DealerScore {
    double dealerProfits = 0;

    public void makeDealerResult(PlayerScore playerScore){
        List<Integer> results = playerScore.getPlayersProfit().entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toUnmodifiableList());
        dealerProfits = results.stream().mapToDouble(Integer::intValue).sum() * -1;
    }

    public int getDealerProfits() {
        return (int)dealerProfits;
    }

}
