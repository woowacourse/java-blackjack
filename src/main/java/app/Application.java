package app;

import controller.Casino;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        new Casino(new InputView(), new ResultView()).open();
    }
}
