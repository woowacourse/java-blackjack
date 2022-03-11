package controller;

import java.util.List;
import model.CardDeck;
import model.Dealer;
import model.Participator;
import model.Participators;
import model.cardbehavior.EveryCardsBehavior;
import view.InputView;
import view.OutputView;

public class BlackJackGame {
    private static final int INIT_CARD_COUNT = 2;

    private Participators participators;
    private CardDeck cardDeck;

    public void run() {
        initGame();
        hitPlayers();
        hitDealer();
        getResult();
        match();
    }

    public void initGame() {
        List<String> names = InputView.inputPlayerNames();
        shareTwoCards(names);
        OutputView.printInitResult(participators, INIT_CARD_COUNT);
    }

    private void shareTwoCards(List<String> names) {
        participators = new Participators(names);
        cardDeck = new CardDeck();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            participators.receiveCards(cardDeck);
        }
    }

    public void hitPlayers() {
        for (Participator player : participators.findPlayers()) {
            while (player.canReceiveCard() && InputView.inputHitResponse(player)) {
                player.receiveCard(cardDeck.drawCard());
                OutputView.printHasCards(player);
            }
        }

    }

    public void hitDealer() {
        Dealer dealer = participators.findDealer();
        if (dealer.canReceiveCard()) {
            dealer.receiveCard(cardDeck.drawCard());
            OutputView.printDealerReceiveCard();
        }
    }

    public void getResult() {
        participators.findDealer().setBehavior(new EveryCardsBehavior());
        OutputView.printAllCardsAndCardSum(participators);
    }

    public void match() {
        OutputView.printFinalResult(participators.getDealerFinalMatchResult(),
                participators.getPlayersFinalMatchResult());
    }
}
