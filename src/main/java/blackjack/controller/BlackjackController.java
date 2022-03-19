package blackjack.controller;

import blackjack.domain.PlayerResult;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Bet;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.UserDto;
import blackjack.service.batchService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        List<Bet> bets = startBettings(inputPlayerNames);
        Dealer dealer = new Dealer(deck.drawInitCards());
        Players players = Players.create(inputPlayerNames, bets, deck);

        OutputView.printDrawMessage(inputPlayerNames);


        OutputView.printTotalUserCards(CardDto.from(dealer.getOneCard()), converToPlayerDtos(players));

        List<UserDto> userDtos = playGame(dealer, players, deck);
        finishGame(dealer, players, userDtos);
    }

    private List<PlayerDto> converToPlayerDtos(Players players) {
        return players.getPlayers().stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

    private void finishGame(Dealer dealer, Players players, List<UserDto> userDtos) {
        OutputView.printTotalResult(userDtos);
        Map<Player, PlayerResult> statistics = players.getStatistics(dealer);
        OutputView.printFinalResult(batchService.calculate(statistics));
    }

    private List<Bet> startBettings(List<String> inputPlayerNames) {
        List<Bet> bets = new ArrayList<>();
        for (String inputPlayerName : inputPlayerNames) {
            bets.add(Bet.from(InputView.askBetAmount(inputPlayerName)));
        }
        return bets;
    }

    private List<UserDto> playGame(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            askOneMoreCard(player, deck);
        }
        while (dealer.isHit()) {
            OutputView.printAddDealerCard();
            dealer.addCard(deck.drawOneCard());
        }
        return convertToUserDtos(dealer, players);
    }

    private void askOneMoreCard(Player player, Deck deck) {
        while (player.isHit() && InputView.askOneMoreCard(player.getName())) {
            player.addCard(deck.drawOneCard());
            OutputView.printPlayerCard(PlayerDto.from(player));
        }
    }

    private List<UserDto> convertToUserDtos(Dealer dealer, Players players) {
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(UserDto.from(dealer));
        for (Player player : players.getPlayers()) {
            UserDto from = UserDto.from(player);
            userDtos.add(from);
        }
        return userDtos;
    }
}
