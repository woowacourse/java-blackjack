package blackjack.response;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InitialCardResponse {

    private final String dealerCard;

    private final Map<String, List<String>> playerNameToCards;

    public InitialCardResponse(final String dealerCard, final Map<String, List<String>> playerNameToCards) {
        this.dealerCard = dealerCard;
        this.playerNameToCards = playerNameToCards;
    }

    public static InitialCardResponse of(
            final Players players,
            final Dealer dealer) {
        final CardConvertStrategy cardConvertStrategy = new CardConvertStrategyImpl();
        final String dealerCard = cardConvertStrategy.convert(dealer.getCards().get(0));
        final Map<String, List<String>> playerNameToCards = generatePlayerNameToCards(
                players.getPlayers(),
                cardConvertStrategy);
        return new InitialCardResponse(dealerCard, playerNameToCards);
    }

    private static Map<String, List<String>> generatePlayerNameToCards(
            final List<Player> players,
            final CardConvertStrategy cardConvertStrategy) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> convertCards(player.getCards(), cardConvertStrategy),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
    }

    private static List<String> convertCards(
            final List<Card> cards,
            final CardConvertStrategy cardConvertStrategy) {
        return cards.stream()
                .map(cardConvertStrategy::convert)
                .collect(Collectors.toList());
    }

    public String getDealerCard() {
        return dealerCard;
    }

    public Map<String, List<String>> getPlayerNameToCards() {
        return playerNameToCards;
    }
}
