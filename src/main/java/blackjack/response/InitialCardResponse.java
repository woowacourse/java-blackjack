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

    private final CardResponse dealerCard;

    private final Map<String, List<CardResponse>> playerNameToCards;

    private InitialCardResponse(final CardResponse dealerCard,
            final Map<String, List<CardResponse>> playerNameToCards) {
        this.dealerCard = dealerCard;
        this.playerNameToCards = playerNameToCards;
    }

    public static InitialCardResponse of(
            final Players players,
            final Dealer dealer) {

        final CardResponse dealerCard = CardResponse.from(dealer.getCards().get(0));
        final Map<String, List<CardResponse>> playerNameToCards = generatePlayerNameToCards(
                players.getPlayers());
        return new InitialCardResponse(dealerCard, playerNameToCards);
    }

    private static Map<String, List<CardResponse>> generatePlayerNameToCards(
            final List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> convertCards(player.getCards()),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
    }

    private static List<CardResponse> convertCards(
            final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public CardResponse getDealerCard() {
        return dealerCard;
    }

    public Map<String, List<CardResponse>> getPlayerNameToCards() {
        return playerNameToCards;
    }
}
