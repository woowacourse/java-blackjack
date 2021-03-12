package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;

import java.util.List;
import java.util.Objects;

public class DealerGameResult {
    private static final int DEALER_PROFIT_OPERATOR = -1;

    private final Participant dealer;
    private final List<Card> resultCards;

    public DealerGameResult(Dealer dealer, List<Card> resultCards) {
        this.dealer = dealer;
        this.resultCards = resultCards;
    }

    public double calculateDealerProfit(UserGameResult userGameResult) {

        return userGameResult.getUserProfit()
                .mapToDouble(i -> i)
                .sum() * DEALER_PROFIT_OPERATOR;
    }

    public Participant getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealerGameResult that = (DealerGameResult) o;
        return Objects.equals(resultCards, that.resultCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCards);
    }
}
