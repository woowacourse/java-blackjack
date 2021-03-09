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
    private final Dealer dealer;
    private final Gamblers gamblers;

    public BlackJackGame(String nameLine){
        dealer = new Dealer();
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

    private void giveInitialCards(){
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
        for (Gambler gambler : gamblers) {
            dealer.checkBlackJack(gambler);
        }
    }

    public void calculateMoney() {
        for (Gambler gambler : gamblers) {
            if (gambler.hasBlackJack()) {
                continue;
            }
            giveWinningMoney(gambler);
        }
    }

    private void giveWinningMoney(Gambler gambler){
        if(dealer.calculateWinOrLose(gambler).equals(WinOrLose.LOSE)){
            dealer.giveWinningMoney(gambler);
        }

        if(dealer.calculateWinOrLose(gambler).equals(WinOrLose.DRAW)){
            dealer.giveBackBettingMoney(gambler);
        }

        "1".hashCode();
    }

//    public WinningResult calculateWinningResult() {
//        WinningResult result = new WinningResult(dealer.getCards());
//        for (Player player : gamblers) {
//            addGamblerResult(result, player);
//        }
//        return result;
//    }

//    private void addGamblerResult(final WinningResult winningResult, final Player player) {
//        WinOrLose winOrLose = dealer.calculateGamblerWinOrNot(player);
//        winningResult.addGamblerResults(player, winOrLose);
//    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }

}
