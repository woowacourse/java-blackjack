package domain.user;

import domain.BettingResult;
import domain.BlackjackResult;
import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.DealerResult;
import domain.Deck;
import domain.PlayerResult;
import domain.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends AbstractUser {
    private static final int UPPER_LIMIT_TO_DRAW = 16;
    private final int INITIAL_CARDS_COUNT = 2;

    private final Deck deck;

    public Dealer() {
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
    }

    public Dealer(CardHand cardHand) {
        super(cardHand);
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
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

    public boolean drawCardDealer() {
        if (this.canAdd()) {
            cardHand.add(deck.draw());
            return true;
        }
        return false;
    }

    public BlackjackResult calculateAllResults(List<Player> users) {
        List<PlayerResult> playerResults = calculatePlayerResults(users);
        DealerResult dealerResult = getDealerResult(playerResults);
        return new BlackjackResult(playerResults, dealerResult);
    }

    private DealerResult getDealerResult(final List<PlayerResult> playerResults) {
        return new DealerResult(
                playerResults.stream().map(result -> new BettingResult(-result.getBettingResult().getResult()))
                        .reduce(new BettingResult(0), (bettingResult, bettingResult2) -> new BettingResult(
                                bettingResult.getResult() + bettingResult2.getResult())));
    }

    private List<PlayerResult> calculatePlayerResults(final List<Player> users) {
        return users.stream()
                .map(user -> new PlayerResult(user.getName(), new BettingResult(calculatePlayerRevenue(user))))
                .collect(Collectors.toList());
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
        if (player.calculateScore() > this.calculateScore()) {
            return player.getBettingAmount().getBettingAmount();
        }
        if (player.calculateScore() == this.calculateScore()) {
            return 0;
        }
        return -player.getBettingAmount().getBettingAmount();
    }

    public Card getFirstCard() {
        return cardHand.getCards().get(0);
    }
}
