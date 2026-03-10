package blackjack.view.dto;

import blackjack.model.participant.Dealer;
import java.util.List;

public record DealerScoreDto(
        List<CardDto> cards,
        int score
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
