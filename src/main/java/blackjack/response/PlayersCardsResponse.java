package blackjack.response;

import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersCardsResponse {

    private final Map<String, CardsScore> playerNameToResult;

    public PlayersCardsResponse(final Map<String, CardsScore> playerNameToResult) {
        this.playerNameToResult = playerNameToResult;
    }

    public Map<String, CardsScore> getPlayerNameToResult() {
        return playerNameToResult;
    }

    public static class CardsScore {

        private final int score;
        private final List<CardResponse> cards;

        private CardsScore(final int score, final List<CardResponse> cards) {
            this.score = score;
            this.cards = cards;
        }

        public static CardsScore of(final int score, final Player player) {
            final List<CardResponse> cardResponses = player.getCards().stream()
                    .map(CardResponse::from)
                    .collect(Collectors.toList());
            return new CardsScore(score, cardResponses);
        }

        public int getScore() {
            return score;
        }

        public List<CardResponse> getCards() {
            return cards;
        }
    }
}
