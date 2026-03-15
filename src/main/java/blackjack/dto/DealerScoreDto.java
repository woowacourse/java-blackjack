package blackjack.dto;

import blackjack.domain.participant.Dealer;

import java.util.List;

public record DealerScoreDto(
        List<CardDto> cards,
        int score
) {
    public static DealerScoreDto from(Dealer dealer) {
        List<CardDto> cardDtos = dealer.getCards().stream()
                .map(CardDto::from)
                .toList();
        return new DealerScoreDto(cardDtos, dealer.score());
    }
}
