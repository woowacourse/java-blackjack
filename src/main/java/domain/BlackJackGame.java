package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    public static final int INITIAL_CARD_COUNT = 2;

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
            throw new IllegalArgumentException("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
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
        return new Dealer(new Hand(deck.drawMultiple(INITIAL_CARD_COUNT)));
    }

    private static Rule initializeRule() {
        return new Rule();
    }

    public List<Player> createPlayers(List<String> names) {
        validateNotDuplicate(names);
        List<Player> players = new ArrayList<>();

        names.forEach(name -> {
            players.add(new Player(name, new Hand(deck.drawMultiple(INITIAL_CARD_COUNT))));
        });

        return players;
    }

    private void validateNotDuplicate(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    public TrumpCard retrieveDealerFirstCard() {
        List<TrumpCard> cards = dealer.retrieveCards();

        if (cards.size() != INITIAL_CARD_COUNT) {
            throw new IllegalStateException("딜러는 " + INITIAL_CARD_COUNT + "장의 카드를 가지고 있어야 합니다.");
        }

        return cards.getFirst();
    }

    public boolean isPlayerHitAllowed(List<TrumpCard> playerCards) {
        return rule.isPlayerHitAllowed(playerCards);
    }

    public void processPlayerHit(Player player) {
        List<TrumpCard> cards = player.retrieveCards();

        if (!isPlayerHitAllowed(cards)) {
            throw new IllegalStateException("플레이어는 더이상 히트할 수 없습니다.");
        }

        player.receiveCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (rule.isDealerHitAllowed(dealer.retrieveCards())) {
            dealer.receiveCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    public List<TrumpCard> retrieveDealerCards() {
        return dealer.retrieveCards();
    }

    public Score caculateScore(List<TrumpCard> cards) {
        return rule.evaluateScore(cards);
    }

    public Map<String, GameResult> calculateGameResults(List<Player> players) {
        Map<String, GameResult> results = new HashMap<>();

        players.forEach(player -> {
            List<TrumpCard> playerCards = player.retrieveCards();
            List<TrumpCard> dealerCards = dealer.retrieveCards();

            GameResult gameResult = rule.evaluateGameResult(playerCards, dealerCards);

            results.put(player.getName(), gameResult);
        });

        return results;
    }
}
