package domain.user;

import common.DealerDto;
import domain.card.Card;
import domain.card.Deck;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private static final int STANDARD_TO_HIT = 17;
    private Deck deck;

    public Dealer(Deck deck) {
        super(deck.popInitCards(), NAME);
        this.deck = deck;
    }

    public static Dealer start(Deck deck) {
        return new Dealer(deck);
    }

    public Money calculateProfit(Result result, Money bettingMoneyOfPlayer) {
        if (dealerLose(result)) {
            return result.calculateProfit(bettingMoneyOfPlayer).multiply(LOSE_PENALTY_RATE);
        }
        return result.calculateProfit(bettingMoneyOfPlayer);
    }

    //todo: refac function name
    public int confirmCards() {
        int count = 0;
        while (canHit()) {
            hit(deck.pop());
            count += 1;
        }
        return count;
    }

    //todo: refac parameters
    public List<Player> passInitCards(List<String> names, List<Money> bettingMoneys) {
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

    public DealerDto serialize() {
        List<String> cards = playingCards.serialize();
        return new DealerDto(NAME, cards);
    }

    public DealerDto serialize(Money sumOfProfit) {
        List<String> cards = playingCards.serialize();
        int score = calculateScore();
        return DealerDto.complete(NAME, cards, sumOfProfit.serialize(), score);
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

    private boolean canHit() {
        int score = calculateScore();
        return score < STANDARD_TO_HIT;
    }

    public Card passCard() {
        return deck.pop();
    }

    @Override
    public Result match(User user, MatchRule matchRule) {
        return Result.judge(matchRule, user, this);
    }
}
