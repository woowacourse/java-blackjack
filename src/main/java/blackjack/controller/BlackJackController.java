package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Cards;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void start() {
        try {
            BlackJackGame blackJackGame = new BlackJackGame(playersName());
            Players players = blackJackGame.getPlayers();
            bettingPlayers(players);
            distributeCards(blackJackGame);
            playersTurn(players, blackJackGame);
            dealerTurn(blackJackGame.getDealer(), blackJackGame);
            showProfitResult(blackJackGame);
        } catch (RuntimeException e) {
            OutputView.printError(e.getMessage());
            start();
        }
    }

    private List<String> playersName() {
        try {
            OutputView.enterPlayersName();
            return InputView.inputName();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return playersName();
        }
    }

    private void bettingPlayers(Players players) {
        players.getPlayers()
                .forEach(this::bettingEachPlayer);
    }

    public void bettingEachPlayer(Player player) {
        try {
            OutputView.askBettingMoney(player);
            player.betting(new BettingMoney(InputView.inputBettingMoney()));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            bettingEachPlayer(player);
        }
    }

    private void distributeCards(BlackJackGame blackJackGame) {
        blackJackGame.distributeCards();
        OutputView.distributeFirstTwoCard(playersDto(blackJackGame.getPlayers()), dealerDto(blackJackGame.getDealer()));
    }

    private void playersTurn(Players players, BlackJackGame blackJackGame) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(player, blackJackGame);
        }
    }

    private void eachPlayerTurn(Player player, BlackJackGame blackJackGame) {
        while (player.canDraw() && askDrawCard(player)) {
            player.draw(blackJackGame.drawOneCard());
            OutputView.showCards(playerDto(player));
        }
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(playerDto(player));
            boolean wantDraw = InputView.inputAnswer();
            playerWantStopDraw(player, wantDraw);
            return wantDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void playerWantStopDraw(Player player, boolean wantDraw) {
        if (!wantDraw) {
            OutputView.showCards(playerDto(player));
            player.stay();
        }
    }

    private void dealerTurn(Dealer dealer, BlackJackGame blackJackGame) {
        while (dealer.canDraw()) {
            dealer.draw(blackJackGame.drawOneCard());
            OutputView.dealerReceiveOneCard();
        }
        if (dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showProfitResult(BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        OutputView.showAllCards(playersDto(players), dealerDto(dealer));
        OutputView.showFinalProfitResult(blackJackGame.calculateProfit(players.verifyResultByCompareScore(dealer)));
    }

    public List<ParticipantDto> playersDto(Players players) {
        return players.getPlayers().stream()
                .map(this::toParticipantDto)
                .collect(Collectors.toList());
    }

    private ParticipantDto toParticipantDto(Participant participant) {
        Cards cards = participant.getCurrentCards();
        return new ParticipantDto(participant.getName(), cards, cards.calculateScore());
    }

    private ParticipantDto dealerDto(Dealer dealer) {
        return toParticipantDto(dealer);
    }

    private ParticipantDto playerDto(Player player) {
        return toParticipantDto(player);
    }
}
