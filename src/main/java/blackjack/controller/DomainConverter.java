package blackjack.controller;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.dto.CardResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    private static List<CardResponse> generateCardResponses(final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }
}
