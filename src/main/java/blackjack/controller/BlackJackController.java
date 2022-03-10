package blackjack.controller;

import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.GamerDto;
import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.GameResult;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    private final BlackJackGame blackJackGame;

    public BlackJackController(List<String> names) {
        blackJackGame = new BlackJackGame(names);
        blackJackGame.distributeFirstCards();
    }

    public GamerDto getDealerDto() {
        Dealer dealer = blackJackGame.getDealer();
        return new GamerDto(dealer);
    }

    public List<GamerDto> getPlayerDtos() {
        List<Player> players = blackJackGame.getPlayers();
        return players.stream()
                .map((GamerDto::new))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isDrawPossible(String name, String answer) {
        return !blackJackGame.isBurst(name) && Answer.from(answer).isYes();
    }

    public void requestPlayerDrawCard(String name) {
        blackJackGame.distributeCardToPlayer(name);
    }

    public GamerDto findPlayerByName(String name) {
        Player player = blackJackGame.findPlayerByName(name);
        return new GamerDto(player);
    }

    public int getDealerAdditionalCardCount() {
        return blackJackGame.distributeAdditionalToDealer();
    }

    public GameResultDto getGamerResult() {
        GameResult result = blackJackGame.createResult();
        return new GameResultDto(result);
    }
}
