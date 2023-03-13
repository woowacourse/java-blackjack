package domain.user;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Deck;
import domain.PlayerRevenues;
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
    private final PlayerRevenues playerRevenues;

    public Dealer() {
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        playerRevenues = new PlayerRevenues();
    }

    public Dealer(CardHand cardHand) {
        super(cardHand);
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
        playerRevenues = new PlayerRevenues();
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
        users.forEach(player -> this.playerRevenues.save(player, calculatePlayerRevenue(player)));
    }

    private Result calculatePlayerResult(Player player) {
        if (this.isBust() || player.isBust()) {
            throw new IllegalStateException("딜러 또는 플레이어가 bust 상태는 잘못된 상태입니다.");
        }
        if (player.calculateScore() > this.calculateScore()) {
            return Result.WIN;
        }
        if (player.calculateScore() == this.calculateScore()) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    private Integer calculatePlayerRevenue(Player player) {
        if (player.isBust()) {
            return -player.getBettingAmount().getBettingAmount();
        }
        if (player.isBlackjack()) {
            return calculateIfBlackjackPlayer(player);
        }
        if (this.isBust()) {
            return player.getBettingAmount().getBettingAmount();
        }
        return calculateMatchDealerAndPlayer(player);
    }

    private Integer calculateIfBlackjackPlayer(Player player) {
        if (this.isBlackjack()) {
            return 0;
        }
        return (int) ((double) player.getBettingAmount().getBettingAmount() * 1.5);
    }

    private Integer calculateMatchDealerAndPlayer(Player player) {
        Result result = calculatePlayerResult(player);
        if (result == Result.WIN) {
            return player.getBettingAmount().getBettingAmount();
        }
        if (result == Result.DRAW) {
            return 0;
        }
        return -player.getBettingAmount().getBettingAmount();
    }

    public PlayerRevenues getPlayerRevenues() {
        return this.playerRevenues;
    }
}
