package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.CardPicker;
import blackjack.domain.RandomCardPicker;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.Scanner;

public class BlackjackApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        CardPicker cardPicker = new RandomCardPicker();

        // BlackjackController blackjackController = new BlackjackController(inputView, outputView, cardPicker);

    }
}
