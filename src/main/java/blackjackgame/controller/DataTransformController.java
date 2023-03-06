package blackjackgame.controller;

import blackjackgame.domain.Card;
import blackjackgame.domain.GameOutcome;
import blackjackgame.domain.Guest;

import java.util.*;

public class DataTransformController {
    public static List<List<String>> transformCards(List<Card> cards) {
        List<List<String>> playerCards = new ArrayList<>();
        for (final Card card : cards) {
            List<String> playerCard = new ArrayList<>();
            playerCard.add(card.getValue());
            playerCard.add(card.getSymbol());
            playerCards.add(playerCard);
        }
        return Collections.unmodifiableList(playerCards);
    }

    public static Map<String, String> transformGuestsResult(Map<Guest, GameOutcome> guestsResult) {
        Map<String, String> result = new LinkedHashMap<>();
        for (final Guest guest : guestsResult.keySet()) {
            result.put(guest.getName(), guestsResult.get(guest).getOutcome());
        }
        return Collections.unmodifiableMap(result);
    }

    public static Map<String, Integer> transformDealerResult(Map<GameOutcome, Integer> dealerResult) {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (final GameOutcome gameOutcome : dealerResult.keySet()) {
            result.put(gameOutcome.getOutcome(), dealerResult.get(gameOutcome));
        }
        return Collections.unmodifiableMap(result);
    }
}
