package participant;

import constant.WinningResult;
import game.Card;
import game.Cards;
import game.Deck;

import java.util.List;

public class Player {

    private final Nickname nickname;
    private final Cards cards;

    public Player(Nickname nickname, Deck deck) {
        this.nickname = nickname;
        this.cards = deck.drawInitialCards();
    }

    public WinningResult compareTo(int dealerScore) {
        int playerScore = cards.sumCardNumbers();
        return WinningResult.getWinningResult(playerScore, dealerScore);
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
