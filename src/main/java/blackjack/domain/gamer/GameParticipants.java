package blackjack.domain.gamer;

import blackjack.domain.GameRule;
import blackjack.domain.result.GameStatistics;

import java.util.List;
import java.util.stream.Stream;

public class GameParticipants {

    private final Dealer dealer;
    private final List<Player> players;

    private GameParticipants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameParticipants of(List<Player> players, Dealer dealer) {
        return new GameParticipants(dealer, players);
    }

    public void dealInitialCards() {
        List<GameParticipant> gameParticipants = getGameParticipants();

        for (int i = 0; i < GameRule.INITIAL_DEALING_CARD_COUNT.getValue(); i++) {
            gameParticipants.forEach(dealer::dealCard);
        }

        dealer.hideCard();
    }

    public void executeHitPhase() {
        players.forEach(participant -> {
                    while (participant.shouldHit()) {
                        dealer.dealCard(participant);
                        participant.showHand();
                    }
                }
        );

        while (dealer.shouldHit()) {
            dealer.dealCard(dealer);
        }

        dealer.openHiddenCard();
    }

    public GameStatistics calculateGameStatistics() {
        GameStatistics statistics = GameStatistics.initialize(this);

        getPlayers().forEach(player ->
                statistics.markResult(player, dealer, player.judgeResult(dealer)));

        return statistics;
    }

    public List<GameParticipant> getGameParticipants() {
        return Stream.concat(Stream.of(dealer), players.stream())
                .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
