package controller;

import domain.*;
import domain.dto.BettingResult;
import domain.dto.DealerHandStatusDto;
import domain.dto.PlayerHandStatusDto;
import domain.dto.PlayingCardDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {
    public void run() {
        Dealer dealer = Dealer.init();
        List<Player> players = initPlayers();
        BlackJackGame blackJackGame = new BlackJackGame(Deck.init());
        Betting betting = initBettingForPlayers(players);

        play(blackJackGame, dealer, players);

        finish(blackJackGame, betting, dealer, players);
    }

    private List<Player> initPlayers() {
        return initPlayerNames().values()
                .stream()
                .map(Player::of)
                .toList();
    }

    private PlayerNames initPlayerNames() {
        try {
            List<String> inputPlayerNames = InputView.inputPlayerNames();
            return PlayerNames.of(inputPlayerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return initPlayerNames();
        }
    }

    private void play(final BlackJackGame blackJackGame,
                      final Dealer dealer, final List<Player> players) {
        firstDraw(blackJackGame, dealer, players);
        players.forEach(player -> playForPlayer(blackJackGame, player));
        playForDealer(blackJackGame, dealer);

        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(dealer);
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream()
                .map(PlayerHandStatusDto::of)
                .toList();
        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }

    private Betting initBettingForPlayers(final List<Player> players) {
        Map<PlayerName, BettingAmount> betting = new HashMap<>();
        players.forEach(player -> betting.put(player.getPlayerName(), initAmount(player)));
        return new Betting(betting);
    }

    private BettingAmount initAmount(final Player player) {
        try {
            String inputAmount = InputView.inputAmount(player.getPlayerName().value());
            return new BettingAmount(inputAmount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return initAmount(player);
        }
    }

    private void firstDraw(final BlackJackGame blackJackGame, final Dealer dealer, final List<Player> players) {
        try {
            blackJackGame.firstDraw(dealer, players);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        PlayingCardDto dealerCardDto = PlayingCardDto.of(dealer.getHandCards().get(0));
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream()
                .map(PlayerHandStatusDto::of)
                .toList();
        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
    }

    private void playForPlayer(final BlackJackGame blackJackGame, final Player player) {
        while (checkAcceptDraw(player)) {
            playOneTurnForPlayer(blackJackGame, player);
        }
    }

    private void playOneTurnForPlayer(final BlackJackGame blackJackGame, final Player player) {
        try {
            blackJackGame.drawForParticipant(player);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        OutputView.printPlayerDrawStatus(PlayerHandStatusDto.of(player));
    }

    private boolean checkAcceptDraw(final Player player) {
        if (!player.isDrawable()) {
            return false;
        }
        return inputDrawDecision(player.getPlayerName());
    }

    private boolean inputDrawDecision(final PlayerName playerName) {
        try {
            return InputView.inputDrawDecision(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputDrawDecision(playerName);
        }
    }

    private void playForDealer(final BlackJackGame blackJackGame, final Dealer dealer) {
        while (dealer.isDrawable()) {
            playOneTurnForDealer(blackJackGame, dealer);
        }
    }

    private static void playOneTurnForDealer(final BlackJackGame blackJackGame, final Dealer dealer) {
        try {
            blackJackGame.drawForParticipant(dealer);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        OutputView.printDealerDrawMessage();
    }

    private void finish(final BlackJackGame blackJackGame, final Betting betting,
                        final Dealer dealer, final List<Player> players) {
        GameResults gameResults = blackJackGame.getGameResults(dealer, players);
        BettingResult bettingResult = new BettingResult(gameResults.calculateBettingOnPlayers(betting));
        OutputView.printDealerBettingResult(bettingResult.getDealerBettingResult());
        OutputView.printPlayerBettingResult(bettingResult);
    }
}
