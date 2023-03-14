package blackjackgame.domain;

import static blackjackgame.domain.GameResult.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guest;
import blackjackgame.domain.player.Player;

public class Judge {

    public static Map<Player, Double> playersProfit(Dealer dealer, List<Guest> guests) {
        Map<Player, Double> profitResult = new LinkedHashMap<>();
        profitResult.put(dealer, 0d);

        guests.forEach(guest ->
            profitResult.put(guest, judgeProfit(guest, dealer)));

        Optional<Double> guestsSumProfit = profitResult.values()
            .stream().reduce(Double::sum);

        profitResult.put(dealer, guestsSumProfit.get() * -1);
        return profitResult;
    }

    private static double judgeProfit(Guest guest, Dealer dealer) {
        if (guest.isBlackJack() && !dealer.isBlackJack()) {
            return guest.bettingMoney() * BLACKJACK_WIN.profit();
        }

        if (guest.isStay() && dealer.isLessThan(guest.scoreValue())) {
            return guest.bettingMoney() * WIN.profit();
        }

        if (guest.isBust() || guest.isLessThan(dealer.scoreValue())) {
            return guest.bettingMoney() * LOSE.profit();
        }

        return guest.bettingMoney() * DRAW.profit();
    }

}
