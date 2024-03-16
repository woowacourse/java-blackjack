package blackjackgame.domain.blackjack;

import java.util.List;

public record GameProfit(double dealerProfit, List<Double> playersProfit) {
}
