package domain;

import java.util.List;

public class GameManager {

    private CardProvider provider;
    private Players players;
    private Dealer dealer;

    public GameManager(List<String> playerNames, CardProvider provider) {
        this.provider = provider;
        List<Player> playerList = playerNames.stream()
            .map(Name::new)
            .map(name -> new Player(name, new Cards(provider.provideCards(2))))
            .toList();
        this.players = new Players(playerList);
        this.dealer = new Dealer(new Cards(provider.provideCards(2)));
    }


}
