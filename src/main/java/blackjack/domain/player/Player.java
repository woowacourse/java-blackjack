package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.WinOrLose;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.ResultOfGamer;
import blackjack.exception.CardDuplicateException;

import java.util.List;

public abstract class Player {

    private final Deck deck;
    private final String name;
    private final int bettingMoney;

    public Player(String name, int bettingMoney) {
        this.deck = new Deck();
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public void addCardToDeck(Card card) {
        if (deck.contains(card)) {
            throw new CardDuplicateException();
        }

        deck.add(card);
    }

    public ResultOfGamer getResult(Player dealer) {
        return new ResultOfGamer(
                name,
                calculateWinning(dealer),
                getRevenue(dealer)
        );
    }

    private int getRevenue(Player dealer) {
        if (calculateWinning(dealer) == WinOrLose.WIN) {
            return bettingMoney;
        }

        return -bettingMoney;
    }

    private WinOrLose calculateWinning(Player dealer) {
        return WinOrLose.calculate(dealer, this);
    }

    public List<Card> getDeckAsList() {
        return deck.getCards();
    }

    public int getScore() {
        return deck.getScore();
    }

    public Status getStatus() {
        return Status.evaluateScore(getScore());
    }

    public String getName() {
        return name;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public boolean isSameName(String name) {
        return getName().equals(name);
    }

    public abstract boolean isDrawable();

}
