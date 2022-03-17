package blackjack.controller;

import blackjack.domain.Card.Deck;
import blackjack.domain.PlayerResult;
import blackjack.service.batchService;
import blackjack.domain.User.Betting;
import blackjack.domain.User.Dealer;
import blackjack.domain.User.Player;
import blackjack.domain.User.Players;
import blackjack.dto.UserDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        List<Betting> bettings = startBettings(inputPlayerNames);

        Dealer dealer = new Dealer(deck.drawInitCards());
        Players players = Players.create(inputPlayerNames, bettings, deck);

        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(convertToUserDtos(dealer, players));

        OutputView.printTotalResult(playGame(dealer, players, deck));

        Map<Player, PlayerResult> statistics = players.getStatistics(dealer);
        OutputView.printFinalResult(batchService.calculate(statistics));
    }

    private List<Betting> startBettings(List<String> inputPlayerNames) {
        List<Betting> bettings = new ArrayList<>();
        for (String inputPlayerName : inputPlayerNames) {
            bettings.add(Betting.from(InputView.askBetAmount(inputPlayerName)));
        }
        return bettings;
    }

    private List<UserDto> playGame(Dealer dealer, Players players, Deck deck) {

        players.getPlayers()
                .forEach(player -> askOneMoreCard(player, deck));
        dealer.hit(deck);
        OutputView.printAddDealerCard();
        return convertToUserDtos(dealer, players);
    }

    private void askOneMoreCard(Player player, Deck deck) {
        while (!player.isBust() && InputView.askOneMoreCard(player.getName())) {
            player.hit(deck);
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
