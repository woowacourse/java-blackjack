package blackjack;

import blackjack.model.CardDispenser;
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
        CardDispenser cardDispenser = new CardDispenser();
        Dealer dealer = createDealer(cardDispenser);
        List<Gamer> gamers = createGamers(names(), cardDispenser);
        OutputView.printOpenCard(dealer, gamers);
        takeCards(cardDispenser, dealer, gamers);
        OutputView.printTotalScore(dealer, gamers);
        printRecord(dealer, gamers);
    }

    private static Dealer createDealer(CardDispenser cardDispenser) {
        return new Dealer(cardDispenser.issue(), cardDispenser.issue());
    }

    private static List<String> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(","));
    }

    private static List<Gamer> createGamers(List<String> names, CardDispenser cardDispenser) {
        return names.stream()
            .map(name -> createEachGamer(cardDispenser, name))
            .collect(Collectors.toList());
    }

    private static Gamer createEachGamer(CardDispenser cardDispenser, String name) {
        return new Gamer(name, cardDispenser.issue(), cardDispenser.issue());
    }

    private static void takeCards(CardDispenser cardDispenser, Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            takeGamerCard(gamer, cardDispenser);
        }
        takeDealerCard(dealer, cardDispenser);
    }

    private static void takeGamerCard(Gamer gamer, CardDispenser cardDispenser) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardDispenser.issue());
            OutputView.printCard(gamer);
        }
    }

    private static void takeDealerCard(Dealer dealer, CardDispenser cardDispenser) {
        while (dealer.isHittable()) {
            dealer.take(cardDispenser.issue());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private static boolean isKeepTakeCard(Player gamer) {
        String option = InputView.chooseOptions(gamer.name());
        return option.equals("y");
    }

    private static void printRecord(Dealer dealer, List<Gamer> gamers) {
        printDealerRecord(dealer, gamers);
        printGamerRecords(dealer, gamers);
    }

    private static void printDealerRecord(Dealer dealer, List<Gamer> gamers) {
        Map<Result, Integer> result = new HashMap<>();
        for (Gamer gamer : gamers) {
            result.merge(dealer.match(gamer), 1, Integer::sum);
        }
        OutputView.printDealerRecord(result);
    }

    private static void printGamerRecords(Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            Result result = dealer.match(gamer);
            OutputView.printGamerRecord(gamer.name(), result.reverse().symbol());
        }
    }
}
