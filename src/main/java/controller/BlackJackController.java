package controller;

import java.util.List;
import model.Agreement;
import model.BetPrice;
import model.Dealer;
import model.Player;
import model.Players;
import model.PlayerName;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final BlackJackService blackJackService;

    public BlackJackController(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        Players players = getParticipantsName();
        getBet(players);

        Dealer dealer = new Dealer();

        blackJackService.initGame(dealer, players);

        drawPlayersTurn(players);
        drawDealer(dealer);

        OutputView.printPlayersScore(dealer.getResult(), players.getPlayersResult());
        OutputView.printResult(blackJackService.getGameResult(players, dealer));
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

    private void drawDealer(Dealer dealer) {
        while (dealer.canDraw()) {
            blackJackService.draw(dealer);
            OutputView.printDealerCardDrawMessage();
        }
    }

}
