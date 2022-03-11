package blackjack;

import blackjack.model.CardGenerator;
import blackjack.model.Dealer;
import blackjack.model.Gamer;
import blackjack.model.Player;
import blackjack.model.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() {
        CardGenerator cardGenerator = new CardGenerator();
        Dealer dealer = createDealer(cardGenerator);
        List<Gamer> gamers = createGamers(names(), cardGenerator);
        OutputView.printOpenCard(dealer, gamers);
        takeCards(cardGenerator, dealer, gamers);
        OutputView.printTotalScore(dealer, gamers);
        printRecord(dealer, gamers);
    }

    private static Dealer createDealer(CardGenerator cardGenerator) {
        return new Dealer(cardGenerator.generate(), cardGenerator.generate());
    }

    private static List<String> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(","));
    }

    private static List<Gamer> createGamers(List<String> names, CardGenerator cardGenerator) {
        return names.stream()
            .map(name -> createEachGamer(cardGenerator, name))
            .collect(Collectors.toList());
    }

    private static Gamer createEachGamer(CardGenerator cardGenerator, String name) {
        return new Gamer(name, cardGenerator.generate(), cardGenerator.generate());
    }

    private static void takeCards(CardGenerator cardGenerator, Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            takeGamerCard(gamer, cardGenerator);
        }
        takeDealerCard(dealer, cardGenerator);
    }

    private static void takeGamerCard(Gamer gamer, CardGenerator cardGenerator) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardGenerator.generate());
            OutputView.printCard(gamer);
        }
    }

    private static void takeDealerCard(Dealer dealer, CardGenerator cardGenerator) {
        while (dealer.isHittable()) {
            dealer.take(cardGenerator.generate());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private static boolean isKeepTakeCard(Player gamer) {
        String option = InputView.chooseOptions(gamer.name());
        return option.equals("y");
    }

    private static void printRecord(Dealer dealer, List<Gamer> gamers) {
        Map<Result, Integer> result = new HashMap<>();
        for (Gamer gamer : gamers) {
            result.merge(dealer.match(gamer.cards()), 1, Integer::sum);
        }
        OutputView.printDealerRecord(result);
    }
}
