package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BlackjackEarningCalculator;
import java.util.List;

public class Dealer extends BlackjackGamer {

    private Deck deck;

    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super();
        initializeShuffledDeck();
    }

    public Dealer(List<Card> cards) {
        super(cards);
        initializeShuffledDeck();
    }

    @Override
    public boolean canReceiveCard() {
        return !isBlackjack()
                && getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
    }

    @Override
    public String getName() {
        return DEFAULT_DEALER_NAME;
    }

    private void initializeShuffledDeck() {
        this.deck = new Deck();
        deck.shuffle();
    }

    private Card drawCardFromDeck() {
        try {
            return deck.draw();
        } catch (IllegalStateException e) {
            initializeShuffledDeck();
            return drawCardFromDeck();
        }
    }

    public void setUpInitialCards(List<Player> players) {
        // 모든 플레이어에게 2장의 카드를 배분한다.
        players.forEach(this::setUpPlayerInitialCards);
        // 딜러도 2장의 카드를 가진다.
        setUpOwnInitialCards();
    }

    private void setUpPlayerInitialCards(Player player) {
        giveCardToPlayer(player);
        giveCardToPlayer(player);
    }

    public void giveCardToPlayer(Player player) {
        player.addCard(drawCardFromDeck());
    }

    private void setUpOwnInitialCards() {
        addCard();
        addCard();
    }

    public void addCard() {
        addCard(drawCardFromDeck());
    }

    public Money calculateOwnEarning(PlayerBetAmounts playerBetAmounts) {
        BlackjackEarningCalculator calculator = BlackjackEarningCalculator.fromDealer(this);
        return calculator.calculateDealerEarning(playerBetAmounts);
    }

    public Money informPlayerEarning(Player player, Money betAmount) {
        BlackjackEarningCalculator calculator = BlackjackEarningCalculator.fromDealer(this);

        return calculator.calculatePlayerEarning(player, betAmount);
    }

    public Card getFirstCard() {
        List<Card> cards = hand.cards();
        return cards.get(0);
    }
}
