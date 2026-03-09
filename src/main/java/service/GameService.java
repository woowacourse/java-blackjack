package service;

import java.util.ArrayList;
import java.util.List;

import domain.PlayerGameResult;
import domain.Players;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;

import dto.GameStartDTO;
import dto.HandDTO;
import dto.GameScoreDTO;
import dto.PlayerResultDTO;
import dto.GameResultDTO;
import dto.DealerResultDTO;

public class GameService {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck = new Deck();


    public GameService(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public GameStartDTO startGame() {
        players.receiveInitialCards(deck);
        dealer.receiveInitialCards(deck.dealFirstHandCards());
        return GameStartDTO.from(players, dealer);
    }

    public HandDTO playerHit(Player player) {
        player.receiveHitCard(deck.drawCard());
        return getCurrentHand(player);
    }

    public HandDTO getCurrentHand(Player player) {
        return HandDTO.from(player);
    }

    public void dealerHit() {
        dealer.receiveHitCard(deck.drawCard());
    }

    public GameScoreDTO getTotalScore() {
        return GameScoreDTO.from(players, dealer);
    }

    public GameResultDTO calculateResults() {
        List<PlayerResultDTO> playerResultDTOs = new ArrayList<>();
        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;

        for (Player player : players) {
            PlayerGameResult playerResult = PlayerGameResult.from(player, dealer);
            if (playerResult == PlayerGameResult.WIN) {
                dealerLoseCount++;
            }
            if (playerResult == PlayerGameResult.DRAW) {
                dealerDrawCount++;
            }
            if (playerResult == PlayerGameResult.LOSE) {
                dealerWinCount++;
            }
            playerResultDTOs.add(new PlayerResultDTO(player.getName(), playerResult.getValue()));
        }

        return new GameResultDTO(playerResultDTOs, new DealerResultDTO(dealerWinCount, dealerDrawCount, dealerLoseCount));

    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
