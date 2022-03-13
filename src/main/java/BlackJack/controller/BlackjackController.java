package BlackJack.controller;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Game;
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


//        Dealer dealer = new Dealer(CardFactory.drawTwoCards());
//        List<Player> players = joinGame(inputPlayerNames);
//        OutputView.printDrawMessage(inputPlayerNames);
//        OutputView.printTotalUserCards(convertToListDto(dealer, players));
//
//        OutputView.printTotalResult(playGame(dealer, players));
//
//        List<PlayerResultDto> resultDtos = calculatePlayerResult(dealer, players);
//        DealerResultDto dealerDto = calculateDealerResult(dealer, players);
//        OutputView.printFinalResult(resultDtos, dealerDto);
    }

//    private DealerResultDto calculateDealerResult(Dealer dealer, List<Player> players) {
//        int dealerLoseCount = dealer.getDealerLoseCount();
//        int dealerDrawCount = dealer.getDealerDrawCount();
//        DealerResultDto dealerDto = DealerResultDto.from(
//                dealer.getName(),
//                dealerLoseCount,
//                dealerDrawCount,
//                players.size() - (dealerLoseCount + dealerDrawCount));
//        return dealerDto;
//    }
//
//    private List<PlayerResultDto> calculatePlayerResult(Dealer dealer, List<Player> players) {
//        List<PlayerResultDto> resultPlayerDtos = new ArrayList<>();
//        for (Player player : players) {
//            Result compare = dealer.compare(player);
//            resultPlayerDtos.add(PlayerResultDto.from(player.getName(), compare));
//        }
//        return resultPlayerDtos;
//    }
//
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

    private List<UserDto> convertToListDto(Dealer dealer, Players players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        for (Player player : players.getPlayers()) {
            userDtos.add(UserDto.from(player));
        }
        return userDtos;
    }

}
