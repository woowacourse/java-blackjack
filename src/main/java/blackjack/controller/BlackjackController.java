package blackjack.controller;

import blackjack.domain.PlayerResult;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Bet;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.UserScoreDto;
import blackjack.service.batchService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        List<String> inputPlayerNames = InputView.inputPlayerNames();
        Map<String, Bet> playerBets = startBettings(inputPlayerNames);
        Dealer dealer = new Dealer(deck.drawInitCards());
        Players players = Players.create(playerBets, deck);

        OutputView.printDrawMessage(inputPlayerNames);
        OutputView.printTotalUserCards(CardDto.from(dealer.getOneCard()), converToPlayerDtos(players));

        List<UserScoreDto> userScoreDtos = playGame(dealer, players, deck);
        finishGame(dealer, players, userScoreDtos);
    }

    private List<PlayerDto> converToPlayerDtos(Players players) {
        return players.getPlayers().stream()
                .map(PlayerDto::from)
                .collect(Collectors.toList());
    }

    private void finishGame(Dealer dealer, Players players, List<UserScoreDto> userScoreDtos) {
        OutputView.printTotalResult(userScoreDtos);
        Map<Player, PlayerResult> statistics = players.getStatistics(dealer);
        OutputView.printFinalResult(batchService.calculate(statistics));
    }

    private Map<String, Bet> startBettings(List<String> inputPlayerNames) {
        return inputPlayerNames.stream()
                .collect(Collectors.toMap(inputPlayerName -> inputPlayerName,
                        inputPlayerName -> Bet.from(InputView.askBetAmount(inputPlayerName))
                        , (a, b) -> b,
                        LinkedHashMap::new));
    }

    private List<UserScoreDto> playGame(Dealer dealer, Players players, Deck deck) {
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

    private List<UserScoreDto> convertToUserDtos(Dealer dealer, Players players) {
        List<UserScoreDto> userScoreDtos = new ArrayList<>();
        userScoreDtos.add(UserScoreDto.from(dealer));
        for (Player player : players.getPlayers()) {
            UserScoreDto from = UserScoreDto.from(player);
            userScoreDtos.add(from);
        }
        return userScoreDtos;
    }
}
