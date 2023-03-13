package blackjackgame.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Result {
    public static Map<Guest, GameOutcome> getGuestsResult(final List<Guest> guests, final Dealer dealer) {
        Map<Guest, GameOutcome> guestsResult = guests.stream()
                .collect(Collectors.toMap(guest -> guest, guest -> guest.calculateOutcome(dealer), (guest1,guest2) -> guest1, LinkedHashMap::new));
        return Collections.unmodifiableMap(guestsResult);
    }

    public static Map<GameOutcome, Integer> getDealerResult(final List<Guest> guests, final Dealer dealer) {
        Map<GameOutcome, Integer> dealerResult = initDealerResult();
        guests.forEach(
                guest -> {
                    GameOutcome gameOutcome = dealer.calculateOutcome(guest);
                    dealerResult.put(gameOutcome, dealerResult.get(gameOutcome) + 1);
                }
        );
        return Collections.unmodifiableMap(dealerResult);
    }

    private static Map<GameOutcome, Integer> initDealerResult() {
        return new HashMap<>(Arrays.stream(GameOutcome.values())
                .collect(Collectors.toMap(value -> value, ignored -> 0)));
    }

    public static Map<String, Integer> getPlayersRevenue(final List<Guest> guests, final Dealer dealer) {
        Map<String, Integer> bettingResult = new LinkedHashMap<>();
        int dealerRevenue = 0;
        bettingResult.put(dealer.getName(), dealerRevenue);
        for(Guest guest : guests) {
            GameOutcome gameOutcome = guest.calculateOutcome(dealer);
            int guestRevenue = gameOutcome.calculateRevenue(guest.getBettingMoney());
            bettingResult.put(guest.getName(), guestRevenue);
            dealerRevenue -= guestRevenue;
        }
        bettingResult.put(dealer.getName(), dealerRevenue);
        return Collections.unmodifiableMap(bettingResult);
    }
}
