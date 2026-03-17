package controller;

import domain.vo.Money;
import domain.vo.Name;
import dto.DealerResultDto;
import dto.PlayerResultDto;
import service.BlackjackService;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private final BlackjackService blackjackService;
    private List<Name> names = new ArrayList<>();

    public GameController(BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    public void run() {
        gameSetup();
        askBettingMoney();

        participantsHit();
        finalizeGameResult();
    }

    private void gameSetup() {
        List<String> names = InputView.askName();
        this.names = names.stream()
                .map((String name) -> new Name(name))
                .collect(Collectors.toList());

        blackjackService.registerPlayers(names);
        blackjackService.dealCardsOut();

        ResultView.printStartPlayersCards(blackjackService.getDealerInfo(), blackjackService.getPlayerInfos());
    }

    private void askBettingMoney() {
        for (Name name : names) {
            blackjackService.placeBet(name, new Money(InputView.askBettingMoney(name.getValueOf())));
        }
    }

    private void participantsHit() {
        for (Name name : names) {
            playerHit(name);
        }

        if (blackjackService.isDealerHit()) {
            ResultView.printDealerOneMoreCard();
        }
    }

    private void playerHit(Name name) {
        while (canHitAndDraw(name)) {
            ResultView.printPlayerCards(blackjackService.getPlayerInfo(name));
        }
    }

    private boolean canHitAndDraw(Name name) {
        if (blackjackService.isBust(name)) {
            return false;
        }

        if (InputView.askHit(name.getValueOf())) {
            blackjackService.playerHit(name);
            return true;
        }

        ResultView.printPlayerCards(blackjackService.getPlayerInfo(name));
        return false;
    }

    private void finalizeGameResult() {
        blackjackService.decideDealerResult(names);

        DealerResultDto dealerResultDto = blackjackService.getDealerResult();
        List<PlayerResultDto> playerResultDtos = blackjackService.getPlayerResultInfos();

        ResultView.printCardsAndScoreResult(dealerResultDto, playerResultDtos);
        ResultView.printRankResult(dealerResultDto, playerResultDtos);

        ResultView.printDealerProfit(dealerResultDto);
        playerResultDtos.stream()
                .forEach(playerResultDto -> ResultView.printPlayerProfit(playerResultDto));
    }
}
