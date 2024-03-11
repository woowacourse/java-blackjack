package controller;

import domain.BlackJackGame;
import domain.Deck;
import domain.GameResults;
import domain.constant.GameResult;
import domain.dto.DealerHandStatusDto;
import domain.dto.PlayerGameResultDto;
import domain.dto.PlayerHandStatusDto;
import domain.dto.PlayingCardDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {
    public void run() {
        Dealer dealer = Dealer.init();
        List<Player> players = initPlayers();
        BlackJackGame blackJackGame = new BlackJackGame(Deck.init());

        play(blackJackGame, dealer, players);

        GameResults gameResults = blackJackGame.getGameResults(dealer, players);
        OutputView.printGameResult(gameResults.dealerGameResult(), getPlayerGameResultDto(gameResults.playerGameResults()));
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

    private void play(final BlackJackGame blackJackGame, final Dealer dealer, final List<Player> players) {
        firstDraw(blackJackGame, dealer, players);
        players.forEach(player -> playForPlayer(blackJackGame, player));
        playForDealer(blackJackGame, dealer);

        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(dealer);
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream().map(PlayerHandStatusDto::of).toList();
        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
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

    private static void playOneTurnForPlayer(final BlackJackGame blackJackGame, final Player player) {
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

    private List<PlayerGameResultDto> getPlayerGameResultDto(final Map<Player, GameResult> playerGameResults) {
        return playerGameResults.entrySet()
                .stream()
                .map(playerGameResult -> new PlayerGameResultDto(playerGameResult.getKey().getPlayerName(), playerGameResult.getValue()))
                .toList();
    }
}
