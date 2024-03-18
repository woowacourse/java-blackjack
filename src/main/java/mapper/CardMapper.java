package mapper;

import domain.card.CardNumber;
import domain.card.CardPhrase;
import domain.card.Cards;
import domain.card.Hand;
import domain.vo.Card;
import view.dto.card.CardDto;
import view.dto.card.CardsDto;

import java.util.Arrays;
import java.util.List;

public class CardMapper {
    private CardMapper() {
    }

    public static CardDto cardToCardDto(final Card card) {
        return new CardDto(convertToPhrase(card.number()), card.shape()
                                                               .value());
    }

    private static String convertToPhrase(final CardNumber cardNumber) {
        return Arrays.stream(CardPhrase.values())
                     .filter(cardPhrase -> cardPhrase.getCardNumber() == cardNumber)
                     .map(CardPhrase::getPhrase)
                     .findFirst()
                     .orElse(String.valueOf(cardNumber.value()));
    }

    public static CardsDto cardsToCardsDto(final Cards cards, final int score) {
        return new CardsDto(convertToCardDtos(cards), score);
    }

    public static CardsDto handToCardsDto(final Hand hand, final int score) {
        return new CardsDto(convertToCardDtos(hand.getCards()), score);
    }

    private static List<CardDto> convertToCardDtos(final Cards cards) {
        return cards.toList()
                    .stream()
                    .map(CardMapper::cardToCardDto)
                    .toList();
    }
}
