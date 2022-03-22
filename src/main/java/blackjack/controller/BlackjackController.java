package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final Participants participants = getParticipants();
        betting(participants.getPlayers());

        final BlackjackGame blackjackGame = new BlackjackGame(participants, Deck.create());
        blackjackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);
        final List<Player> players = playersTurn(blackjackGame, participants);
        final Dealer dealer = dealerTurn(blackjackGame);

        createBlackjackProfitResult(dealer, players, createBlackjackGameResult(players, dealer));
    }

    private Participants getParticipants() {
        try {
            final List<String> playerNames = InputView.inputPlayerNames();
            final List<Player> players = playerNames.stream()
                    .map(Player::new)
                    .collect(Collectors.toUnmodifiableList());
            return new Participants(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private void betting(List<Player> players) {
        for (Player player : players) {
            bettingEachPlayer(player);
        }
    }

    private void bettingEachPlayer(Player player) {
        try {
            final int money = InputView.inputPlayerMoney(player.getName());
            player.betting(money);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            bettingEachPlayer(player);
        }
    }

    private List<Player> playersTurn(BlackjackGame blackjackGame, Participants participants) {
        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(blackjackGame, player);
        }
        return players;
    }

    private void doEachPlayerTurn(BlackjackGame blackjackGame, Player player) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            Player playerAfterGame = blackjackGame.doPlayerGame(player);
            OutputView.printNowHoldCardInfo(playerAfterGame);
        }
        player.requestStay();
    }

    private boolean getOneMoreCard(Player player) {
        try {
            final String choice = InputView.inputOneMoreCard(player.getName());
            return YesOrNo.YES == YesOrNo.find(choice);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getOneMoreCard(player);
        }
    }

    private Dealer dealerTurn(BlackjackGame blackjackGame) {
        final Dealer dealer = blackjackGame.doDealerGame();
        OutputView.printDealerReceiveCardCount(dealer);
        return dealer;
    }

    private Map<Player, BlackjackMatch> createBlackjackGameResult(List<Player> players, Dealer dealer) {
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, players);
        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();
        final Map<Player, BlackjackMatch> playersResult = blackjackGameResult.calculatePlayersResult();
        OutputView.printGameResult(dealer, players);
        OutputView.printMatchResult(dealer, dealerResult, playersResult);
        return playersResult;
    }

    private void createBlackjackProfitResult(Dealer dealer, List<Player> players, Map<Player, BlackjackMatch> result) {
        final BlackjackProfitResult blackjackProfitResult = new BlackjackProfitResult(dealer, players);
        Map<Participant, Double> profitResult = blackjackProfitResult.calculateProfitResult(result);
        OutputView.printProfitResult(profitResult);
    }
}
