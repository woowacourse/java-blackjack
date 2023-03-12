package blackjack.controller;

import blackjack.domain.game.Betting;
import blackjack.domain.game.BettingTable;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = generatePlayers();
        BettingTable bettingTable = generateBettingTable(players.getNames());

        BlackJackGame blackJackGame = BlackJackGame.create(players, bettingTable);

        setUp(blackJackGame);

        play(blackJackGame);

        showResult(blackJackGame);
    }

    private void setUp(BlackJackGame blackJackGame) {
        blackJackGame.setUp();

        outputView.showInitStatus(blackJackGame.getPlayers());
        outputView.showDealerFirstCard(blackJackGame.getDealer().getFirstCard());
        outputView.showPlayers(blackJackGame.getPlayers());
    }

    private void play(BlackJackGame blackJackGame) {
        if (blackJackGame.isDealerBlackJack()) {
            return;
        }
        passExtraCardToPlayers(blackJackGame);
        passExtraCardToDealer(blackJackGame);
    }

    private void showResult(BlackJackGame blackJackGame) {
        outputView.showTotalScore(blackJackGame.getDealer(), blackJackGame.getPlayers());
        Map<Name, Integer> result = blackJackGame.calculatePlayersProfit();
        int dealerProfit = blackJackGame.calculateDealerProfit();

        outputView.showProfits(result, dealerProfit);
    }

    private void passExtraCardToPlayers(BlackJackGame blackJackGame) {
        List<Name> playerNames = blackJackGame.getPlayerNames();
        for (Name playerName : playerNames) {
            addExtraCard(blackJackGame, playerName);
        }
    }

    private void addExtraCard(BlackJackGame blackJackGame, Name name) {
        while (blackJackGame.canPlayerReceive(name) && hasIntention(name.getValue())) {
            blackJackGame.passCardToPlayer(name);
            outputView.showPlayer(blackJackGame.getPlayer(name));
        }
    }

    private void passExtraCardToDealer(BlackJackGame blackJackGame) {
        if (blackJackGame.canDealerReceive()) {
            blackJackGame.passCardToDealer();
            outputView.showDealerDrawPossible();
            return;
        }
        outputView.showDealerDrawImpossible();
    }

    private boolean hasIntention(String name) {
        return inputView.readIntention(name);
    }

    private Players generatePlayers() {
        try {
            List<String> names = inputView.readNames();
            return Players.create(names);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            return generatePlayers();
        }
    }

    private BettingTable generateBettingTable(List<Name> playerNames) {
        Map<Name, Betting> bettingInfo = new HashMap<>();
        for (Name playerName : playerNames) {
            Betting betting = generateBetting(playerName);
            bettingInfo.put(playerName, betting);
        }
        return new BettingTable(bettingInfo);
    }

    private Betting generateBetting(Name name) {
        try {
            int betting = inputView.readBetting(name.getValue());
            return new Betting(betting);
        } catch (IllegalArgumentException e) {
            outputView.showError(e.getMessage());
            return generateBetting(name);
        }
    }
}
