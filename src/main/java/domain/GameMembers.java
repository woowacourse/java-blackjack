package domain;

import controller.PlayerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMembers {

    private static final int INITIAL_DRAW_SIZE = 2;

    private Players players;
    private Dealer dealer;

    public GameMembers(List<PlayerInfo> playerInfos, CardProvider provider) {
        this.players = new Players(createPlayers(playerInfos, provider));
        this.dealer = new Dealer(drawInitialCards(provider));
    }

    private List<Player> createPlayers(List<PlayerInfo> playerInfos, CardProvider provider) {
        List<Player> createdPlayers = new ArrayList<>();
        for (PlayerInfo playerInfo : playerInfos) {
            createdPlayers.add(
                new Player(playerInfo.name(),
                    drawInitialCards(provider),
                    playerInfo.betAmount())
            );
        }
        return createdPlayers;
    }

    private List<Card> drawInitialCards(CardProvider provider) {
        return provider.provideCards(INITIAL_DRAW_SIZE);
    }

    public Map<Player, Integer> calculateIncome() {
        return players.judgeAllPlayersIncomes(dealer);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> findAllPlayers() {
        return players.findAllPlayers();
    }
}
