package controller;

import java.util.List;
import model.Agreement;
import model.BetPrice;
import model.Dealer;
import model.Player;
import model.Players;
import model.PlayerName;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void playBlackJackGame() {
        Players players = getParticipantsName();
        getBet(players);

        Dealer dealer = new Dealer();

        initGame(dealer, players);
        participantTurn(dealer, players);

        displayResult(dealer.getResult(), players.getPlayersResult(), blackJackService.getGameResult(players, dealer));
    }

    private Players getParticipantsName() {
        List<String> playerNamesInput = InputView.getPlayerNames();
        List<Player> players = playerNamesInput.stream().map(playerNameInput -> new Player(new PlayerName(playerNameInput))).toList();

        return new Players(players);
    }

    private void getBet(Players players) {
        for(Player player : players.getPlayers()) {
            addBet(player);
        }
    }

    private void addBet(Player player) {
        BetPrice betPrice = new BetPrice(InputView.getBet(player.getName()));
        player.setBetAmount(betPrice.value());
    }

    private void initGame(Dealer dealer, Players players) {
        blackJackService.initGame(dealer, players);
        OutputView.printInitDeck(players.getPlayersResult(), dealer.getFirstCard());
    }

    private void participantTurn(Dealer dealer, Players players) {
        drawPlayersTurn(players);
        drawDealerTurn(dealer);
    }

    private void drawPlayersTurn(Players players) {
        for(Player player : players.getPlayers()) {
            drawPlayerTurns(player);
        }
        OutputView.printNewLine();
    }

    private void drawPlayerTurns(Player player) {
        while(drawPlayerTurn(player));
    }

    private boolean drawPlayerTurn(Player player) {
        if(!getCondition(player.getName())) {
            return false;
        }

        blackJackService.draw(player);
        OutputView.printPlayerCurrentDeck(player.getResult());

        return !player.isBust();
    }

    private boolean getCondition(String name) {
        return new Agreement(InputView.getDrawCondition(name)).get();
    }

    private void drawDealerTurn(Dealer dealer) {
        while (dealer.canDraw()) {
            blackJackService.draw(dealer);
            OutputView.printDealerCardDrawMessage();
        }
    }

    private void displayResult(PlayerResult dealerResult, List<PlayerResult> playerResults, ParticipantWinning result) {
        OutputView.printPlayersScore(dealerResult, playerResults);
        OutputView.printResult(result);
    }
}
