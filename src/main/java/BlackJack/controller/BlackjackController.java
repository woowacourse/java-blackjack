package BlackJack.controller;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.DealerScore;
import BlackJack.domain.Game;
import BlackJack.domain.PlayerScore;
import BlackJack.domain.Result;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import BlackJack.domain.User.Players;
import BlackJack.domain.User.User;
import BlackJack.dto.DealerResultDto;
import BlackJack.dto.PlayerResultDto;
import BlackJack.dto.UserDto;
import BlackJack.view.InputView;
import BlackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        Game game = new Game(inputPlayerNames,new Dealer());
        game.handOutInitCard();
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToListDto(game.getDealer(), game.getPlayers()));

        game.checkPlayerAndDealerIsBlackJack();
        OutputView.printTotalResult(playGame(game.getDealer(), game.getPlayers()));

        makeResults(game);

        OutputView.printFinalResult(
                convertToPlayerResultDtos(game.getPlayerScore()),
                convertToDealerResultDto(game.getDealerScore())
        );
    }

    private void makeResults(Game game) {
        game.makePlayerResult();
        game.makeDealerResult(game.getPlayerScore());
    }

    private void checkDealerIsBlackJack(Game game) {
        if(game.checkDealerIsBlackJack()){
            OutputView.printFinalResult(
                convertToPlayerResultDtos(game.getPlayerScore()),
                convertToDealerResultDto(game.getDealerScore())
            );
        }
    }


    private List<UserDto> playGame(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            addCardPerPlayer(player);
        }
        while (dealer.checkPossibleAdd()) {
            OutputView.printAddDealerCard();
            dealer.addCard();
        }
        return convertToListDto(dealer, players);
    }

    private void addCardPerPlayer(Player player) {
        while (InputView.askOneMoreCard(UserDto.from(player))) {
            player.addCard();
            OutputView.printPlayerCard(UserDto.from(player));
        }
    }

    private List<UserDto> convertToToTalUserDto(Dealer dealer, Players players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        for (Player player : players.getPlayers()) {
            userDtos.add(UserDto.from(player));
        }
        return userDtos;
    }

    private DealerResultDto convertToDealerResultDto(DealerScore dealerScore) {
        Integer lose = dealerScore.getDealerScore().get(Result.LOSE);
        Integer draw = dealerScore.getDealerScore().get(Result.DRAW);
        Integer win = dealerScore.getDealerScore().get(Result.WIN);
        DealerResultDto dealerDto = DealerResultDto.from(lose, draw, win);
        return dealerDto;
    }

    private List<PlayerResultDto> convertToPlayerResultDtos(PlayerScore playerScore) {
        List<PlayerResultDto> resultPlayerDtos = new ArrayList<>();
        for (Map.Entry<String, Result> resultEntry : playerScore.getPlayersScore().entrySet()) {
            PlayerResultDto.from(resultEntry.getKey(), resultEntry.getValue());
        }
        return resultPlayerDtos;
    }
}
