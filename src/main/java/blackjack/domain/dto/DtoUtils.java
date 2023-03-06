package blackjack.domain.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class DtoUtils {

    public static List<CardDto> makeCardToDto(List<Card> cards){
        final ArrayList<CardDto> cardsDto = new ArrayList<>();
        for (Card card : cards) {
            cardsDto.add(new CardDto(card));
        }
        return cardsDto;
    }

}
