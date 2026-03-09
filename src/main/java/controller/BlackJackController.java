package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import dto.BlackJackHandDto;
import dto.BlackJackInitStatusDto;
import dto.FinalResultDto;
import dto.ScoreResultDto;
import java.util.List;
import service.BlackJackInitService;
import service.BlackJackResultService;
import service.BlackJackTurnService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackInitService blackJackInitService;
    private final BlackJackTurnService blackJackTurnService;
    private final BlackJackResultService blackJackResultService;

    public BlackJackController(BlackJackInitService blackJackInitService,
                               BlackJackTurnService blackJackTurnService,
                               BlackJackResultService blackJackResultService) {
        this.blackJackInitService = blackJackInitService;
        this.blackJackTurnService = blackJackTurnService;
        this.blackJackResultService = blackJackResultService;
    }

    public void run() {
        List<String> names = InputView.askPlayerNames();

        Deck deck = blackJackInitService.createDeck();
        List<Player> players = blackJackInitService.createPlayers(names, deck);
        Dealer dealer = blackJackInitService.createDealer(deck);

        playGame(deck, dealer, players);
        printResult(dealer, players);
    }

    private void printResult(Dealer dealer, List<Player> players) {
        ScoreResultDto scoreResultDto = blackJackResultService.createScoreResultDto(dealer, players);
        OutputView.printScoreResult(scoreResultDto);

        FinalResultDto finalResultDto = blackJackResultService.createFinalResultDto(dealer, players);
        OutputView.printFinalResult(finalResultDto);
    }

    private void playGame(Deck deck, Dealer dealer, List<Player> players) {
        BlackJackInitStatusDto blackJackInitStatusDto = blackJackInitService.createInitStatusDto(dealer, players);
        OutputView.printInitMessage(blackJackInitStatusDto);

        for (Player player : players) {
            drawPlayerCard(player, deck);
        }
        drawDealerCard(dealer, deck);
    }

    // todo: depth 2 해결
    private void drawPlayerCard(Player player, Deck deck) {
        String yesNoInput = InputView.askPlayerCommand(player.getName());

        if (!blackJackTurnService.canPlayerHit(player, yesNoInput)) {
            BlackJackHandDto blackJackHandDto = blackJackTurnService.createHandDto(player);
            OutputView.printHandOutput(blackJackHandDto);
        }

        while (blackJackTurnService.canPlayerHit(player, yesNoInput)) {
            blackJackTurnService.playerHit(player, deck);
            BlackJackHandDto blackJackHandDto = blackJackTurnService.createHandDto(player);
            OutputView.printHandOutput(blackJackHandDto);
            // 합이 21 넘어가면 바로 입력받기 종료
            if (!blackJackTurnService.isPlayerUnder21(player)) {
                break;
            }
            yesNoInput = InputView.askPlayerCommand(player.getName());
        }
    }

    private void drawDealerCard(Dealer dealer, Deck deck) {
        while (blackJackTurnService.canDealerHit(dealer)) {
            blackJackTurnService.dealerHit(dealer, deck);
            OutputView.printDealerHitMessage();
        }
    }
}
