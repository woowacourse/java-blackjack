package blackjack.dto;

import java.util.List;

public class TotalProfitDTO {
    private ProfitDTO profitOfDealer;
    private List<ProfitDTO> profitOfPlayers;

    public TotalProfitDTO(ProfitDTO profitOfDealer, List<ProfitDTO> profitOfPlayers) {
        this.profitOfDealer = profitOfDealer;
        this.profitOfPlayers = profitOfPlayers;
    }

    public ProfitDTO getProfitOfDealer() {
        return profitOfDealer;
    }

    public List<ProfitDTO> getProfitOfPlayers() {
        return profitOfPlayers;
    }
}
