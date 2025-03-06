package domain;

import java.util.ArrayList;
import java.util.List;

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
            throw new IllegalStateException("블랙잭게임은 덱과 딜러를 가지고 있어야합니다.");
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

    public boolean isPlayerHitAllowed(Player player) {
        Hand hand = player.getHand();
        List<TrumpCard> cards = hand.getCards();
        Score score = Score.from(cards);

        return score != Score.BUST && score != Score.BLACKJACK;
    }

    public void processPlayerHit(Player player) {
        player.receiveCard(deck.draw());
    }

    public int processDealerHit() {
        int hitCount = 0;

        while (isDealerHitAllowed()) {
            dealer.receiveCard(deck.draw());
            hitCount++;
        }

        return hitCount;
    }

    private boolean isDealerHitAllowed() {
        Hand hand = dealer.getHand();
        Score score = Score.from(hand.getCards());

        return score != Score.BUST
                && score != Score.BLACKJACK
                && score != Score.SEVENTEEN
                && score != Score.EIGHTEEN
                && score != Score.NINETEEN
                && score != Score.TWENTY
                && score != Score.TWENTY_ONE;
    }
}
