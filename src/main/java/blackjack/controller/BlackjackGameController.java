package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.Card;
import blackjack.domain.user.ParticipantName;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.CardDto;
import blackjack.dto.DtoMapper;
import blackjack.dto.UserCardsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGameController {
    private BlackjackGame blackjackGame;

    public void start() {
        blackjackGame = BlackjackGame.create(inputUsers());
        blackjackGame.firstDraw();

        printFirstDrawInformation(blackjackGame.getUserNames());
        printFirstDrawCards(getDealerOpenedCard(), DtoMapper.createUsersCardDto(blackjackGame.getUsers()));

        processUserRound();

        processDealerRound();

        createResultAndPrint();
    }

    private void printFirstDrawInformation(List<ParticipantName> userNames) {
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

    private CardDto getDealerOpenedCard() {
        Card dealerFirstCard = blackjackGame.getDealerFirstCard();
        return new CardDto(dealerFirstCard.getSuitLetter(), dealerFirstCard.getSymbolLetter());
    }

    private void processUserRound() {
        while (blackjackGame.existCanContinueUser()) {
            User currentUser = blackjackGame.findFirstCanPlayUser();
            boolean isContinue = InputView.askMoreDraw(currentUser.getName());
            OutputView.printCardList(progressTurnAndGetResult(currentUser, isContinue));
        }
        OutputView.println();
    }

    private UserCardsDto progressTurnAndGetResult(User currentUser, boolean isContinue) {
        userDrawOrStop(isContinue, currentUser);
        return DtoMapper.createUserCardsDto(currentUser);
    }

    private void userDrawOrStop(boolean isContinue, User playingUser) {
        if (isContinue) {
            playingUser.drawCard(blackjackGame.draw());
            return;
        }
        playingUser.stopUser();
    }

    private void processDealerRound() {
        while (blackjackGame.canDealerMoreDraw()) {
            blackjackGame.drawToDealer();
            OutputView.printDealerMoreDrawMessage();
        }
    }

    private void createResultAndPrint() {
        OutputView.printScoreBoard(blackjackGame.createScoreBoard());
    }
}
