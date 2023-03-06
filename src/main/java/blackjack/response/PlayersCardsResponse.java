package blackjack.response;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayersCardsResponse {

    private final Map<String, CardsScore> playerNameToResult;

    private PlayersCardsResponse(final Map<String, CardsScore> playerNameToResult) {
        this.playerNameToResult = playerNameToResult;
    }


    public static PlayersCardsResponse from(final Players players) {
        final Map<String, CardsScore> playerNameToResult = players.getPlayers().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        player -> CardsScore.of(player.currentScore(), player),
                        (x, y) -> y,
                        LinkedHashMap::new)
                );
        return new PlayersCardsResponse(playerNameToResult);
    }

    public Map<String, CardsScore> getPlayerNameToResult() {
        return playerNameToResult;
    }

    /**
     * 고민인 부분인데, 다른 dto(PlayerCardsResponse)와 완벽하게 중복되는 코드여서, 이렇게 해도 될지 궁금합니다
     */
    public static class CardsScore {

        private final int score;
        private final List<CardResponse> cards;

        private CardsScore(final int score, final List<CardResponse> cards) {
            this.score = score;
            this.cards = cards;
        }

        static CardsScore of(final int score, final Player player) {
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
