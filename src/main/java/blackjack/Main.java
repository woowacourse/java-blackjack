package blackjack;

import blackjack.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.play();
        controller.computeResult();
    }
}
