package blackjack.controller;

import blackjack.model.CardGenerator;
import blackjack.model.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;
import blackjack.model.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    public void play() {
        CardGenerator cardGenerator = new CardGenerator();
        Dealer dealer = createDealer(cardGenerator);
        List<Gamer> gamers = createGamers(names(), cardGenerator);
        OutputView.printOpenCard(dealer, gamers);
        takeCards(cardGenerator, dealer, gamers);
        OutputView.printTotalScore(dealer, gamers);
        printRecord(dealer, gamers);
    }

    private Dealer createDealer(CardGenerator cardGenerator) {
        return new Dealer(cardGenerator.generate(), cardGenerator.generate());
    }

    private List<String> names() {
        String namesText = InputView.inputNames();
        return List.of(namesText.split(","));
    }

    private List<Gamer> createGamers(List<String> names, CardGenerator cardGenerator) {
        return names.stream()
                .map(name -> createEachGamer(name, cardGenerator))
                .collect(Collectors.toList());
    }

    private Gamer createEachGamer(String name, CardGenerator cardGenerator) {
        return new Gamer(name, cardGenerator.generate(), cardGenerator.generate());
    }

    private void takeCards(CardGenerator cardGenerator, Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            takeGamerCard(gamer, cardGenerator);
        }
        takeDealerCard(dealer, cardGenerator);
    }

    private void takeGamerCard(Gamer gamer, CardGenerator cardGenerator) {
        while (gamer.isHittable() && isKeepTakeCard(gamer)) {
            gamer.take(cardGenerator.generate());
            OutputView.printCard(gamer);
        }
    }

    private void takeDealerCard(Dealer dealer, CardGenerator cardGenerator) {
        while (dealer.isHittable()) {
            dealer.take(cardGenerator.generate());
            OutputView.printDealerTakeCardMessage();
        }
    }

    private boolean isKeepTakeCard(Player gamer) {
        String option = InputView.chooseOptions(gamer.name());
        return option.equals("y");
    }

    private void printRecord(Dealer dealer, List<Gamer> gamers) {
        printDealerRecord(dealer, gamers);
        printGamerRecords(dealer, gamers);
    }

    private void printDealerRecord(Dealer dealer, List<Gamer> gamers) {
        Map<Result, Integer> result = new HashMap<>();
        for (Gamer gamer : gamers) {
            result.merge(dealer.match(gamer.cards()), 1, Integer::sum);
        }
        OutputView.printDealerRecord(result);
    }

    private void printGamerRecords(Dealer dealer, List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            Result result = dealer.match(gamer.cards());
            OutputView.printGamerRecord(gamer.name(), result.reverse().symbol());
        }
    }
}
