package controller;

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

public class BlackJackGame {
    public void run() {
        Dealer dealer = Dealer.init();
        List<Player> players = initPlayers();
        Deck deck = Deck.init();

        play(deck, dealer, players);

        GameResults gameResults = dealer.getGameResults(players);
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

    private void play(final Deck deck, final Dealer dealer, final List<Player> players) {
        firstDraw(deck, dealer, players);
        players.forEach(player -> playForPlayer(deck, player));
        playForDealer(deck, dealer);

        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(dealer);
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream().map(PlayerHandStatusDto::of).toList();
        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }

    private void playForDealer(final Deck deck, final Dealer dealer) {
        while (dealer.isDrawable()) {
            try {
                dealer.draw(deck);
            } catch (IllegalStateException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
            OutputView.printDealerDrawMessage();
        }
    }

    private void playForPlayer(final Deck deck, final Player player) {
        while (checkAcceptDraw(player)) {
            try {
                player.draw(deck);
            } catch (IllegalStateException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
            OutputView.printPlayerDrawStatus(PlayerHandStatusDto.of(player));
        }
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

    private void firstDraw(final Deck deck, final Dealer dealer, final List<Player> players) {
        try {
            firstDrawInit(deck, dealer, players);
        } catch (IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        PlayingCardDto dealerCardDto = PlayingCardDto.of(dealer.getHandCards().get(0));
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream()
                .map(PlayerHandStatusDto::of)
                .toList();
        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
    }

    private void firstDrawInit(final Deck deck, final Dealer dealer, final List<Player> players) {
        for (int gameCount = 0; gameCount < 2; gameCount++) {
            players.forEach(player -> player.draw(deck));
            dealer.draw(deck);
        }
    }

    private List<PlayerGameResultDto> getPlayerGameResultDto(Map<Player, GameResult> playerGameResults) {
        return playerGameResults.entrySet()
                .stream()
                .map(playerGameResult -> new PlayerGameResultDto(playerGameResult.getKey().getPlayerName(), playerGameResult.getValue()))
                .toList();
    }
}
