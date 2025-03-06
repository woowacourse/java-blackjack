package domain;

import java.util.List;
import java.util.Map;

public class GameManager {

    private CardProvider provider;
    private Players players;
    private Dealer dealer;

    public GameManager(List<String> playerNames, CardProvider provider) {
        this.provider = provider;
        List<Player> playerList = playerNames.stream()
            .map(Name::new)
            .map(name -> new Player(name, new Cards(this.provider.provideCards(2))))
            .toList();
        this.players = new Players(playerList);
        this.dealer = new Dealer(new Cards(this.provider.provideCards(2)));
    }

    public Dealer findDealer() {
        return dealer;
    }

    public List<Player> findAllPlayers() {
        return players.findAllPlayers();
    }

    public Player findPlayer(String name) {
        return players.findPlayer(name);
    }

    public Participant<?> drawCard(Participant<?> participant) {
        if (participant instanceof Player player) {
            Player newPlayer = player.drawCard(provider.provideCards(1));
            players = players.editPlayer(player, newPlayer);
            return newPlayer;
        }
        Dealer newDealer = (Dealer) participant;
        dealer = newDealer.drawCard(provider.provideCards(1));
        return dealer;
    }

    public Map<String, ResultStatus> findGameResult() {
        return ResultStatus.judgeGameResult(players, dealer);
    }
}
