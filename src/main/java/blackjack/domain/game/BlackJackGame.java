package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlackJackGame {

    private final String SEPARATOR_OF_NAME_INPUT = ",";
    private final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Deck deck = new Deck();
    private final Dealer dealer = new Dealer();
    private final Gamblers gamblers;

    public BlackJackGame(String nameLine) {
        gamblers = initGamblerWithNames(splitAndParseToNames(nameLine));
        giveInitialCards();
    }

    private List<Name> splitAndParseToNames(String nameLine) {
        return Arrays.asList(nameLine.split(SEPARATOR_OF_NAME_INPUT))
                .stream().map(Name::new)
                .collect(Collectors.toList());
    }

    private Gamblers initGamblerWithNames(List<Name> names) {
        List<Gambler> gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
        return new Gamblers(gamblers);
    }

    private void giveInitialCards() {
        giveCard(dealer, NUMBER_OF_INITIAL_CARDS);
        for (Gambler gambler : gamblers) {
            giveCard(gambler, NUMBER_OF_INITIAL_CARDS);
        }
    }

    public void giveCard(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void giveCard(final Player player, int numberOfCards) {
        IntStream.range(0, numberOfCards)
                .forEach(i -> giveCard(player));
    }

    public Result getResult() {
        Result result = new Result(dealer.getCards());
        for (Player player : gamblers) {
            addGamblerResult(result, player);
        }
        return result;
    }

    private void addGamblerResult(final Result result, final Player player) {
        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
        result.addGamblerResults(player, winOrLose);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }

}
