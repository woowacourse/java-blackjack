package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        Dealer dealer = new Dealer();
        Users users = participateUsers();
        List<Participant> participants = setUpParticipants(dealer, users);
        distributeToParticipants(dealer, users);
        showParticipantsCards(dealer, users);
        askToUsers(users);
        isDealerDrawable(dealer);
        OutputView.printResults(participants);
        OutputView.printResultBoard(dealer, new ResultBoard(dealer, users));
    }

    private Users participateUsers() {
        OutputView.printInputNames();
        List<String> names = InputView.inputNames();
        return new Users(names);
    }

    private List<Participant> setUpParticipants(Dealer dealer, Users users) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(users.users());
        return participants;
    }

    private void distributeToParticipants(Dealer dealer, Users users) {
        dealer.distribute(Deck.popTwo());
        users.distributeToEachUser();
    }

    private void showParticipantsCards(Dealer dealer, Users users) {
        OutputView.printDistribute(dealer, users);
        OutputView.printDealerCard(dealer);
        OutputView.printUsersCards(users);
    }

    private void askToUsers(Users users) {
        for (User user : users.users()) {
            askForDraw(user);
        }
    }

    private void askForDraw(User user) {
        while (isDrawable(user) && DrawAnswer.isYes(DrawAnswer.of(InputView.inputDrawAnswer()))) {
            user.draw();
            OutputView.printUserCards(user);
        }
    }

    private boolean isDrawable(User user) {
        if (user.isDrawable()) {
            OutputView.printMoreDraw(user);
            return true;
        }
        return false;
    }

    private void isDealerDrawable(Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.draw();
            OutputView.printDealerDrawable(dealer);
            return;
        }
        OutputView.printDealerNotDrawable(dealer);
    }
}
