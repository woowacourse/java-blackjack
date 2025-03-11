package controller;

import controller.dto.DealerMatchResultCountDto;
import controller.dto.NameAndCardsDto;
import controller.dto.NameAndSumsDto;
import controller.dto.UsersMatchResultDto;
import domain.BlackjackManager;
import domain.card.Deck;
import domain.card.DeckGenerator;
import domain.player.Dealer;
import domain.player.Players;
import domain.player.User;
import domain.player.Users;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        BlackjackManager blackjackManager = createBlackjackManager();

        distributeInitialCards(blackjackManager);
        addMoreCards(blackjackManager);
        printGameResult(blackjackManager);
    }

    private void distributeInitialCards(BlackjackManager blackjackManager) {
        blackjackManager.distributeInitialCards();
        blackjackManager.openInitialCards();
        OutputView.printInitialCards(
                NameAndCardsDto.toNameAndOpenedCards(blackjackManager.getDealer()),
                NameAndCardsDto.toNameAndOpenedCards(blackjackManager.getUsers())
        );
    }

    private void addMoreCards(BlackjackManager blackjackManager) {
        blackjackManager.addMoreCardsToUsers(InputView::inputWantOneMoreCard, OutputView::printPlayerCards);

        if (blackjackManager.addCardToDealerIfLowSum()) {
            OutputView.printAddCardToDealer();
        }
    }

    private void printGameResult(BlackjackManager blackjackManager) {
        final Dealer dealer = blackjackManager.getDealer();
        final List<User> users = blackjackManager.getUsers();
        OutputView.printPlayersCardsAndSum(NameAndCardsDto.toNameAndCards(dealer),
                NameAndCardsDto.toNameAndCards(users),
                NameAndSumsDto.from(blackjackManager.computePlayerSum()));

        final var usersMatchResult = blackjackManager.computeUsersMatchResult(dealer, users);
        final var dealerMathResultCount = blackjackManager.computeDealerMatchResultCount(usersMatchResult);
        OutputView.printMatchResults(blackjackManager.getDealerName(),
                DealerMatchResultCountDto.from(dealerMathResultCount),
                UsersMatchResultDto.from(usersMatchResult));
    }

    private BlackjackManager createBlackjackManager() {
        List<String> names = InputView.inputUserName();
        Users users = createUsers(names);
        Dealer dealer = new Dealer();
        Deck deck = DeckGenerator.generateDeck();
        Players players = createPlayers(dealer, users);
        return new BlackjackManager(players, deck);
    }

    private Users createUsers(List<String> names) {
        return new Users(
                names.stream()
                        .map(String::strip)
                        .map(User::new)
                        .collect(Collectors.toList())
        );
    }

    private Players createPlayers(Dealer dealer, Users users) {
        return new Players(dealer, users);
    }
}
