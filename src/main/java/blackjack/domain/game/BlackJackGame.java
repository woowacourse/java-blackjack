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
        giveCards(dealer, NUMBER_OF_INITIAL_CARDS);
        for (Gambler gambler : gamblers) {
            giveCards(gambler, NUMBER_OF_INITIAL_CARDS);
        }
    }

    private void giveCards(final Player player, int numberOfCards) {
        IntStream.range(0, numberOfCards)
                .forEach(i -> giveCard(player));
    }

    public void giveCard(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void bet(Gambler gambler, int money) {
        dealer.takeMoney(gambler, new Money(money));
    }

    public void checkBlackJack() {
        // TODO :: 정리 -> Gamblers로 넘기기
        for (Gambler gambler : gamblers) {
            if (!gambler.hasBlackJack()) {
                continue;
            }

            if (dealer.hasBlackJack()) {
                dealer.giveBackBettingMoney(gambler);
            }

            dealer.giveMoneyByBlackJack(gambler);
        }
    }

    public void calculateMoney() {
        if (dealer.isBust()) {
            calculateBettingMoneyWhenDealerBust();
        }

        if (!dealer.isBust()) {
            calculateBettingMoneyWhenDealerNotBust();
        }
    }

    private void calculateBettingMoneyWhenDealerBust() {
        // TODO :: 정리 -> Gamblers로 넘기기
        for (Gambler gambler : gamblers) {
            if (gambler.hasBlackJack()) {
                continue;
            }

            if (gambler.isBust()) {
                dealer.giveBackBettingMoney(gambler);
                continue;
            }
            dealer.giveWinningMoney(gambler);
        }
    }

    private void calculateBettingMoneyWhenDealerNotBust() {
        // TODO :: 정리 -> Gamblers로 넘기기
        for (Gambler gambler : gamblers) {
            // TODO :: dealer.isWin(player)로 고치기
            if (gambler.hasBlackJack()) {
                continue;
            }

            if (dealer.calculateGamblerWinOrNot(gambler).equals(WinOrLose.WIN)) {
                dealer.giveWinningMoney(gambler);
            }

            if (dealer.calculateGamblerWinOrNot(gambler).equals(WinOrLose.DRAW)) {
                dealer.giveBackBettingMoney(gambler);
            }
        }
    }

    public WinningResult calculateWinningResult() {
        WinningResult result = new WinningResult(dealer.getCards());
        for (Player player : gamblers) {
            addGamblerResult(result, player);
        }
        return result;
    }

    private void addGamblerResult(final WinningResult winningResult, final Player player) {
        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
        winningResult.addGamblerResults(player, winOrLose);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }
}
