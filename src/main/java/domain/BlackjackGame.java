package domain;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    private final List<Player> players;
    private final Dealer dealer;

    public BlackjackGame(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public BlackjackGame() {
        players = new ArrayList<>();
        dealer = new Dealer();
    }

    public void registPlayer(Player player){
        players.add(player);
    }

}
