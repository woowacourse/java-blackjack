package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.BlackjackGameResult;
import blackjack.domain.result.BlackjackMatch;
import blackjack.domain.result.YesOrNo;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        final Participants participants = getParticipants();
        final Deck deck = Deck.create();
        final BlackjackGame blackjackGame = new BlackjackGame(participants, deck);
        blackjackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);

        final List<Player> players = playersTurn(participants, deck);
        final Dealer dealer = dealerTurn(blackjackGame);
        createBlackJackGameResult(players, dealer);
    }

    private Participants getParticipants() {
        try {
            final List<String> playerNames = InputView.inputPlayerNames();
            checkValidPlayerNames(playerNames);
            final List<Player> players = getPlayersBetMoney(playerNames);
            return new Participants(new Dealer(), players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getParticipants();
        }
    }

    private Participants checkValidPlayerNames(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Participants(new Dealer(), players);
    }

    private List<Player> getPlayersBetMoney(List<String> playerNames) {
        final List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(getPlayerBetMoney(playerName));
        }
        return players;
    }

    private Player getPlayerBetMoney(String playerName) {
        try {
            final String playerMoney = InputView.inputPlayerMoney(playerName);
            return new Player(playerName, playerMoney);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getPlayerBetMoney(playerName);
        }
    }

    private List<Player> playersTurn(Participants participants, Deck deck) {
        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            doEachPlayerTurn(player, deck);
        }
        return players;
    }

    private void doEachPlayerTurn(Player player, Deck deck) {
        while (player.hasNextTurn() && getOneMoreCard(player)) {
            player.receiveCard(deck.distributeCard());
            OutputView.printNowHoldCardInfo(player);
        }
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

    private void createBlackJackGameResult(List<Player> players, Dealer dealer) {
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, players);
        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();
        final Map<Player, BlackjackMatch> playersResult = blackjackGameResult.calculatePlayersResult();
        OutputView.printGameResult(dealer, players);
        OutputView.printMatchResult(dealer, dealerResult, playersResult);
        OutputView.printProfitResult(dealer, blackjackGameResult.calculateDealerProfit(), playersResult);
    }
}
