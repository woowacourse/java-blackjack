package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CompeteResult;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dealer extends Participant {
    
    private static final int DEALER_THRESHOLD = 16;
    private static final String NAME = "딜러";
    private static final String ERROR_CANNOT_RECEIVE = "최고 합이거나 버스트되어 카드를 받을 수 없습니다.";
    
    private final Deck deck;
    
    private Dealer(String name, CardHand cardHand, Deck deck) {
        super(name, cardHand);
        this.deck = deck;
    }
    
    public static Dealer create() {
        CardHand cardHand = new CardHand(new ArrayList<>());
        
        return new Dealer(NAME, cardHand, Deck.createShuffledDeck());
    }
    
    public void deal(Participant participant) {
        if (!participant.canReceive()) {
            throw new IllegalArgumentException(ERROR_CANNOT_RECEIVE);
        }
        
        final Card card = deck.drawCard();
        participant.receive(card);
    }
    
    public boolean shouldReceive() {
        return sumCardHand() <= DEALER_THRESHOLD;
    }
    
    public Card getCard(int cardIndex) {
        return getCards().get(cardIndex);
    }
    
    public EnumMap<CompeteResult, Long> competeResultMap(List<Player> players) {
        return players.stream()
                      .map(this::compete)
                      .collect(Collectors.groupingBy(Function.identity(), () -> new EnumMap<>(CompeteResult.class),
                              Collectors.counting()));
    }
    
    private CompeteResult compete(Player player) {
        if (isDealerWin(player)) {
            return CompeteResult.WIN;
        }
        
        if (isDealerDefeat(player)) {
            return CompeteResult.DEFEAT;
        }
        
        return CompeteResult.DRAW;
    }
    
    private boolean isDealerWin(Player player) {
        return (sumCardHand() > player.sumCardHand()) || player.isBurst();
    }
    
    private boolean isDealerDefeat(Player player) {
        return (sumCardHand() < player.sumCardHand()) && !player.isBurst();
    }
}
