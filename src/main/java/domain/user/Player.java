package domain.user;

import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;
import static view.Command.YES;

import domain.Deck;
import domain.card.Card;
import domain.money.GameResult;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import view.Command;
import view.OutputView;

public class Player {
    public static final int RECEIVABLE_THRESHOLD = 21;
    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void receiveCard(Function<String, Command> commandFunction, Deck deck) {
        if (isBlackjack()) {
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
        if (hand.isBusted()) {
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

    public GameResult generateResult(Dealer dealer) {
        if (hand.isBusted() || isDealerBlackjackOnly(dealer)) {
            return LOSE;
        }
        if (dealer.isBusted() || isPlayerBlackjackOnly(dealer)) {
            return WIN;
        }
        return compare(dealer.sumCard());
    }

    private GameResult compare(int opponent) {
        return Arrays.stream(GameResult.values())
                .filter(result -> result.getCondition().test(hand.sumCard(), opponent))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("입력에 따른 결과가 존재하지 않습니다."));
    }

    private boolean isDealerBlackjackOnly(Dealer dealer) {
        return !isBlackjack() && dealer.isBlackjack();
    }

    private boolean isPlayerBlackjackOnly(Dealer dealer) {
        return isBlackjack() && !dealer.isBlackjack();
    }

    public List<Card> getAllCards() {
        return hand.getCards();
    }

    public String getNameValue() {
        return name.value();
    }
}
