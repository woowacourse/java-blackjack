package blackjack;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.card.CardDeck;
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
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck.next(), cardDeck.next());
        Gamers gamers = new Gamers(createGamers(cardDeck));
        playBlackjack(cardDeck, dealer, gamers);
    }

    private static List<Gamer> createGamers(CardDeck cardDeck) {
        List<String> names = InputView.inputNames();
        List<Money> moneys = moneys(names);
        return IntStream.range(0, names.size())
            .mapToObj(i -> createGamer(names.get(i), moneys.get(i), cardDeck))
            .collect(toUnmodifiableList());
    }

    private static Gamer createGamer(String name, Money money, CardDeck cardDeck) {
        return new Gamer(name, money, cardDeck.next(), cardDeck.next());
    }

    private static List<Money> moneys(List<String> names) {
        return names.stream()
            .map(InputView::inputMoneys)
            .map(Money::new)
            .collect(toUnmodifiableList());
    }

    private static void playBlackjack(CardDeck cardDeck, Dealer dealer, Gamers gamers) {
        printOpenCard(dealer, gamers);
        takeCards(cardDeck, dealer, gamers);
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

    private static void takeCards(CardDeck cardDeck, Dealer dealer, Gamers gamers) {
        for (Gamer gamer : gamers.values()) {
            takePlayerCard(gamer, cardDeck);
        }
        takeDealerCard(dealer, cardDeck);
    }

    private static void takePlayerCard(Gamer gamer, CardDeck cardDeck) {
        while (gamer.isHittable() && isKeepTakeCard(gamer.name())) {
            gamer.take(cardDeck.next());
            OutputView.printCards(gamer.name(), gamer.cards());
        }
    }

    private static boolean isKeepTakeCard(String name) {
        Answer answer = InputView.isKeepTakeCard(name);
        return answer.isKeepGoing();
    }

    private static void takeDealerCard(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isHittable()) {
            dealer.take(cardDeck.next());
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
