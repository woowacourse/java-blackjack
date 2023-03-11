package blackjack.dto;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Money;
import blackjack.domain.result.Result;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChallengerProfitDto {

    private final Map<String, Integer> nameAndProfits;

    public ChallengerProfitDto(Result result, List<Challenger> challengers) {
        Map<String, Integer> nameAndProfits = new LinkedHashMap<>();
        for (Challenger challenger : challengers) {
            Money challengerProfit = result.getChallengerProfit(challenger);
            nameAndProfits.put(challenger.getName(), challengerProfit.getAmount());
        }
        this.nameAndProfits = nameAndProfits;
    }

    public Map<String, Integer> getNameAndProfits() {
        return nameAndProfits;
    }
}
