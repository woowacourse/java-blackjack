package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.dto.DealerDto;
import java.util.ArrayList;
import java.util.List;

public class Dealer implements Gamer {

    private final Cards cards;

    private Dealer(final Cards cards) {
        this.cards = cards;
    }

    public static Dealer create() {
        return new Dealer(new Cards(new ArrayList<>()));
    }

    @Override
    public void draw(final Card card) {
        cards.add(card);
    }

    public Card findFaceUpCard() {
        return cards.findFirst();
    }

    public DealerDto firstCardToDto() {
        return new DealerDto(List.of(this.findFaceUpCard()));
    }

    public DealerDto allCardToDto() {
        return new DealerDto(cards.getCards());
    }
}
