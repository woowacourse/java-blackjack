package domain;


import controller.PlayerInfo;

import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private final GameMembers gameMembers;

    public GameManager(List<PlayerInfo> playerInfos, CardProvider provider) {
        this.provider = provider;
        this.gameMembers = new GameMembers(playerInfos, provider);
    }

    public Dealer findDealer() {
        return gameMembers.getDealer();
    }

    public List<Player> findAllPlayers() {
        return gameMembers.findAllPlayers();
    }

    public void drawCard(Participant participant) {
        participant.drawCard(provider.provideCards(DEFAULT_DRAW_SIZE));
    }

    public Map<Player, Integer> calculateIncomes() {
        return gameMembers.calculateIncome();
    }

    public boolean isPlayerBurst(Player player) {
        return player.isBurst();
    }

    public boolean isDealerHittable(Dealer dealer) {
        return dealer.isDealerHittable();
    }
}
