package BlackJack.controller;

import BlackJack.domain.*;
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

        List<UserDto> userDtos = convertToListDto(dealer, players);

        OutputView.printTotalResult(userDtos);

        int dealerLoseCount = 0;
        int dealerDrawCount = 0;
        List<PlayerResultDto> resultDtos = new ArrayList<>();
        for (Player player : players) {
            Result compare = dealer.compare(player);

            if (compare == Result.WIN) {
                dealerLoseCount++;
            }
            if( compare == Result.DRAW) {
                dealerDrawCount++;
            }
            resultDtos.add(PlayerResultDto.from(player.getName(), compare));
        }

        DealerResultDto dealerDto = DealerResultDto.from(dealer.getName(), dealerLoseCount, dealerDrawCount,
                players.size() - (dealerLoseCount + dealerDrawCount));
        OutputView.printFinalResult(resultDtos, dealerDto);

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
