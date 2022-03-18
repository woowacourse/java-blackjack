package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
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
        final Map<Player, BettingMoney> playersInfo = getPlayersBettingMoney(participants.getPlayers());
        final Deck deck = Deck.of(Deck.create().getDeck());
        final BlackjackGame blackjackGame = progressGame(participants, deck);
        
        final List<Player> players = playersTurn(blackjackGame, participants);
        final Dealer dealer = dealerTurn(blackjackGame);
        createBlackjackGameResult(players, dealer);
        createBlackjackProfitResult(dealer, playersInfo);
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

    private Map<Player, BettingMoney> getPlayersBettingMoney(List<Player> players) {
        final Map<Player, BettingMoney> playersInfo = new LinkedHashMap<>();
        for (Player player : players) {
            final BettingMoney money = getPlayerBettingMoney(player.getName());
            playersInfo.put(player, money);
        }
        return playersInfo;
    }

    private BettingMoney getPlayerBettingMoney(String playerName) {
        try {
            final int money = InputView.inputPlayerMoney(playerName);
            return new BettingMoney(money);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return getPlayerBettingMoney(playerName);
        }
    }

    private BlackjackGame progressGame(Participants participants, Deck deck) {
        final BlackjackGame blackjackGame = new BlackjackGame(participants, deck);
        blackjackGame.firstCardDispensing();
        OutputView.printInitCardResult(participants);
        return blackjackGame;
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

    private void createBlackjackGameResult(List<Player> players, Dealer dealer) {
        final BlackjackGameResult blackjackGameResult = new BlackjackGameResult(dealer, players);
        final Map<String, Integer> dealerResult = blackjackGameResult.calculateDealerResult();
        final Map<Player, BlackjackMatch> playersResult = blackjackGameResult.calculatePlayersResult();
        OutputView.printGameResult(dealer, players);
        OutputView.printMatchResult(dealer, dealerResult, playersResult);
    }

    private void createBlackjackProfitResult(Dealer dealer, Map<Player, BettingMoney> result) {
        final BlackjackProfitResult blackjackProfitResult = new BlackjackProfitResult(dealer, result);
        OutputView.printProfitResult(blackjackProfitResult.calculateParticipantsProfit());
    }
}
