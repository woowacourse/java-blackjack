package BlackJack.controller;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Result;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import BlackJack.domain.User.Players;
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
        Dealer dealer = new Dealer(CardFactory.initCards());
        Players players = Players.join(inputPlayerNames);
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToUserDtos(dealer, players));

        OutputView.printTotalResult(playGame(dealer, players));

        List<PlayerResultDto> resultDtos = calculatePlayerResult(dealer, players);
        DealerResultDto dealerDto = calculateDealerResult(dealer, players);
        OutputView.printFinalResult(resultDtos, dealerDto);
    }

    private DealerResultDto calculateDealerResult(Dealer dealer, Players players) {
        int dealerLoseCount = dealer.getDealerLoseCount();
        int dealerDrawCount = dealer.getDealerDrawCount();
        return DealerResultDto.from(
                dealer.getName(),
                dealerLoseCount,
                dealerDrawCount,
                players.size() - (dealerLoseCount + dealerDrawCount));
    }

    private List<PlayerResultDto> calculatePlayerResult(Dealer dealer, Players players) {
        List<PlayerResultDto> resultPlayerDtos = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            Result compare = dealer.compare(player);
            resultPlayerDtos.add(PlayerResultDto.from(player.getName(), compare));
        }
        return resultPlayerDtos;
    }

    private List<UserDto> playGame(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            askOneMoreCard(player);
        }
        while (dealer.checkScore()) {
            OutputView.printAddDealerCard();
            dealer.addCard();
        }
        return convertToUserDtos(dealer, players);

    }

    private void askOneMoreCard(Player player) {
        while (InputView.askOneMoreCard(player.getName())) {
            player.addCard();
            OutputView.printPlayerCard(UserDto.from(player));
        }
    }

    private List<UserDto> convertToUserDtos(Dealer dealer, Players players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        players.getPlayers().stream()
                .map(UserDto::from)
                .forEach(userDtos::add);
        return userDtos;
    }
}
