package blackjack;

import blackjack.model.Gamer;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        try {
            List<String> names = names();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<String> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(","));
    }

}
