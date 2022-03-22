package blackjack.controller;

import blackjack.domain.betting.BettingToken;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.process.BlackJackGame;
import blackjack.domain.process.BettingResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void gameStart() {
        BlackJackGame blackJackGame = new BlackJackGame(new Deck());
        Players players = generatePlayers();
        Dealer dealer = new Dealer();
        initializeCard(players, dealer, blackJackGame);
        progressPlayerTurn(players, blackJackGame);
        progressDealerTurn(players, dealer, blackJackGame);
        makeResult(players, dealer);
    }

    private Players generatePlayers() {
        List<Name> names = generateNames();
        List<BettingToken> bettingTokens = generateBettingMoneys(names);
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(bettingTokens.get(i), names.get(i)));
        }
        return new Players(players);
    }

    private List<Name> generateNames() {
        try {
            return inputView.inputPlayerNames().stream()
                .map(Name::new)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateNames();
        }
    }

    private List<BettingToken> generateBettingMoneys(List<Name> names) {
        outputView.displayNewLine();
        List<BettingToken> bettingTokens = new ArrayList<>();
        for (Name name : names) {
            bettingTokens.add(inputMoney(name.getName()));
        }
        return bettingTokens;
    }

    private void makeResult(Players players, Dealer dealer) {
        outputView.displayNewLine();
        outputView.displayAllCardAndScore(dealer);
        for (Player player : players.getPlayers()) {
            outputView.displayAllCardAndScore(player);
        }
        BettingResult bettingResult = new BettingResult(players, dealer);
        outputView.displayNewLine();
        outputView.displayResult(bettingResult);
    }

    private void progressDealerTurn(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        outputView.displayNewLine();
        while (dealer.canHit() && !players.isAllPlayersBlackJackOrBust()) {
            outputView.displayDealerUnderSevenTeen();
            blackJackGame.drawTo(dealer);
        }
    }

    private void progressPlayerTurn(Players players, BlackJackGame blackJackGame) {
        players.getPlayers().forEach(player -> progressOnePlayer(player, blackJackGame));
    }

    private void progressOnePlayer(Player player, BlackJackGame blackJackGame) {
        while (player.canHit() && inputDecision(player)) {
            blackJackGame.drawTo(player);
            outputView.displayAllCard(player);
        }
    }

    private boolean inputDecision(Player player) {
        try {
            return inputView.inputYesOrNo(player.getName());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return inputDecision(player);
        }
    }

    private BettingToken inputMoney(String name) {
        try {
            return new BettingToken(inputView.inputMoney(name));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return inputMoney(name);
        }
    }

    private void initializeCard(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        blackJackGame.deal(players, dealer);
        outputView.displayNewLine();
        outputView.displayFirstDistribution(players, dealer);
        outputView.displayDealerOneCard(dealer);
        for (Player player : players.getPlayers()) {
            outputView.displayAllCard(player);
        }
        outputView.displayNewLine();
    }
}
