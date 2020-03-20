package domain.user;

import common.DealerDto;
import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends User {
    private static final String NAME = "딜러";
    private static final int STANDARD_TO_HIT = 17;
    private final Deck deck;

    private Dealer(Deck deck) {
        super(NAME, passInitCards(deck));
        this.deck = deck;
    }

    public static Dealer shuffle(Deck deck) {
        return new Dealer(deck.shuffle());
    }

    public Profit calculateProfit(Result result, Money bettingMoneyOfPlayer) {
        if (dealerLose(result)) {
            return result.calculateProfit(bettingMoneyOfPlayer).multiply(LOSE_PENALTY_RATE);
        } else {
            return result.calculateProfit(bettingMoneyOfPlayer);
        }
    }

    public int confirmCards() {
        int count = 0;
        while (canHit()) {
            hit(deck.pop());
            count += 1;
        }
        return count;
    }

    private static PlayingCards passInitCards(Deck deck) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Card card = deck.pop();
            cards.add(card);
        }
        return PlayingCards.of(cards);
    }

    PlayingCards passInitCards() {
        return passInitCards(deck);
    }

    public DealerDto serialize() {
        List<String> cards = playingCards.serialize();
        return new DealerDto(NAME, cards);
    }

    public DealerDto serialize(Profit profit) {
        List<String> cards = playingCards.serialize();
        int score = calculateScore();
        return DealerDto.complete(NAME, cards, profit.getValue(), score);
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
