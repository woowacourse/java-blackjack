package controller;

import domain.Dealer;
import domain.Deck;
import domain.Money;
import domain.Player;
import domain.betting.Betting;
import domain.betting.Bettings;
import domain.result.Results;
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

        Bettings bettings = betMoney(players);

        Results results = playGame(deck, dealer, bettings);
        printResult(dealer, players, results);
    }

    private Bettings betMoney(List<Player> players) {
        Bettings bettings = new Bettings();
        for (Player player : players) {
            Money money = new Money(InputView.askBettingAmount(player.getName()));
            Betting betting = new Betting(player, money);
            bettings = bettings.addBetting(betting);
        }
        return bettings;
    }

    private void printResult(Dealer dealer, List<Player> players, Results results) {
        ScoreResultDto scoreResultDto = ScoreResultDto.of(dealer, players);
        OutputView.printScoreResult(scoreResultDto);

        FinalResultDto finalResultDto = FinalResultDto.from(results);
        OutputView.printFinalResult(finalResultDto);
    }

    private Results playGame(Deck deck, Dealer dealer, Bettings bettings) {
        List<Player> players = bettings.getPlayers();
        InitStatusDto initStatusDto = InitStatusDto.of(dealer, players);
        OutputView.printInitMessage(initStatusDto);

        players.forEach(player -> drawPlayerCard(player, deck));

        drawDealerCard(dealer, deck);

        return Results.of(dealer, bettings);
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
            OutputView.printHandOutput(player.getName(), handDto);
        }
    }

    private boolean isFirstCommandNo(Player player, Deck deck) {
        String yesNoInput = InputView.askPlayerCommand(player.getName());

        if (blackJackTurnService.canPlayerHit(player, yesNoInput)) {
            blackJackTurnService.playerHit(player, deck);
            HandDto handDto = HandDto.from(player.getHand());
            OutputView.printHandOutput(player.getName(), handDto);
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
