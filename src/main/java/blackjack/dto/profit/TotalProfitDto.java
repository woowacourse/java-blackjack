package blackjack.dto.profit;

import java.util.Collections;
import java.util.List;

public class TotalProfitDto {
    private ProfitDto profitOfDealer;
    private List<ProfitDto> profitOfPlayers;

    public TotalProfitDto(ProfitDto profitOfDealer, List<ProfitDto> profitOfPlayers) {
        this.profitOfDealer = profitOfDealer;
        this.profitOfPlayers = profitOfPlayers;
    }

    public ProfitDto getProfitOfDealer() {
        return profitOfDealer;
    }

    public List<ProfitDto> getProfitOfPlayers() {
        return Collections.unmodifiableList(profitOfPlayers);
    }
}
