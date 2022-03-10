package blackjack;

import blackjack.model.CardGenerator;
import blackjack.model.Dealer;
import blackjack.model.Gamer;
import blackjack.model.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        CardGenerator cardGenerator = new CardGenerator();
        try {
            List<String> names = names();
            Dealer dealer = new Dealer(cardGenerator.generate(), cardGenerator.generate());
            List<Player> gamers = createGamer(names, cardGenerator);
            OutputView.printOpenCard(dealer, gamers);

            for (Player gamer : gamers) {
                takeCard(gamer, cardGenerator);
            }

            takeDealerCard(dealer, cardGenerator);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static void takeDealerCard(Dealer dealer, CardGenerator cardGenerator) {
        while (dealer.isHittable()) {
            dealer.take(cardGenerator.generate());
            OutputView.printDealerTakeCardMessage();
        }

    }

    private static void takeCard(Player gamer, CardGenerator cardGenerator) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardGenerator.generate());
            OutputView.printCard(gamer);
        }
    }

    private static boolean isKeepTakeCard(Player gamer) {
        String option = InputView.chooseOptions(gamer.name());
        return option.equals("y");
    }

    private static List<String> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(","));
    }

    private static List<Player> createGamer(List<String> names, CardGenerator cardGenerator) {
        List<Player> gamers = new ArrayList<>();
        for (String name : names) {
            gamers.add(new Gamer(name, cardGenerator.generate(), cardGenerator.generate()));
        }
        return gamers;
    }
}
