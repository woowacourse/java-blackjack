package controller;

import java.util.ArrayList;
import java.util.List;
import model.Dealer;
import model.Participant;
import model.Player;
import model.Players;
import model.dto.PlayerResult;
import service.BlackJackService;
import view.OutputView;

public class BlackJackController {
    private final InputController inputController;
    private final BlackJackService blackJackService;

    public BlackJackController(InputController inputController, BlackJackService blackJackService) {
        this.inputController = inputController;
        this.blackJackService = blackJackService;
    }

    public void run() {
        Players players = inputController.getParticipantsName();
        Dealer dealer = new Dealer();

        OutputView.printNewLine();

        initDraw(dealer, players);

        drawPlayersTurn(players);
        drawDealer(dealer);

        printPlayersScore(dealer, players);

        OutputView.printResult(blackJackService.getGameResult(players, dealer));

    }

    private void initDraw(Dealer dealer, Players players) {
        //딜러 두번
        initParticipantDraw(dealer);
        //플레이어 전체 두번
        List<PlayerResult> playersResult = new ArrayList<>();

        for(Player player : players.getPlayers()) {
            initParticipantDraw(player);
            playersResult.add(player.getResult());
        }

        OutputView.printInitDeck(playersResult,dealer.getResult());
    }

    private void initParticipantDraw(Participant participant) {
        for(int i  = 0; i < 2; i++) {
            blackJackService.draw(participant);
        }
    }

    private void drawPlayersTurn(Players players) {
        for(Player player : players.getPlayers()) {
            drawPlayerTurn(player);
        }
        OutputView.printNewLine();
    }

    private void drawPlayerTurn(Player player) {
        while(true) {
           if(!inputController.getCondition(player.getResult().name().get())) {
               break;
           }

           blackJackService.draw(player);

           if(blackJackService.isBust(player)) {
               break;
           }

           OutputView.printPlayerCurrentDeck(player.getResult());
        }
    }

    private void drawDealer(Dealer dealer) {
        if(dealer.getResult().score() <= 16) {
            blackJackService.draw(dealer);
            OutputView.printDealerCardDrawMessage();
        }
    }

    private void printPlayersScore(Dealer dealer, Players players) {
        List<PlayerResult> playerResults = new ArrayList<>();
        playerResults.add(dealer.getResult());

        for(Player player : players.getPlayers()) {
            playerResults.add(player.getResult());
        }

        OutputView.printPlayersScore(playerResults);
    }




}
