package controller;

import domain.Deck;
import domain.GameResults;
import domain.dto.DealerHandStatusDto;
import domain.dto.PlayerHandStatusDto;
import domain.dto.PlayingCardDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.PlayerNames;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackGame {
    public void run() {
        Dealer dealer = Dealer.init();
        List<Player> players = initPlayers();
        Deck deck = Deck.init();

        play(deck, dealer, players);

        GameResults gameResults = dealer.getGameResults(players);
        OutputView.printGameResult(gameResults.dealerGameResult(), gameResults.getPlayerGameResultDto());
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

    private void play(Deck deck, Dealer dealer, List<Player> players) {
        firstDraw(deck, dealer, players);
        players.forEach(player -> playForPlayer(deck, player));
        playForDealer(deck, dealer);

        DealerHandStatusDto dealerHandStatusDto = DealerHandStatusDto.of(dealer);
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream().map(PlayerHandStatusDto::of).toList();
        OutputView.printFinalHandStatus(dealerHandStatusDto, playerHandStatusDtos);
    }

    private void playForDealer(Deck deck, Dealer dealer) {
        while (dealer.isDrawable()) {
            dealer.draw(deck);
            OutputView.printDealerDrawMessage();
        }
    }

    private void playForPlayer(Deck deck, Player player) {
        while (checkAcceptDraw(player)) {
            player.draw(deck);
            OutputView.printPlayerDrawStatus(PlayerHandStatusDto.of(player));
        }
    }

    private boolean checkAcceptDraw(Player player) {
        if (!player.isDrawable()) {
            return false;
        }
        return inputDrawDecision(player.getPlayerName());
    }

    private boolean inputDrawDecision(PlayerName playerName) {
        try {
            return InputView.inputDrawDecision(playerName);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputDrawDecision(playerName);
        }
    }

    private void firstDraw(Deck deck, Dealer dealer, List<Player> players) {
        for (int gameCount = 0; gameCount < 2; gameCount++) {
            players.forEach(player -> player.draw(deck));
            dealer.draw(deck);
        }
        PlayingCardDto dealerCardDto = PlayingCardDto.of(dealer.getHandCards().get(0));
        List<PlayerHandStatusDto> playerHandStatusDtos = players.stream()
                .map(PlayerHandStatusDto::of)
                .toList();
        OutputView.printFirstDrawStatus(dealerCardDto, playerHandStatusDtos);
    }
}
