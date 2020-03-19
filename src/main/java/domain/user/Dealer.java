package domain.user;

import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.Result;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends User {
    private static final String NAME = "딜러";
    public static final int INIT_CARDS_SIZE = 2;
    private Deck deck;

    public Dealer(Deck deck, Money money) {
        super(deck.popInitCards(), NAME, money);
        this.deck = deck;
    }

    @Override
    public Money calculateProfit(Result result) {
        if (dealerLose(result)) {
            return result.calculateProfit(money).multiply(LOSE_PENALTY_RATE);
        }
        return result.calculateProfit(money);
    }

    //todo: refac parameters
    List<Player> passInitCards(List<String> names, List<Money> bettingMoneys) {
        List<Player> players = new ArrayList<>();
        if (names.size() != bettingMoneys.size()) {
            throw new IllegalArgumentException("갯수가 맞지 않습니다.");
        }
        int playersSize = names.size();
        for (int i = 0; i < playersSize; i++) {
            players.add(new Player(deck.popInitCards(), names.get(i), bettingMoneys.get(i)));
        }
        return players;
    }

    private boolean dealerLose(Result result) {
        return result.equals(Result.PLAYER_WIN_WITH_BLACKJACK) || result.equals(Result.PLAYER_WIN_WITHOUT_BLACKJACk);
    }

    void confirmCards(int hitSize) {
        for (int i = 0; i < hitSize; i++) {
            Card card = deck.pop();
            hit(card);
        }
    }
}
