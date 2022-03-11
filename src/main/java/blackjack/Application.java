package blackjack;

import static blackjack.model.player.Dealer.dealerName;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.blackjack.Blackjack;
import blackjack.model.blackjack.CardDispenser;
import blackjack.model.blackjack.Records;
import blackjack.model.player.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() {
        List<Name> playerNames = names();
        Blackjack blackjack = new Blackjack(CardDispenser.shuffledCardDispenser(), playerNames);
        printOpenCard(blackjack, playerNames);
        takeCards(blackjack, playerNames);
        printTotalScore(blackjack, playerNames);
        printRecord(blackjack, playerNames);
    }

    private static List<Name> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(",")).stream()
            .map(Name::new)
            .collect(toUnmodifiableList());
    }

    private static void printOpenCard(Blackjack blackjack, List<Name> playerNames) {
        OutputView.printOpenCardMessage(dealerName(), playerNames);
        OutputView.printOpenCard(dealerName(), blackjack.openedCardsByName(dealerName()));
        for (Name name : playerNames) {
            OutputView.printOpenCard(name, blackjack.openedCardsByName(name));
        }
    }

    private static void takeCards(Blackjack blackjack, List<Name> playerNames) {
        for (Name name : playerNames) {
            takePlayerCard(blackjack, name);
        }
        takeDealerCard(blackjack);
    }

    private static void takePlayerCard(Blackjack blackjack, Name name) {
        while (blackjack.isHittableByName(name) && isKeepTakeCard(name)) {
            blackjack.takeCardByName(name);
            OutputView.printCards(name, blackjack.ownCardsByName(name));
        }
    }

    private static boolean isKeepTakeCard(Name name) {
        String option = InputView.chooseOptions(name.value());
        return option.equals("y");
    }

    private static void takeDealerCard(Blackjack blackjack) {
        while (blackjack.isHittableByName(dealerName())) {
            blackjack.takeCardByName(dealerName());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private static void printTotalScore(Blackjack blackjack, List<Name> playerNames) {
        OutputView.printTotalScore(dealerName(), blackjack.ownCardsByName(dealerName()),
            blackjack.scoreByName(dealerName()));

        for (Name name : playerNames) {
            OutputView.printTotalScore(name, blackjack.ownCardsByName(name),
                blackjack.scoreByName(name));
        }
    }

    private static void printRecord(Blackjack blackjack, List<Name> playerNames) {
        Records records = blackjack.records();
        OutputView.printDealerRecord(records.recordByName(dealerName()));
        for (Name name : playerNames) {
            OutputView.printPlayerRecord(records.recordByName(name));
        }
    }
}
