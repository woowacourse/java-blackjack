package controller;

import domain.game.GamblerAnswer;
import domain.participant.Gambler;
import view.InputView;
import view.OutputView;

public class ViewPlayerAnswer implements GamblerAnswer {

    private final InputView inputView;
    private final OutputView outputView;

    public ViewPlayerAnswer(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public boolean isAnswerOK(Gambler gambler) {
        return inputView.getYesOrNo(gambler.getName());
    }

    @Override
    public void notifyResult(Gambler gambler) {
        outputView.printTakenMoreCards(gambler.getName(), gambler.getCards());
    }
}
