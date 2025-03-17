package blackjack.gamer;

import blackjack.bettingMachine.Money;
import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Shape;
import blackjack.cardMachine.CardMachine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer extends Gamer {

    private static final String NICKNAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    private final CardMachine cardMachine;
    private final Money earnMoney;

    public Dealer(final CardMachine cardMachine) {
        this.cardMachine = cardMachine;
        this.earnMoney = new Money(0);

        initCardMachine();
    }

    public List<Card> spreadTwoCards() {
        final Card firstCard = spreadOneCard();
        final Card secondCard = spreadOneCard();
        return new ArrayList<>(List.of(firstCard, secondCard));
    }

    public Card spreadOneCard() {
        return cardMachine.drawOneCard();
    }

    public boolean isHit() {
        return hand.sumCards() <= HIT_THRESHOLD;
    }

    public void updateEarnedMoney(final long money) {
        earnMoney.add(money);
    }

    public long getProfit() {
        return earnMoney.getMoney();
    }

    @Override
    public List<Card> showInitialCards() {
        return List.of(hand.openOneCard());
    }

    @Override
    public String getNickName() {
        return NICKNAME;
    }

    private void initCardMachine() {
        final List<Card> deck = organizeDeck();
        cardMachine.receiveDeck(deck);
    }

    private List<Card> organizeDeck() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(shape, denomination)))
                .toList();
    }
}
