package blackjack.dto;

import blackjack.model.bet.Profit;
import blackjack.model.bet.Profits;
import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProfitsDto {
    private final Map<EntryDto, Integer> entryProfits;
    private final DealerDto dealer;
    private final int dealerProfit;

    private ProfitsDto(Map<EntryDto, Integer> entryProfits, DealerDto dealer, int dealerProfit) {
        this.entryProfits = entryProfits;
        this.dealer = dealer;
        this.dealerProfit = dealerProfit;
    }

    public static ProfitsDto from(Profits profits) {
        Map<Player, Profit> profitValues = profits.getValues();
        Dealer dealer = findDealer(profitValues);
        return new ProfitsDto(mapEntryProfits(profitValues),
                DealerDto.fromNameOf(dealer),
                profitValues.get(dealer).getValue());
    }

    private static Dealer findDealer(Map<Player, Profit> profitValues) {
        return (Dealer) profitValues.keySet().stream()
                .filter(player -> player instanceof Dealer)
                .findFirst()
                .get();
    }

    private static Map<EntryDto, Integer> mapEntryProfits(Map<Player, Profit> profitValues) {
        return findEntries(profitValues)
                .collect(Collectors.toMap(
                        EntryDto::from,
                        entry -> profitValues.get(entry).getValue(),
                        (a, b) -> b));
    }

    private static Stream<Entry> findEntries(Map<Player, Profit> profitValues) {
        return profitValues.keySet().stream()
                .filter(player -> player instanceof Entry)
                .map(player -> (Entry) player);
    }

    public Map<EntryDto, Integer> getEntryProfits() {
        return entryProfits;
    }

    public DealerDto getDealer() {
        return dealer;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
