package model;

import java.util.Collections;
import java.util.List;

public class Entrant {
    private final Dealer dealer;
    private final List<Player> players;

    public Entrant(Names names) {
        this.dealer = new Dealer();
        this.players = generatePlayers(names);
    }

    private List<Player> generatePlayers(Names names) {
        return names.getPlayerNames()
                .stream()
                .map(Player::new)
                .toList();
    }

    public void hitCardToDealer(Card card) {
        dealer.hitCard(card);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public IndividualFaceUpResult getDealerFaceUpResult() {
        return dealer.generateFaceUpResult();
    }

    public List<IndividualFaceUpResult> getPlayerFaceUpResults() {
        return players.stream()
                .map(Player::generateFaceUpResult)
                .toList();
    }

}
