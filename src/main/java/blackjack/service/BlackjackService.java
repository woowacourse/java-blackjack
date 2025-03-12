package blackjack.service;

import java.util.List;

import blackjack.domain.GameManager;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.dto.GamerDto;
import blackjack.dto.request.NamesRequestDto;
import blackjack.dto.response.FinalResultResponseDto;
import blackjack.dto.response.RoundResultsResponseDto;
import blackjack.dto.response.StartingCardsResponseDto;

public class BlackjackService {

    private GameManager gameManager;
    private Gamers gamers;

    public void setPlayer(NamesRequestDto requestDto) {
        Dealer dealer = new Dealer();
        gameManager = dealer;
        gamers = Gamers.of(dealer,
            requestDto.names().stream()
                .map(Player::new)
                .toList());
    }

    public StartingCardsResponseDto drawStartingCards() {
        gameManager.drawStartingCards(gamers.getDealer());
        for (var player : gamers.getPlayers()) {
            gameManager.drawStartingCards(player);
        }
        return StartingCardsResponseDto.of(gamers.getDealer(), gamers.getPlayers());
    }

    public List<Player> getPlayers() {
        return gamers.getPlayers();
    }

    public void drawCardFor(Player player) {
        gameManager.drawCard(player);
    }

    public GamerDto getPlayerCards(Player player) {
        return GamerDto.from(player);
    }

    public boolean canReceiveAdditionalCards(Player player) {
        return player.canReceiveAdditionalCards();
    }

    public boolean dealerCanReceiveAdditionalCards() {
        Gamer dealer = gamers.getDealer();
        return dealer.canReceiveAdditionalCards();
    }

    public void drawCardForDealer() {
        gameManager.drawCard(gamers.getDealer());
    }

    public RoundResultsResponseDto getRoundResults() {
        return RoundResultsResponseDto.of(gamers.getDealer(), gamers.getPlayers());
    }

    public FinalResultResponseDto getFinalResult() {
        return FinalResultResponseDto.of(gamers.getDealer(), gamers.getPlayers());
    }
}
