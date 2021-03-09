package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class DealerGameResult {
    private final Participant dealer;
    private final List<Card> resultCards;
    private final long DEFAULT_COUNT = 0L;

    public DealerGameResult(Dealer dealer, List<Card> resultCards) {
        this.dealer = dealer;
        this.resultCards = resultCards;
    }

    public List<String> countDealerWinOrLose(UserGameResult userGameResult) {
        Map<WinOrLose, Long> dealerWinOrLose = makeDealerWinOrLoseMap(userGameResult);

        return Arrays.stream(WinOrLose.values())
                .map(winOrLose -> dealerWinOrLose.getOrDefault(winOrLose, DEFAULT_COUNT) + winOrLose.getCharacter())
                .collect(Collectors.toList());
    }

    private Map<WinOrLose, Long> makeDealerWinOrLoseMap(UserGameResult userGameResult) {

        return userGameResult.getUserWinOrLose()
                .collect(groupingBy(WinOrLose::opposite, counting()));
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
