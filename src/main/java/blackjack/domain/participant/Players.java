package blackjack.domain.participant;

import blackjack.controller.BlackJackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchResult;

import java.util.*;

public class Players {
    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "인원 수는 2 ~ 8명입니다.";
    private static final String PLAYER_DUPLICATE_ERROR_MESSAGE = "중복된 닉네임을 사용할 수 없습니다.";
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validatePlayerCount(players);
        validateDuplicate(players);
        this.players = players;
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() < MIN_PLAYER_COUNT || player.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(List<Player> players) {
        Set<Player> set = new HashSet<>(players);
        if (players.size() != set.size()) {
            throw new IllegalArgumentException(PLAYER_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public Map<Player, MatchResult> verifyResultByCompareScore(Dealer dealer) {
        Map<Player, MatchResult> result = new LinkedHashMap<>();
        for (Player player : players) {
            result.put(player, dealer.matchGame(player));
        }
        return result;
    }

    public void eachPlayerFirstDraw(Deck deck) {
        for (Player player : players) {
            player.firstDraw(deck.drawCard(), deck.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void bettingEachPlayer() {
        players.forEach(BlackJackGame::bettingEachPlayer);
    }
}
