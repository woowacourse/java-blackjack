package blackjack.domain.participant;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Deck;

import java.util.*;

public class Players {

    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validateSize(playerNames);
        validateDuplicateName(playerNames);
        this.players = from(playerNames);
    }

    public void initialHands(Deck deck, int initialCardCount) {
        for (Player player : players) {
            for (int i = 0; i < initialCardCount; i++) {
                player.receive(deck.drawCard());
            }
        }
    }

    public Map<String, MatchResult> calculateResult(Dealer dealer) {
        Map<String, MatchResult> playersResult = new HashMap<>();

        for (Player player : players) {
            player.compareWithDealer(dealer, playersResult);
        }

        return playersResult;
    }

    private List<Player> from(List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .map(Player::new)
                .toList();
    }

    private void validateDuplicateName(List<String> playerNames) {
        Set<String> uniqueNames = new HashSet<>(playerNames);

        if (uniqueNames.size() != playerNames.size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_SIZE)
            throw new IllegalArgumentException("플레이어 인원 수는 5명 이하여야 합니다.");
    }

    public List<Player> getPlayers() {
        return players;
    }
}
