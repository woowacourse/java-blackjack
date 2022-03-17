package blackjack.dto.dealer;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.dto.CardDto;

public class DealerDto {

    private final List<CardDto> cardDtos;
    private final int score;

    private DealerDto(final List<Card> cards, final int score) {
        this.cardDtos = cards.stream()
                .map(CardDto::toDto)
                .collect(Collectors.toUnmodifiableList());
        this.score = score;
    }

    public static DealerDto toDto(final Dealer dealer) {
        return new DealerDto(dealer.getCards(), dealer.getScore());
    }

    public List<String> getCardNames() {
        return cardDtos.stream()
                .map(CardDto::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return score;
    }

}
