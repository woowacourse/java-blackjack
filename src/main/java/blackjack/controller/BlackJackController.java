package blackjack.controller;

import blackjack.domain.card.CardGenerator;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.UserResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private static final int INIT_DISTRIBUTE_NUM = 2;

    public void play() {
        Users users = new Users(InputView.inputUsersName());
        Dealer dealer = new Dealer();
        Deck deck = new Deck(new CardGenerator());
        initDistribute(deck, users, dealer);

        playGame(users, dealer, deck);
        gameResult(users, dealer);
    }

    private void initDistribute(Deck deck, Users users, Dealer dealer) {
        OutputView.printInitDistribute(users);

        cardDistribute(deck, dealer);
        OutputView.printParticipantCards(dealer.getDealerInfoWithoutHiddenCard());

        for (User user : users.getUsers()) {
            cardDistribute(deck, user);
            OutputView.printParticipantCards(user.getUserInfo());
        }
        OutputView.printLineSeparators();
    }

    private void cardDistribute(Deck deck, Participant participant) {
        for (int i = 0; i < INIT_DISTRIBUTE_NUM; i++) {
            participant.receiveCard(deck.drawCard());
        }
    }

    private void playGame(Users users, Dealer dealer, Deck deck) {
        for (User user : users.getUsers()) {
            playEachUser(user, deck);
        }
        playDealer(dealer, deck);
    }

    private void playEachUser(User user, Deck deck) {
        while (!user.getHoldingCard().checkBust() && InputView.inputMoreCard(user)) {
            user.receiveCard(deck.drawCard());
            OutputView.printParticipantCards(user.getUserInfo());
        }
    }

    private void playDealer(Dealer dealer, Deck deck) {
        while (dealer.checkUnderScoreStandard()) {
            OutputView.printDealerDraw();
            dealer.receiveCard(deck.drawCard());
        }
    }

    private void gameResult(Users users, Dealer dealer) {
        OutputView.printFinalCard(dealer.getDealerInfoWithScore(), users.getUsersInfoWithScore());

        List<UserResult> userResults = users.getUsersInfoWithResult(dealer.getHoldingCard().cardSum());
        DealerResult dealerResult = new DealerResult(userResults);

        OutputView.printFinalResult(dealerResult, userResults);
    }
}
