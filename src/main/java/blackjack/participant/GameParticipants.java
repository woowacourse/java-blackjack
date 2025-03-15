package blackjack.participant;

import blackjack.GameRule;
import blackjack.result.GameStatistics;

import java.util.Collections;
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
        getGameParticipants(false).forEach(participant -> {
            while (participant.shouldHit()) {
                dealer.dealCard(participant);
                participant.showHand();
            }
        });
        dealer.openHiddenCard();
    }

    public GameStatistics calculateGameStatistics() {
        return GameStatistics.from(this);
    }

    public List<GameParticipant> getGameParticipants() {
        return getGameParticipants(true);
    }

    public List<GameParticipant> getGameParticipants(boolean dealerFirst) {
        if (dealerFirst) {
            return Stream.concat(Stream.of(dealer), players.stream())
                    .toList();
        }

        return Stream.concat(players.stream(), Stream.of(dealer))
                .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
