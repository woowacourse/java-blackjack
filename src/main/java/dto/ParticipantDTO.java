package dto;

import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDTO {

    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();


    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = (Dealer) dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
