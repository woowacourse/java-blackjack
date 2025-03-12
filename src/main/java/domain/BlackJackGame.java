package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Dealer dealer;


    public BlackJackGame(Deck deck, Dealer dealer) {
        validate(deck, dealer);
        this.deck = deck;
        this.dealer = dealer;

    }

    private void validate(Deck deck, Dealer dealer) {
        validateNotNull(deck, dealer);
    }

    private void validateNotNull(Deck deck, Dealer dealer) {
        if (deck == null || dealer == null) {
            throw new IllegalArgumentException("블랙잭게임은 덱과 딜러와 룰을 가지고 있어야합니다.");
        }
    }

    public static BlackJackGame create() {
        Deck deck = initializeDeck();
        Dealer dealer = initializeDealer(deck);

        return new BlackJackGame(deck, dealer);
    }

    private static Deck initializeDeck() {
        Deck deck = Deck.create();
        deck.shuffle();

        return deck;
    }

    private static Dealer initializeDealer(Deck deck) {
        return new Dealer(Hand.of(deck.draw(), deck.draw()));
    }


    public Hand createStartingHands() {
            TrumpCard firstCard = deck.draw();
            TrumpCard secondCard = deck.draw();
        return Hand.of(firstCard, secondCard);
    }

    public void processPlayerHit(Player player) {
        player.addCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (dealer.isHitable()) {
            dealer.addCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    public int calculateScore(Dealer player) {
        return player.getTotalScore();
    }

    public Map<String, GameResult> calculateGameResults(List<Player> players) {
        Map<String, GameResult> results = new HashMap<>();

        players.forEach(player -> {
            GameResult gameResult = GameResult.from(player, dealer);

            results.put(player.getName(), gameResult);
        });

        return results;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
