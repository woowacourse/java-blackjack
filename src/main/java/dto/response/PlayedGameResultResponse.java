package dto.response;

import dto.PlayedGameResult;
import domain.gameplaying.Card;
import java.util.List;

public record PlayedGameResultResponse(NameAndCards nameAndCards, int scoreSum) {

    public static PlayedGameResultResponse from(PlayedGameResult result) {
        NameAndCards infos = new NameAndCards(result.name(), result.cards());
        return new PlayedGameResultResponse(infos, result.scoreSum());
    }

    public String name() {
        return nameAndCards.name();
    }

    public List<String> cardsInfo() {
        return nameAndCards.cardInfos().stream()
                .map(Card::toString)
                .toList();
    }

    private record NameAndCards(String name, List<Card> cardInfos) {
    }
}
