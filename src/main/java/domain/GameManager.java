package domain;


import domain.card.Card;
import domain.card.CardProvider;
import domain.dto.PlayerInfo;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int DEFAULT_DRAW_SIZE = 1;
    public static final int INITIAL_DRAW_SIZE = 2;

    private final CardProvider provider;
    private Players players;
    private Dealer dealer;

    public GameManager(List<PlayerInfo> playerInfos, CardProvider provider) {
        this.provider = provider;
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
