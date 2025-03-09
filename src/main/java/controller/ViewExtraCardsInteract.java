package controller;

import domain.game.ExtraCardsInteract;
import domain.participant.Dealer;
import domain.participant.Gambler;
import view.InputView;
import view.OutputView;

public class ViewExtraCardsInteract implements ExtraCardsInteract {

    private final InputView inputView;
    private final OutputView outputView;

    public ViewExtraCardsInteract(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public boolean listenReceives(Gambler gambler) {
        if (gambler instanceof Dealer) {
            return true;
        }

        return inputView.getYesOrNo(gambler.getName());
    }

    @Override
    public void notifyReceived(Gambler gambler) {
        if (gambler instanceof Dealer) {
            outputView.printDealerHasTaken();
            return;
        }

        outputView.printTakenMoreCards(gambler.getName(), gambler.getCards());
    }

}
