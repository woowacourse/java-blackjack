package controller;

import static util.Constants.COMMA_DELIMITER;
import static util.Constants.DEFAULT_CARD_SET;

import domain.player.Participants;
import java.util.List;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputGambersInfo();
        
        // 한 덱으로 게임 만들기
        Participants participants = new Participants(names, DEFAULT_CARD_SET_COUNT);
        participants.initializeGame();
    }
    
    private List<String> inputGambersInfo() {
        String name = inputView.askGamblerNames().name();
        return Parser.parse(name, COMMA_DELIMITER);
    }
}