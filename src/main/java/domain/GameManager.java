package domain;

import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final Players players;
    private final Dealer dealer;

    public GameManager(List<String> playerNames, CardProvider provider) {
        this.provider = provider;
        this.players = new Players(createPlayers(playerNames));
        this.dealer = new Dealer(new Cards(drawInitialCards()));
    }

    private List<Player> createPlayers(List<String> playerNames) {
        return playerNames.stream()
            .map(name -> new Player(name, new Cards(drawInitialCards())))
            .toList();
    }

    private List<Card> drawInitialCards() {
        return this.provider.provideCards(INITIAL_DRAW_SIZE);
    }

    public Dealer findDealer() {
        return dealer;
    }

    public List<Player> findAllPlayers() {
        return players.findAllPlayers();
    }

    public void drawCard(Participant participant) {
        participant.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public Map<Player, ResultStatus> findGameResult() {
        return ResultStatus.judgeGameResult(players, dealer);
    }

    public boolean isPlayerBurst(Player player) {
        return player.isBurst();
    }

    public boolean isDealerHittable(Dealer dealer) {
        return dealer.isDealerHittable();
    }
}
