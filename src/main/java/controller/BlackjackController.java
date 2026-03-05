package controller;

import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readPlayerNames();
        OutputView.printCardOpen(names);
    }
}
