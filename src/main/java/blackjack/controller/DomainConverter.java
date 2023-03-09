package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Players;
import blackjack.response.CardResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//이 클래스는 controller 의 역할이지만, controller 에다가 두면, 너무 많은 역할을 하게 되는 것 같아서 분리하였습니다
//이 클래스가 단순하게 유틸리티 비슷한 클래스인데, 이대로 괜찮을지 여쭤보고 싶어요
class DomainConverter {

    private DomainConverter() {
    }

    static CardResponse convertCard(final Card card) {
        return CardResponse.from(card);
    }

    static List<CardResponse> convertCards(final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    static Map<String, List<CardResponse>> getPlayerCards(final Players players) {
        final List<String> playerNames = players.getPlayerNames();
        final Map<String, List<CardResponse>> playerCards = new HashMap<>();
        for (final String playerName : playerNames) {
            final List<CardResponse> cardResponses = players.findPlayerByName(playerName)
                    .getCards()
                    .stream()
                    .map(CardResponse::from)
                    .collect(Collectors.toList());
            playerCards.put(playerName, cardResponses);
        }
        return playerCards;
    }

    static Map<String, List<CardResponse>> convertPlayersCards(final Map<String, List<Card>> playersCards) {
        return playersCards.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(CardResponse::from)
                                .collect(Collectors.toList()),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }
}
