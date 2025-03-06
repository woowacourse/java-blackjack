package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;
    private final Rule rule;

    public BlackJackGame(Deck deck, Dealer dealer, Rule rule) {
        validate(deck, dealer, rule);
        this.deck = deck;
        this.dealer = dealer;
        this.rule = rule;
    }

    private void validate(Deck deck, Dealer dealer, Rule rule) {
        validateNotNull(deck, dealer, rule);
    }

    private void validateNotNull(Deck deck, Dealer dealer, Rule rule) {
        if (deck == null || dealer == null || rule == null) {
            throw new IllegalStateException("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
        }
    }

    public static BlackJackGame create() {
        Deck deck = initializeDeck();
        Dealer dealer = initializeDealer(deck);
        Rule rule = initializeRule();
        return new BlackJackGame(deck, dealer, rule);
    }

    private static Deck initializeDeck() {
        Deck deck = Deck.create();
        deck.shuffle();

        return deck;
    }

    private static Dealer initializeDealer(Deck deck) {
        return new Dealer(Hand.of(deck.draw(), deck.draw()));
    }

    private static Rule initializeRule() {
        return new Rule();
    }

    public List<Player> createPlayers(List<String> names) {
        List<Player> players = new ArrayList<>();

        names.forEach(name -> {
            TrumpCard firstCard = deck.draw();
            TrumpCard secondCard = deck.draw();
            players.add(new Player(name, Hand.of(firstCard, secondCard)));
        });

        return players;
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public boolean isPlayerHitAllowed(List<TrumpCard> playerCards) {
        return rule.isPlayerHitAllowed(playerCards);
    }

    public void processPlayerHit(Player player) {
        Hand hand = player.getHand();
        List<TrumpCard> cards = hand.getCards();

        if (!isPlayerHitAllowed(cards)) {
            throw new IllegalStateException("플레이어는 더이상 히트할 수 없습니다.");
        }

        player.receiveCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;
        Hand hand = dealer.getHand();

        while (rule.isDealerHitAllowed(hand.getCards())) {
            dealer.receiveCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    public Map<String, GameResult> calculateGameResults(List<Player> players) {
        Map<String, GameResult> results = new HashMap<>();

        players.forEach(player -> {
            List<TrumpCard> playerCards = player.getHand().getCards();
            List<TrumpCard> dealerCards = dealer.getHand().getCards();

            GameResult gameResult = rule.evaluateGameResult(playerCards, dealerCards);

            results.put(player.getName(), gameResult);
        });

        return results;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
