package blackjack;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.blackjack.CardDispenser;
import blackjack.model.player.Name;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.blackjack.Result;
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
        CardDispenser cardDispenser = CardDispenser.shuffledCardDispenser();
        Dealer dealer = createDealer(cardDispenser);
        List<Player> gamers = createGamers(names(), cardDispenser);
        OutputView.printOpenCard(dealer, gamers);
        takeCards(cardDispenser, dealer, gamers);
        OutputView.printTotalScore(dealer, gamers);
        printRecord(dealer, gamers);
    }

    private static Dealer createDealer(CardDispenser cardDispenser) {
        return new Dealer(cardDispenser.issue(), cardDispenser.issue());
    }

    private static List<Name> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(",")).stream()
            .map(Name::new)
            .collect(toUnmodifiableList());
    }

    private static List<Player> createGamers(List<Name> names, CardDispenser cardDispenser) {
        return names.stream()
            .map(name -> createEachGamer(cardDispenser, name))
            .collect(Collectors.toList());
    }

    private static Player createEachGamer(CardDispenser cardDispenser, Name name) {
        return new Player(name, cardDispenser.issue(), cardDispenser.issue());
    }

    private static void takeCards(CardDispenser cardDispenser, Dealer dealer, List<Player> gamers) {
        for (Player gamer : gamers) {
            takeGamerCard(gamer, cardDispenser);
        }
        takeDealerCard(dealer, cardDispenser);
    }

    private static void takeGamerCard(Player gamer, CardDispenser cardDispenser) {
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
        String option = InputView.chooseOptions(gamer.name().value());
        return option.equals("y");
    }

    private static void printRecord(Dealer dealer, List<Player> gamers) {
        printDealerRecord(dealer, gamers);
        printGamerRecords(dealer, gamers);
    }

    private static void printDealerRecord(Dealer dealer, List<Player> gamers) {
        Map<Result, Integer> result = new HashMap<>();
        for (Player gamer : gamers) {
            result.merge(dealer.match(gamer), 1, Integer::sum);
        }
        OutputView.printDealerRecord(result);
    }

    private static void printGamerRecords(Dealer dealer, List<Player> gamers) {
        for (Player gamer : gamers) {
            Result result = dealer.match(gamer);
            OutputView.printGamerRecord(gamer.name().value(), result.reverse().symbol());
        }
    }
}
