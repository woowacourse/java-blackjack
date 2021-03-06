package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Card;
import blackjack.domain.user.Name;
import blackjack.domain.user.Participant;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.CardDto;
import blackjack.dto.DtoMapper;
import blackjack.dto.UserCardsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class BlackjackGameController {
    private static final int DEALER_MINIMUM_SCORE = 16;

    private BlackjackGame blackjackGame;

    public void start() {
        blackjackGame = BlackjackGame.create(inputUsers());
        blackjackGame.firstDraw();

        printFirstDrawInformation(getUserNameList());
        printFirstDrawCards(getDealerOpenedCard(), DtoMapper.createUsersCardDto(blackjackGame.getUsers()));

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
                .map(User::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Users::new));
    }

    private List<Name> getUserNameList() {
        return blackjackGame.getUsers()
                .stream()
                .map(Participant::getName)
                .collect(toList());
    }

    private CardDto getDealerOpenedCard() {
        Card dealerFirstCard = blackjackGame.getDealer().getFirstCard();
        return new CardDto(dealerFirstCard.getSuitLetter(), dealerFirstCard.getSymbolLetter());
    }

    private void processUserRound() {
        while (blackjackGame.existCanContinueUser()) {
            User currentUser = blackjackGame.findFirstCanPlayUser();
            Boolean isContinue = InputView.askMoreDraw(currentUser.getName());
            OutputView.printCardList(progressTurnAndGetResult(currentUser, isContinue));
        }
        OutputView.println();
    }

    private UserCardsDto progressTurnAndGetResult(User currentUser, Boolean isContinue) {
        userDrawOrStop(isContinue, currentUser);
        return DtoMapper.createUserCardsDto(currentUser);
    }

    private void userDrawOrStop(Boolean isContinue, User playingUser) {
        if (isContinue) {
            playingUser.drawCard(blackjackGame.draw());
            return;
        }
        playingUser.stopUser();
    }

    private void processDealerRound() {
        while (canDealerMoreDraw()) {
            blackjackGame.drawToDealer();
            OutputView.printDealerMoreDrawMessage();
        }
    }

    private boolean canDealerMoreDraw() {
        return blackjackGame.getDealer().calculateScore() <= DEALER_MINIMUM_SCORE;
    }

    private void createResultAndPrint() {
        OutputView.printScoreBoard(blackjackGame.createScoreBoard());
    }
}
