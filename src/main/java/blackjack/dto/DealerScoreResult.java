package blackjack.dto;

import blackjack.domain.Dealer;

import java.util.List;

public record DealerScoreResult(
        List<CardInfo> cards,
        int score
) {
    public static DealerScoreResult from(Dealer dealer) {
        List<CardInfo> cardInfos = dealer.getCards().stream()
                .map(CardInfo::from)
                .toList();
        return new DealerScoreResult(cardInfos, dealer.score());
    }
}
