package blackjack.gamer;

import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Hand;
import blackjack.card.Shape;
import blackjack.cardMachine.CardMachine;
import java.util.Arrays;
import java.util.List;

public class Dealer extends Gamer {

    private static final String NICKNAME = "딜러";

    private final CardMachine cardMachine;

    public Dealer(final Hand hand, final CardMachine cardMachine) {
        super(hand);
        this.cardMachine = cardMachine;
    }

    private void initCardMachine() {
        List<Card> deck = organizeDeck();
        cardMachine.receiveDeck(deck);
    }

    private List<Card> organizeDeck() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(shape, denomination)))
                .toList();
    }

    private Card spreadOneCard() {
        return cardMachine.drawOneCard();
    }
}
