package model.participant;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import model.card.Card;
import model.dto.IndividualFaceUpResult;

public class Entrant {
    private final Dealer dealer;
    private final Queue<Player> players;

    public Entrant(Names names) {
        this.dealer = new Dealer();
        this.players = generatePlayers(names);
    }

    private Queue<Player> generatePlayers(Names names) {
        return names.getPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public void hitDealer(Card card) {
        dealer.hitCard(card);
    }

    public void hitPlayer(Card card){
        Player player = Objects.requireNonNull(players.poll());
        player.hitCard(card);
        players.add(player);
    }

    Queue<Player> getPlayers() {
        return players;
    }


    public IndividualFaceUpResult getDealerFaceUpResult() {
        return dealer.generateFaceUpResult();
    }

    public List<IndividualFaceUpResult> getPlayerFaceUpResults() {
        return players.stream()
                .map(Player::generateFaceUpResult)
                .toList();
    }

    public int getPlayerSize() {
        return players.size();
    }
}
