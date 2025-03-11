package controller;

import domain.game.GamblerAnswer;
import domain.participant.Gambler;
import view.OutputView;

public class ViewDealerAnswer implements GamblerAnswer {

    private final OutputView outputView;

    public ViewDealerAnswer(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public boolean isAnswerOK(Gambler gambler) {
        return true; // always OK
    }

    @Override
    public void notifyResult(Gambler gambler) {
        outputView.printDealerHasTaken();
    }
}
