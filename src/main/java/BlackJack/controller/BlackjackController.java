package BlackJack.controller;

import BlackJack.domain.*;
import BlackJack.dto.ResultDto;
import BlackJack.dto.UserDto;
import BlackJack.view.InputView;
import BlackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> inputPlayerNames = InputView.inputPlayerNames();

        Dealer dealer = new Dealer(CardFactory.drawTwoCards());
        List<Player> players = joinGame(inputPlayerNames);
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToListDto(dealer, players));
        for (Player player : players) {
            addCard(player);
        }

        while (dealer.checkScore()) {
            OutputView.printAddDealerCard();
            dealer.addCard();
        }

        List<ResultDto> resultDtos = new ArrayList<>();
//        resultDtos.add(ResultDto.from(UserDto.from(dealer), dealer.getScore()));
//
//        for (Player player : players) {
//
//            resultDtos.add(ResultDto.from(UserDto.from(player), player.getScore()));
//        }
        List<UserDto> userDtos = convertToListDto(dealer, players);

        OutputView.printTotalResult(userDtos);


//   int dealerLoseCount = 0;
//        for (Player player : players) {
//            int compare = dealer.compare(player);
//            if(compare == 1){ // 딜러가 졌다면
//                 ResultDto.from(UserDto.from(player),"승")
//            }
//
//        }

    }

    public List<Player> joinGame(List<String> inputPlayerNames) {
        List<Player> players = new ArrayList<>();
        for (String name : inputPlayerNames) {
            players.add(new Player(name, CardFactory.drawTwoCards()));
        }
        return players;
    }

    private List<UserDto> convertToListDto(Dealer dealer, List<Player> players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        for (Player player : players) {
            userDtos.add(UserDto.from(player));
        }
        return userDtos;
    }

    public void addCard(Player player) {
        while (InputView.askOneMoreCard(UserDto.from(player))) {
            player.addCard();
            OutputView.printPlayerCard(UserDto.from(player));
        }
    }

}
