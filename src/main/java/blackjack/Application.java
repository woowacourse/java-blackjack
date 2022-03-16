package blackjack;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.card.CardDispenser;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Gamers;
import blackjack.model.player.Money;
import blackjack.view.Answer;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.IntStream;

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
        Dealer dealer = new Dealer(cardDispenser.issue(), cardDispenser.issue());
        Gamers gamers = new Gamers(createGamers(cardDispenser));
        playBlackjack(cardDispenser, dealer, gamers);
    }

    private static List<Gamer> createGamers(CardDispenser cardDispenser) {
        List<String> names = InputView.inputNames();
        List<Money> moneys = moneys(names);
        return IntStream.range(0, names.size())
            .mapToObj(i -> createGamer(names.get(i), moneys.get(i), cardDispenser))
            .collect(toUnmodifiableList());
    }

    private static Gamer createGamer(String name, Money money, CardDispenser cardDispenser) {
        return new Gamer(name, money, cardDispenser.issue(), cardDispenser.issue());
    }

    private static List<Money> moneys(List<String> names) {
        return names.stream()
            .map(InputView::inputMoneys)
            .map(Money::new)
            .collect(toUnmodifiableList());
    }

    private static void playBlackjack(CardDispenser cardDispenser, Dealer dealer, Gamers gamers) {
        printOpenCard(dealer, gamers);
        takeCards(cardDispenser, dealer, gamers);
        printResults(dealer, gamers);
    }

    private static void printOpenCard(Dealer dealer, Gamers gamers) {
        OutputView.printOpenCardMessage(dealer.name(), playerNames(gamers));
        OutputView.printOpenCard(dealer.name(), dealer.openCards());
        for (Gamer gamer : gamers.values()) {
            OutputView.printOpenCard(gamer.name(), gamer.openCards());
        }
    }

    private static List<String> playerNames(Gamers gamers) {
        return gamers.values().stream().map(Gamer::name).collect(toUnmodifiableList());
    }

    private static void takeCards(CardDispenser cardDispenser, Dealer dealer, Gamers gamers) {
        for (Gamer gamer : gamers.values()) {
            takePlayerCard(gamer, cardDispenser);
        }
        takeDealerCard(dealer, cardDispenser);
    }

    private static void takePlayerCard(Gamer gamer, CardDispenser cardDispenser) {
        while (gamer.isHittable() && isKeepTakeCard(gamer.name())) {
            gamer.take(cardDispenser.issue());
            OutputView.printCards(gamer.name(), gamer.cards());
        }
    }

    private static boolean isKeepTakeCard(String name) {
        Answer answer = InputView.isKeepTakeCard(name);
        return answer.isKeepGoing();
    }

    private static void takeDealerCard(Dealer dealer, CardDispenser cardDispenser) {
        while (dealer.isHittable()) {
            dealer.take(cardDispenser.issue());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private static void printResults(Dealer dealer, Gamers gamers) {
        OutputView.printTotalScore(dealer.name(), dealer.cards(), dealer.score());
        for (Gamer gamer : gamers.values()) {
            OutputView.printTotalScore(gamer.name(), gamer.cards(), gamer.score());
        }
        OutputView.printRecords(gamers.match(dealer));
    }
}
