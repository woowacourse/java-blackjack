package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.GameResult;
import dto.PlayerResult;

import java.util.List;

public class BlackjackGame {
    public static final int INITIAL_HAND_SIZE = 2;

    private final Players players;
    private final Dealer dealer;

    public BlackjackGame(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        initGame();
    }

    private void initGame() {
        initPlayers();
        initDealer();
    }

    private void initPlayers() {
        for (final Player player : players.getPlayers()) {
            player.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
        }
    }

    private void initDealer() {
        dealer.drawCards(dealer.drawCards(INITIAL_HAND_SIZE));
    }

    public void dealCard(final Participant participant) {
        participant.drawCard(dealer.drawSingleCard());
    }

    public GameResult finishGame() {
        final List<PlayerResult> playerResults = createPlayerResults();
        final PlayerResult dealerResult = createDealerResult();
        return new GameResult(playerResults, dealerResult);
    }

    private PlayerResult createDealerResult() {
        return new PlayerResult(dealer.name(), -players.totalProfit(dealer));
    }

    private List<PlayerResult> createPlayerResults() {
        return players.getPlayers().stream()
                .map(player -> new PlayerResult(player.name(), player.profit(dealer)))
                .toList();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
