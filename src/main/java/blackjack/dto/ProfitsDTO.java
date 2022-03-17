package blackjack.dto;

import blackjack.model.Money;
import blackjack.model.Profits;
import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProfitsDTO {
    private final Map<EntryDTO, Integer> entryProfits;
    private final DealerDTO dealer;
    private final int dealerProfit;

    private ProfitsDTO(Map<EntryDTO, Integer> entryProfits, DealerDTO dealer, int dealerProfit) {
        this.entryProfits = entryProfits;
        this.dealer = dealer;
        this.dealerProfit = dealerProfit;
    }

    public static ProfitsDTO from(Profits profits) {
        Map<Player, Money> profitValues = profits.getValues();
        Dealer dealer = findDealer(profitValues);
        return new ProfitsDTO(mapEntryProfits(profitValues),
                DealerDTO.fromNameOf(dealer),
                profitValues.get(dealer).getAmount());
    }

    private static Dealer findDealer(Map<Player, Money> profitValues) {
        return (Dealer) profitValues.keySet().stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .get();
    }

    private static Map<EntryDTO, Integer> mapEntryProfits(Map<Player, Money> profitValues) {
        return findEntries(profitValues)
                .collect(Collectors.toMap(
                        EntryDTO::from,
                        entry -> profitValues.get(entry).getAmount(),
                        (a, b) -> b));
    }

    private static Stream<Entry> findEntries(Map<Player, Money> profitValues) {
        return profitValues.keySet().stream()
                .filter(player -> player instanceof Entry)
                .map(player -> (Entry) player);
    }

    public Map<EntryDTO, Integer> getEntryProfits() {
        return entryProfits;
    }

    public DealerDTO getDealer() {
        return dealer;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
