package blackjack.dto;

import blackjack.domain.deck.Deck;
import java.util.List;

public record DeckDto (List<String> cardNames){
    public static DeckDto from(Deck deck) {
        List<String> cardNameList = deck.getDeck().stream()
                .map(CardDto::from)
                .map(CardDto::cardName)
                .toList();
        return new DeckDto(cardNameList);
    }
}
