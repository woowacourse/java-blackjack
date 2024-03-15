package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import java.util.List;
import java.util.stream.Collectors;

public record CardHandDto(List<CardDto> cardDtos) {

    public static CardHandDto from(final CardHand cardHand) {
        final List<Card> cards = cardHand.getCards();

        return cards.stream()
                .map(CardDto::from)
                .collect(Collectors.collectingAndThen(Collectors.toList(), CardHandDto::new));
    }
}
