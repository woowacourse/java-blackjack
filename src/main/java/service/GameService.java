package service;

import java.util.ArrayList;
import java.util.List;

import domain.PlayerGameResult;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import dto.*;

public class GameService {
    private final List<Player> players;
    private final Dealer dealer;

    public GameService(List<String> playerNames) {
        this.players = participateGame(playerNames);
        this.dealer = new Dealer();
    }

    private List<Player> participateGame(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    public GameStartDTO startGame() {
        for (Player player : players) {
            List<Card> firstHandCards = dealer.dealInitialCards();
            player.receiveInitialCards(firstHandCards);
        }
        dealer.receiveInitialCards(dealer.dealInitialCards());

        return GameStartDTO.from(players, dealer);
    }

    public HandDTO playerHit(Player player) {
        player.receiveHitCard(dealer.dealHitCard());
        return getCurrentHand(player);
    }

    public HandDTO getCurrentHand(Player player) {
        return HandDTO.from(player);
    }

    public void dealerHit() {
        dealer.receiveHitCard(dealer.dealHitCard());
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

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
