package service;

import domain.PlayerGameResult;
import domain.Players;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.GameScoreDto;
import dto.GameStartDto;
import dto.PlayerResultDto;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private final Players players;
    private final Dealer dealer;
    private final Deck deck = new Deck();


    public GameService(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public GameStartDto startGame() {
        for (Player player : players) {
            player.drawInitialCards(deck.drawInitialCards());
        }
        dealer.drawInitialCards(deck.drawInitialCards());

        return GameStartDto.from(players, dealer);
    }

    public void playerHit(Player player) {
        player.draw(deck.drawCard());
    }

    public void playerStay(Player player) {
        player.stay();
    }

    public void dealerHit() {
        dealer.draw(deck.drawCard());
    }

    public void dealerStay() {
        dealer.stay();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public GameScoreDto getTotalScore() {
        return GameScoreDto.from(players, dealer);
    }

    public GameResultDto calculateResults() {
        List<PlayerResultDto> playerResultDTOs = new ArrayList<>();
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
            playerResultDTOs.add(new PlayerResultDto(player.getName(), playerResult.getValue()));
        }

        return new GameResultDto(playerResultDTOs, new DealerResultDto(dealerWinCount, dealerDrawCount, dealerLoseCount));

    }
}
