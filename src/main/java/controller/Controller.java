package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.InputView.inputNames;

import model.casino.Casino;
import model.participant.Entrant;
import model.participant.Names;
import model.casino.RandomCardShuffleMachine;

public class Controller {

    public void execute() {
        Names names = inputRetryHelper(() -> new Names(inputNames("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")));
        Entrant entrant = new Entrant(names);
        Casino casino = new Casino(entrant, new RandomCardShuffleMachine());
        casino.initializeGame();

    }
}
