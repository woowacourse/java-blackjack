package blackjack.response;

import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCardsResponse {

    private final String name;
    private final List<CardResponse> cards;

    private PlayerCardsResponse(final String name, final List<CardResponse> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerCardsResponse of(final String playerName, final Player player) {
        final List<CardResponse> cardResponses = player.getCards().stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
        return new PlayerCardsResponse(playerName, cardResponses);
    }

    public String getName() {
        return name;
    }

    public List<CardResponse> getCards() {
        return cards;
    }
}
