package blackjack.gamer;

import blackjack.domain.GameRule;

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

    public void processInitialDealing() {
        List<GameParticipant> gameParticipants = getGameParticipants();

        for (int i = 0; i < GameRule.INITIAL_DEALING_CARD_COUNT.getValue(); i++) {
            gameParticipants.forEach(dealer::dealCard);
        }

        dealer.hideCard();
        gameParticipants.forEach(GameParticipant::showHand);
    }

    public void processHit() {
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
