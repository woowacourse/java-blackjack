package BlackJack.controller;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.User.Dealer;
import BlackJack.domain.User.Player;
import BlackJack.domain.User.Players;
import BlackJack.dto.DealerResultDto;
import BlackJack.dto.PlayerResultsDto;
import BlackJack.dto.UserDto;
import BlackJack.view.InputView;
import BlackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public void run() {
        CardFactory cardFactory = new CardFactory();
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        Dealer dealer = new Dealer(cardFactory);
        Players players = Players.create(inputPlayerNames, cardFactory);
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToUserDtos(dealer, players));

        OutputView.printTotalResult(playGame(dealer, players, cardFactory));

        Map<String, String> statistics = players.getStatistics(dealer);
        OutputView.printFinalResult(PlayerResultsDto.from(statistics), DealerResultDto.from(dealer, players.size()));
    }

    private List<UserDto> playGame(Dealer dealer, Players players, CardFactory cardFactory) {
        players.getPlayers().forEach(player -> askOneMoreCard(player, cardFactory));
        while (dealer.canOneMoreCard()) {
            OutputView.printAddDealerCard();
            dealer.addCard(cardFactory);
        }
        return convertToUserDtos(dealer, players);

    }

    private void askOneMoreCard(Player player, CardFactory cardFactory) {
        while (!player.isBust() && InputView.askOneMoreCard(player.getName())) {
            player.addCard(cardFactory);
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
