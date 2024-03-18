package blackjack.vo;

import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;

public record Card(Suit suit, Denomination denomination) {
}
