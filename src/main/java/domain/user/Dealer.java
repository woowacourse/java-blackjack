package domain.user;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Deck;
import domain.PlayerResults;
import domain.Result;
import domain.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends AbstractUser {
    private static final int UPPER_LIMIT_TO_DRAW = 16;
    private final int INITIAL_CARDS_COUNT = 2;

    private final Deck deck;
    private final PlayerResults playerResults;

    public Dealer() {
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        playerResults = new PlayerResults();
    }

    public Dealer(CardHand cardHand) {
        super(cardHand);
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        playerResults = new PlayerResults();
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }

    public void drawInitCardsDealer() {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cardHand.add(deck.draw());
        }
    }

    public List<Card> drawInitCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cards.add(deck.draw());
        }
        return cards;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void drawCardDealer() {
        cardHand.add(deck.draw());
    }

    public void calculateAllResults(List<Player> users) {
        users.forEach(player -> this.playerResults.save(player, calculatePlayerResult(player)));
    }

    private Result calculatePlayerResult(AbstractUser user) {
        if (user.isBust()) {
            return Result.LOSE;
        }
        if (this.isBust() || user.calculateScore() > this.calculateScore()) {
            return Result.WIN;
        }
        if (user.calculateScore() == this.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public PlayerResults getPlayerResults() {
        return this.playerResults;
    }
}
