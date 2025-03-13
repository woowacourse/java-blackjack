package domain;


import controller.PlayerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final Players players;
    private final Dealer dealer;

    public GameManager(List<PlayerInfo> playerInfos, CardProvider provider) {
        this.provider = provider;
        this.players = new Players(createPlayers(playerInfos));
        this.dealer = new Dealer(drawInitialCards());
    }

    private List<Player> createPlayers(List<PlayerInfo> playerInfos) {
        List<Player> createdPlayers = new ArrayList<>();
        for (PlayerInfo playerInfo : playerInfos) {
            createdPlayers.add(
                new Player(playerInfo.name(),
                    provider.provideCards(INITIAL_DRAW_SIZE),
                    playerInfo.betAmount())
            );
        }
        return createdPlayers;
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

    public Map<Player, Integer> calculateIncomes() {
        return players.judgeAllPlayersIncomes(dealer);
    }

    public boolean isPlayerBurst(Player player) {
        return player.isBurst();
    }

    public boolean isDealerHittable(Dealer dealer) {
        return dealer.isDealerHittable();
    }
}
