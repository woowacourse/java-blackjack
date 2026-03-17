package controller;

import domain.Deck;
import domain.betting.Betting;
import domain.betting.Bettings;
import domain.money.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
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

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(BlackJackInitService blackJackInitService,
                               BlackJackTurnService blackJackTurnService,
                               InputView inputView,
                               OutputView outputView) {
        this.blackJackInitService = blackJackInitService;
        this.blackJackTurnService = blackJackTurnService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        List<String> names = inputView.askPlayerNames();

        Deck deck = blackJackInitService.createDeck();
        Players players = blackJackInitService.createPlayers(names, deck);
        Dealer dealer = blackJackInitService.createDealer(deck);

        Bettings bettings = betMoney(players);

        Results results = playGame(deck, dealer, bettings);
        printResult(dealer, players, results);
    }

    private Bettings betMoney(Players players) {
        Bettings bettings = new Bettings();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            BettingMoney bettingMoney = BettingMoney.from(inputView.askBettingAmount(player.getNameString()));
            Betting betting = new Betting(player, bettingMoney);
            bettings = bettings.addBetting(betting);
        }
        return bettings;
    }

    private void printResult(Dealer dealer, Players players, Results results) {
        ScoreResultDto scoreResultDto = ScoreResultDto.of(dealer, players);
        outputView.printScoreResult(scoreResultDto);

        FinalResultDto finalResultDto = FinalResultDto.from(results);
        outputView.printFinalResult(finalResultDto);
    }

    private Results playGame(Deck deck, Dealer dealer, Bettings bettings) {
        Players players = bettings.getPlayers();
        InitStatusDto initStatusDto = InitStatusDto.of(dealer, players);
        outputView.printInitMessage(initStatusDto);

        // todo : player, players를 붋변객체로 만들어서 해당 로직도 불변에 맞게 수정
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            drawPlayerCard(player, deck);
        }
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
        while (blackJackTurnService.canPlayerHit(player, inputView.askPlayerCommand(player.getNameString()))) {
            blackJackTurnService.playerHit(player, deck);
            HandDto handDto = HandDto.from(player.getHand());
            outputView.printHandOutput(player.getNameString(), handDto);
        }
    }

    private boolean isFirstCommandNo(Player player, Deck deck) {
        String yesNoInput = inputView.askPlayerCommand(player.getNameString());

        if (blackJackTurnService.canPlayerHit(player, yesNoInput)) {
            blackJackTurnService.playerHit(player, deck);
            HandDto handDto = HandDto.from(player.getHand());
            outputView.printHandOutput(player.getNameString(), handDto);
        }
        return !blackJackTurnService.canPlayerHit(player, yesNoInput);
    }

    private void drawDealerCard(Dealer dealer, Deck deck) {
        while (blackJackTurnService.canDealerHit(dealer)) {
            blackJackTurnService.dealerHit(dealer, deck);
            outputView.printDealerHitMessage();
        }
    }
}
