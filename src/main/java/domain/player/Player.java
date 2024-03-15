package domain.player;

import static view.Command.YES;

import domain.Deck;
import domain.card.Card;
import domain.money.GameResult;
import java.util.List;
import java.util.function.Function;
import view.Command;
import view.OutputView;

public class Player {
    public static final int RECEIVABLE_THRESHOLD = 21;
    private final Name name;
    private final Hand hand;

    public Player(Name name, Card... cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    public void receiveCard(Function<String, Command> commandFunction, Deck deck) {
        if (hand.isBlackjack()) {
            OutputView.printBlackjack(name.value());
            return;
        }
        while (isReceivable() && YES == commandFunction.apply(name.value())) {
            hand.receiveCard(deck.drawCard());
            printByState();
        }
    }

    private void printByState() {
        OutputView.printUserAndCards(name.value(), hand.getCards());
        if (hand.busted()) {
            OutputView.printBust();
        }
    }

    public int sumHand() {
        return hand.sumCard();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    private boolean isReceivable() {
        return hand.sumCard() < RECEIVABLE_THRESHOLD;
    }

    public GameResult generatePlayerResult(Hand dealerHand) {
        return hand.generateResult(dealerHand);
    }

    public List<Card> getAllCards() {
        return hand.getCards();
    }

    public String getNameValue() {
        return name.value();
    }
}
