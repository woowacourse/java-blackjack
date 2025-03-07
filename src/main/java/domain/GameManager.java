package domain;

import java.util.List;
import java.util.Map;

public class GameManager {

    private static final int INITIAL_DRAW_SIZE = 2;
    private static final int DEFAULT_DRAW_SIZE = 1;

    private final CardProvider provider;
    private Players players;
    private Dealer dealer;

    public GameManager(List<String> playerNames, CardProvider provider) {
        this.provider = provider;
        List<Player> playerList = playerNames.stream()
            .map(Name::new)
            .map(name -> new Player(name, new Cards(this.provider.provideCards(INITIAL_DRAW_SIZE))))
            .toList();
        this.players = new Players(playerList);
        this.dealer = new Dealer(new Cards(this.provider.provideCards(INITIAL_DRAW_SIZE)));
    }

    public Dealer findDealer() {
        return dealer;
    }

    public List<Player> findAllPlayers() {
        return players.findAllPlayers();
    }

    public <T extends Participant<T>> T drawCard(T participant) {
        T newParticipant = participant.createParticipant(provider.provideCards(DEFAULT_DRAW_SIZE));

        if (newParticipant instanceof Player newPlayer) {
            players = players.editPlayer((Player) participant, newPlayer);
        }
        if (newParticipant instanceof Dealer newDealer) {
            dealer = newDealer;
        }

        return newParticipant;
    }

    public Map<Player, ResultStatus> findGameResult() {
        return ResultStatus.judgeGameResult(players, dealer);
    }
}
