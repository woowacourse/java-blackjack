package blackjack.dto.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public record GamerCardState(List<Card> cards, int score) {
}
