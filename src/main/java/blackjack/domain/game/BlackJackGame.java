package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;

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

    public BlackJackGame(final String nameLine) {
        gamblers = initGamblerWithNames(splitAndParseToNames(nameLine));
        distributeInitialCards();
    }

    private List<Name> splitAndParseToNames(final String nameLine) {
        return Arrays.asList(nameLine.split(SEPARATOR_OF_NAME_INPUT))
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private Gamblers initGamblerWithNames(final List<Name> names) {
        final List<Gambler> gamblers = names.stream()
                .map(Gambler::new)
                .collect(Collectors.toList());
        return new Gamblers(gamblers);
    }

    private void distributeInitialCards() {
        giveCards(dealer, NUMBER_OF_INITIAL_CARDS);
        for (final Gambler gambler : gamblers) {
            giveCards(gambler, NUMBER_OF_INITIAL_CARDS);
        }
    }

    private void giveCards(final Player player, final int numberOfCards) {
        IntStream.range(0, numberOfCards)
                .forEach(i -> giveCard(player));
    }

    public void giveCard(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void bet(final Gambler gambler, final int money) {
        dealer.takeMoney(gambler, new Money(money));
    }

    public void checkBlackJack() {
        for (final Gambler gambler : gamblers) {
            dealer.checkBlackJack(gambler);
        }
    }

    public void calculateMoney() {
        final WinningResult winningResult = calculateResult();
        for (final Gambler gambler : gamblers) {
            giveWinningMoney(gambler, winningResult.get(gambler));
        }
    }

    private void giveWinningMoney(final Gambler gambler, final WinOrLose winOrLose) {
        if (gambler.hasBlackJack()) {
            return;
        }
        dealer.calculateMoney(gambler, winOrLose);
    }

    public WinningResult calculateResult() {
        final WinningResult winningResult = new WinningResult();
        for (final Gambler gambler : gamblers) {
            winningResult.add(gambler, dealer.calculateWinOrLose(gambler));
        }
        return winningResult;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }
}
