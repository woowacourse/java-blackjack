package dto.response;

import domain.PlayedGameResult;
import domain.gameplaying.Card;
import java.util.List;

public record NameAndCardsResponse(String name, List<String> cardInfos) {

    public static NameAndCardsResponse from(PlayedGameResult result) {
        return new NameAndCardsResponse(result.name(), parse(result.cards()));
    }

    private static List<String> parse(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }
}
