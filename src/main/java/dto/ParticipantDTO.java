package dto;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getPlayerNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    public List<List<Card>> getPlayerHands() {
        return players.stream().map(Player::showHand).collect(Collectors.toList());
    }
}
