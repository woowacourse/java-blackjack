package controller;

import domain.Dealer;
import domain.Deck;
import domain.Player;
import dto.FinalResultDto;
import dto.HandDto;
import dto.InitStatusDto;
import dto.ScoreResultDto;
import java.util.List;
import service.BlackJackInitService;
import service.BlackJackTurnService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackInitService blackJackInitService;
    private final BlackJackTurnService blackJackTurnService;

    public BlackJackController(BlackJackInitService blackJackInitService,
                               BlackJackTurnService blackJackTurnService) {
        this.blackJackInitService = blackJackInitService;
        this.blackJackTurnService = blackJackTurnService;
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
        ScoreResultDto scoreResultDto = ScoreResultDto.of(dealer, players);
        OutputView.printScoreResult(scoreResultDto);

        FinalResultDto finalResultDto = FinalResultDto.of(dealer, players);
        OutputView.printFinalResult(finalResultDto);
    }

    private void playGame(Deck deck, Dealer dealer, List<Player> players) {
        InitStatusDto initStatusDto = InitStatusDto.of(dealer, players);
        OutputView.printInitMessage(initStatusDto);

        players.forEach(player -> drawPlayerCard(player, deck));

        drawDealerCard(dealer, deck);
    }

    private void drawPlayerCard(Player player, Deck deck) {
        if (isFirstCommandNo(player, deck)) {
            return;
        }
        repeatCommands(player, deck);
    }

    private void repeatCommands(Player player, Deck deck) {
        while (blackJackTurnService.canPlayerHit(player, InputView.askPlayerCommand(player.getName()))) {
            blackJackTurnService.playerHit(player, deck);
            HandDto handDto = HandDto.from(player.getHand());
            OutputView.printHandOutput(handDto);
        }
    }

    private boolean isFirstCommandNo(Player player, Deck deck) {
        String yesNoInput = InputView.askPlayerCommand(player.getName());

        if (blackJackTurnService.canPlayerHit(player, yesNoInput)) {
            blackJackTurnService.playerHit(player, deck);
            HandDto handDto = HandDto.from(player.getHand());
            OutputView.printHandOutput(handDto);
        }
        return !blackJackTurnService.canPlayerHit(player, yesNoInput);
    }

    private void drawDealerCard(Dealer dealer, Deck deck) {
        while (blackJackTurnService.canDealerHit(dealer)) {
            blackJackTurnService.dealerHit(dealer, deck);
            OutputView.printDealerHitMessage();
        }
    }
}
