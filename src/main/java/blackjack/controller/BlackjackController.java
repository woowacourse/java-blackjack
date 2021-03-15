package blackjack.controller;

import blackjack.controller.dto.PlayerRequestDto;
import blackjack.domain.*;
import blackjack.domain.participant.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = initPlayers();

        initGame(dealer, players);
        if (!dealer.isBlackjack()) {
            playBlackjack(dealer, players);
        }

        OutputView.printHandResult(players.toList(), dealer);
        List<GameResultDto> gameResultDtos = BlackjackManager.getGameResult(dealer, players);
        OutputView.printGameResult(gameResultDtos);
    }

    private Players initPlayers() {
        Names names = initPlayerNames();
        Moneys moneys = initBettingMoneys(names);
        List<PlayerRequestDto> playerRequestDtos = createPlayersRequestDto(names, moneys);
        return BlackjackManager.createPlayers(playerRequestDtos);
    }

    private Names initPlayerNames() {
        try {
            return Names.of(InputView.getPlayerNames());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            return initPlayerNames();
        }
    }

    private Moneys initBettingMoneys(final Names names) {
        return new Moneys(names.stream()
                .map(this::initBettingMoney)
                .collect(Collectors.toList()));
    }

    private Money initBettingMoney(final Name name) {
        try {
            return Money.of(InputView.getBettingMoney(name.getValue()));
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            return initBettingMoney(name);
        }
    }

    private List<PlayerRequestDto> createPlayersRequestDto(Names names, Moneys moneys) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new PlayerRequestDto(names.get(i), moneys.get(i)))
                .collect(Collectors.toList());
    }

    private void initGame(final Dealer dealer, final Players players) {
        BlackjackManager.initGame(players, dealer);
        OutputView.printInitGame(players.toList(), dealer);
    }

    private void playBlackjack(final Dealer dealer, final Players players) {
        for (Player player : players.toList()) {
            playHit(player, dealer);
        }
        while (!dealer.isFinished()) {
            dealer.receiveCard(dealer.drawCard());
            OutputView.printDealerHit();
        }
    }

    private void playHit(final Player player, final Dealer dealer) {
        try {
            hitOrStay(player, dealer);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            playHit(player, dealer);
        }
    }

    private void hitOrStay(final Player player, final Dealer dealer) {
        if (player.isBlackjack()) {
            OutputView.printPlayerBlackjack(player.getName());
            return;
        }
        UserAnswer userAnswer = UserAnswer.getUserAnswer(InputView.getHitOrStay(player.getName()));
        if (userAnswer.isStay()) {
            player.stay();
            OutputView.printCards(player);
            return;
        }
        player.receiveCard(dealer.drawCard());
        OutputView.printCards(player);
        if (player.isFinished()) {
            OutputView.printPlayerBurst(player.getName());
            return;
        }
        playHit(player, dealer);
    }
}
