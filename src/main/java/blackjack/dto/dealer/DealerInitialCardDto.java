package blackjack.dto.dealer;

import blackjack.domain.card.Card;
import blackjack.dto.CardDto;

public class DealerInitialCardDto {

    private final CardDto cardDto;

    private DealerInitialCardDto(final Card card) {
        this.cardDto = CardDto.toDto(card);
    }

    public static DealerInitialCardDto toDto(final Card card) {
        return new DealerInitialCardDto(card);
    }

    public String getCardName() {
        return cardDto.getCardName();
    }

}
