package blackjack.controller;

import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.CardDto;
import blackjack.dto.UserCardsDto;
import blackjack.service.BlackjackGameService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGameController {

    private BlackjackGameService blackjackGameService;

    public void start() {
        blackjackGameService = BlackjackGameService.createByUsers(inputUsers());
        blackjackGameService.firstDraw();

        printFirstDrawInformation(blackjackGameService.getUserNameList());
        printFirstDrawCards(blackjackGameService.getDealerOpenedCard(), blackjackGameService.getAllUserCurrentCards());

        processUserRound();

        processDealerRound();

        createResultAndPrint();
    }

    private void printFirstDrawInformation(List<Name> userNames) {
        OutputView.printDrawMessage(userNames);
        OutputView.println();
    }

    private void printFirstDrawCards(CardDto openedDealerCard, UserCardsDto userCardsDto) {
        OutputView.printDealerFirstCard(openedDealerCard);
        OutputView.printCardList(userCardsDto);
        OutputView.println();
    }

    private Users inputUsers() {
        return InputView.askPlayersName()
                .stream()
                .map(Name::new)
                .map(User::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Users::new));
    }

    private void processUserRound() {
        while (blackjackGameService.existCanContinueUser()) {
            Boolean isContinue = InputView.askMoreDraw(blackjackGameService.getCurrentUserName());
            OutputView.printCardList(blackjackGameService.progressTurnAndGetResult(isContinue));
        }
        OutputView.println();
    }


    private void processDealerRound() {
        while (blackjackGameService.canDealerMoreDraw()) {
            blackjackGameService.drawCardToDealer();
            OutputView.printDealerMoreDrawMessage();
        }
    }

    private void createResultAndPrint() {
        OutputView.printScoreBoard(blackjackGameService.createScoreBoard());
    }
}
