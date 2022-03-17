package blackJack.controller;

import blackJack.domain.BlackJackGame;
import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.BlackJackGameResult;
import blackJack.domain.result.BlackJackMatch;
import blackJack.domain.result.YesOrNo;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {
        final Participants participants = getParticipants();
        final Deck deck = Deck.create();
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck);
        blackJackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);

        final List<Player> players = playersTurn(participants, deck);
        final Dealer dealer = dealerTurn(blackJackGame);
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

    private Dealer dealerTurn(BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.doDealerGame();
        OutputView.printDealerReceiveCardCount(dealer);
        return dealer;
    }

    private void createBlackJackGameResult(List<Player> players, Dealer dealer) {
        final BlackJackGameResult blackJackGameResult = new BlackJackGameResult(dealer, players);
        final Map<String, Integer> dealerResult = blackJackGameResult.calculateDealerResult();
        final Map<Player, BlackJackMatch> playersResult = blackJackGameResult.calculatePlayersResult();
        OutputView.printGameResult(dealer, players);
        OutputView.printMatchResult(dealer, dealerResult, playersResult);
        OutputView.printProfitResult(dealer, blackJackGameResult.calculateDealerProfit(), playersResult);
    }
}
