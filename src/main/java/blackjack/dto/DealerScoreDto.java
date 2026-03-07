package blackjack.dto;

import blackjack.model.Dealer;
import blackjack.model.Score;
import java.util.List;

public record DealerScoreDto(
        List<CardDto> cards,
        Score score
) {
    public static DealerScoreDto from(Dealer dealer) {
        List<CardDto> cards = dealer.getCards()
                .stream()
                .map(CardDto::from)
                .toList();

        return new DealerScoreDto(
                cards,
                dealer.getScore()
        );
    }
}
