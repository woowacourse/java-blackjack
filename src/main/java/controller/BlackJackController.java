package controller;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJackController {
    public void run(){
        List<String> players = getPlayer();
    }
    private List<String> getPlayer(){
        OutputView.inputPlayerMessage();
        String input = InputView.input();
        return Arrays.asList(input.split(","));
    }
}
