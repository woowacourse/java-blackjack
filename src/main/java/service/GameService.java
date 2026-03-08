package service;

import java.util.List;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.GameStartDTO;

import dto.ParticipantHandDTO;

public class GameService {
    private final List<Player> players;
    private final Dealer dealer;

    public GameService(List<String> playerNames) {
        this.players = participateGame(playerNames);
        this.dealer = new Dealer();
    }

    public GameStartDTO startGame() {
        for (Player player : players) {
            List<Card> firstHandCards = dealer.dealInitialCards();
            player.receiveInitialCards(firstHandCards);
        }
        dealer.receiveInitialCards(dealer.dealInitialCards());

        return GameStartDTO.from(players, dealer);
    }

    private List<Player> participateGame(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public ParticipantHandDTO playerHit(Player player) {
        player.receiveHitCard(dealer.dealHitCard());
        return new ParticipantHandDTO(player, player.getHandCards());
    }

    public ParticipantHandDTO getCurrentHand(Player player) {
        return new ParticipantHandDTO(player, player.getHandCards());
    }

    public void dealerHit() {
        dealer.receiveHitCard(dealer.dealHitCard());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
