package blackjack.controller;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Players;
import blackjack.response.CardResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//이 클래스는 controller 의 역할이지만, controller 에다가 두면, 너무 많은 역할을 하게 되는 것 같아서 분리하였습니다
//이 클래스가 단순하게 유틸리티 비슷한 클래스인데, 이대로 괜찮을지 여쭤보고 싶어요
public class DomainConverter {

    private DomainConverter() {
    }

    public static CardResponse convertCard(final Card card) {
        return CardResponse.from(card);
    }

    public static List<CardResponse> convertCards(final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public static Map<String, List<CardResponse>> getPlayerCards(final Players players) {
        return players.getPlayerNames().stream()
                .collect(toMap(
                        Function.identity(),
                        name -> generateCardResponses(players.findPlayerByName(name).getCards())
                ));
    }

    static List<CardResponse> generateCardResponses(final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public static Map<String, List<CardResponse>> convertPlayersCards(final Map<String, List<Card>> playersCards) {
        return playersCards.keySet().stream()
                .collect(toMap(
                        Function.identity(),
                        name -> generateCardResponses(playersCards.get(name)),
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
