package blackjack.model.player;

import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerCardsOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.model.betting.Betting;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.dealer.Dealer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {
    private static final String INVALID_NAMES_COUNT = "참여자 수는 1명 이상이어야 합니다.";
    private static final String DUPLICATED_NAMES = "참여자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validate(playerNames);
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void validate(final List<String> playerNames) {
        validatePlayerNamesCount(playerNames);
        validateDuplicatedPlayerNames(playerNames);
    }

    private void validatePlayerNamesCount(final List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAMES_COUNT);
        }
    }

    private void validateDuplicatedPlayerNames(final List<String> playerNames) {
        Set<String> uniquePlayerNames = new HashSet<>(playerNames);
        if (uniquePlayerNames.size() != playerNames.size()) {
            throw new IllegalArgumentException(DUPLICATED_NAMES);
        }
    }

    public void dealCards(final CardGenerator cardGenerator) {
        players.forEach(player -> player.dealCards(cardGenerator));
    }

    public List<PlayerFinalCardsOutcome> captureFinalCardsOutcomes() {
        return players.stream()
                .map(PlayerFinalCardsOutcome::from)
                .toList();
    }

    public List<PlayerCardsOutcome> captureCardsOutcomes() {
        return players.stream()
                .map(PlayerCardsOutcome::from)
                .toList();
    }

    public List<PlayerBettingProfitOutcome> calculateBettingProfits(final Betting betting, final Dealer dealer) {
        return players.stream()
                .map(player -> betting.calculatePlayerBettingProfit(
                        player.getName(), player.determineMatchResult(dealer)
                ))
                .toList();
    }

    public boolean isAllBlackJack() {
        return players.stream()
                .allMatch(Player::isBlackjack);
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
