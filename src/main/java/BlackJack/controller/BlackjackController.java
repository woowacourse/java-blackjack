package BlackJack.controller;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Result;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
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

        OutputView.printTotalResult(playGame(dealer, players));

        List<PlayerResultDto> resultDtos = calculatePlayerResult(dealer, players);
        DealerResultDto dealerDto = calculateDealerResult(dealer, players);
        OutputView.printFinalResult(resultDtos, dealerDto);
    }

    private DealerResultDto calculateDealerResult(Dealer dealer, List<Player> players) {
        int dealerLoseCount = dealer.getDealerLoseCount();
        int dealerDrawCount = dealer.getDealerDrawCount();
        DealerResultDto dealerDto = DealerResultDto.from(
                dealer.getName(),
                dealerLoseCount,
                dealerDrawCount,
                players.size() - (dealerLoseCount + dealerDrawCount));
        return dealerDto;
    }

    private List<PlayerResultDto> calculatePlayerResult(Dealer dealer, List<Player> players) {
        List<PlayerResultDto> resultPlayerDtos = new ArrayList<>();
        for (Player player : players) {
            Result compare = dealer.compare(player);
            resultPlayerDtos.add(PlayerResultDto.from(player.getName(), compare));
        }
        return resultPlayerDtos;
    }

    private List<UserDto> playGame(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            addCard(player);
        }
        while (dealer.checkScore()) {
            OutputView.printAddDealerCard();
            dealer.addCard();
        }
        return convertToListDto(dealer, players);

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
