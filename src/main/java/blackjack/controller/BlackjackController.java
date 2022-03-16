package blackjack.controller;

import blackjack.domain.Card.CardFactory;
import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;
import blackjack.domain.User.Players;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerResultsDto;
import blackjack.dto.UserDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public void run() {
        CardFactory cardFactory = new CardFactory();
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        Dealer dealer = new Dealer(cardFactory.initCards());
        Players players = Players.create(inputPlayerNames, cardFactory);
        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToUserDtos(dealer, players));

        OutputView.printTotalResult(playGame(dealer, players, cardFactory));

        Map<String, String> statistics = players.getStatistics(dealer);
        OutputView.printFinalResult(PlayerResultsDto.from(statistics), DealerResultDto.from(statistics));
    }

    private List<UserDto> playGame(Dealer dealer, Players players, CardFactory cardFactory) {

        players.getPlayers()
                .forEach(player -> askOneMoreCard(player, cardFactory));
        while (dealer.hit(cardFactory)) {
            OutputView.printAddDealerCard();
        }
        return convertToUserDtos(dealer, players);

    }

    private void askOneMoreCard(Player player, CardFactory cardFactory) {
        while (!player.isBust() && InputView.askOneMoreCard(player.getName())) {
            player.hit(cardFactory);
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
