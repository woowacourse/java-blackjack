package blakjack;

import blakjack.view.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        List<String> names = InputView.inputPlayerNames();
    }
}
