package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.dto.DealerDto;

import java.util.List;

public class Dealer extends Gamer {

    private static final int HIT_THRESHOLD = 16;

    public Dealer() {

    }

    @Override
    public boolean canHit() {
        return cards.sum() <= HIT_THRESHOLD;
    }

    public Card findFaceUpCard() {
        return cards.findFirst();
    }

    // TODO: DTO 변환 위치 재고
    public DealerDto firstCardToDto() {
        return new DealerDto(List.of(this.findFaceUpCard()), calculateScore());
    }

    public DealerDto allCardToDto() {
        return new DealerDto(cards.getCards(), calculateScore());
    }
}
