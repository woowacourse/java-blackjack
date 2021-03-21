package blackjack.controller;

import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.ParticipantResponseDto;
import blackjack.controller.dto.PlayerRequestDto;
import blackjack.domain.*;
import blackjack.domain.participant.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackjackController {

    private BlackjackManager blackjackManager;

    public void play() {
        blackjackManager = new BlackjackManager(initPlayers());

        initGame();
        if (!blackjackManager.isDealerBlackjack()) {
            playBlackjack();
        }

        OutputView.printHandResult(blackjackManager.createParticipantsResponseDto());
        List<GameResultDto> gameResultDtos = blackjackManager.getGameResult();
        OutputView.printGameResult(gameResultDtos);
    }

    private List<PlayerRequestDto> initPlayers() {
        Names names = initPlayerNames();
        Moneys moneys = initBettingMoneys(names);
        return createPlayersRequestDto(names, moneys);
    }

    private List<PlayerRequestDto> createPlayersRequestDto(Names names, Moneys moneys) {
        return IntStream.range(0, names.size())
                .mapToObj(i -> new PlayerRequestDto(names.get(i), moneys.get(i)))
                .collect(Collectors.toList());
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

    private void initGame() {
        OutputView.printInitGame(blackjackManager.initGame());
    }

    private void playBlackjack() {
        while(!blackjackManager.isFinishedAllOfPlayer()) {
            playPlayersTurn();
        }
        while (blackjackManager.isDealerHit()) {
            blackjackManager.dealerDrawCard();
            OutputView.printDealerHit();
        }
    }

    private void playPlayersTurn() {
        ParticipantResponseDto playerDto = blackjackManager.getCurrentPlayerDto();
        if (blackjackManager.isCurrentPlayerBlackJack()) {
            OutputView.printPlayerBlackjack(playerDto.getName());
            blackjackManager.nextTurn();
            return;
        }
        blackjackManager.playerHitOrStay(getUserAnswer(playerDto.getName()));
        OutputView.printCards(blackjackManager.getCurrentPlayerDto());
        currentPlayerFinished();
    }

    private void currentPlayerFinished() {
        ParticipantResponseDto playerDto = blackjackManager.getCurrentPlayerDto();
        if (!blackjackManager.isCurrentPlayerFinished()) {
            return;
        }
        if (blackjackManager.isCurrentPlayerBust()) {
            OutputView.printPlayerBurst(playerDto.getName());
        }
        blackjackManager.nextTurn();
    }

    private UserAnswer getUserAnswer(String playerName) {
        try {
            return UserAnswer.getUserAnswer(InputView.getHitOrStay(playerName));
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printException(e);
            return getUserAnswer(playerName);
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
            OutputView.printCards(ParticipantResponseDto.from(player));
            return;
        }
        player.receiveCard(dealer.drawCard());
        OutputView.printCards(ParticipantResponseDto.from(player));
        if (player.isFinished()) {
            OutputView.printPlayerBurst(player.getName());
            return;
        }
        playHit(player, dealer);
    }
}
