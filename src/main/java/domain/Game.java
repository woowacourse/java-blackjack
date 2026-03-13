package domain;

public class Game {
    public static final int ADDITIONAL_THRESHOLD = 16;
    public static final int BLACKJACK_VALUE = 21;
    public static final int CARD_COUNT = 2;
    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private final Player dealer;
    private final Players players;

    public Game(Deck deck, Players players) {
        this.deck = deck;
        this.players = players;
        this.dealer = new Player(DEALER_NAME);
        dealer.addInitializedCard(deck);
    }

//    private static boolean isPlayerLose(Player player, boolean dealerBurst, int dealerTotal) {
//        return player.isBust() || (!dealerBurst && player.getFinalScore() < dealerTotal);
//    }
//
//    public void updateBettingScore(int money) {
//        betMoney(-money);
//    }
//
//    public boolean needAdditionalCard() {
//        return this.calculateScore() <= ADDITIONAL_THRESHOLD;
//    }

    public Card getDealerFirstCard() {
        return dealer.getCards().getFirst();
    }

    public Players getPlayers() {
        return players;
    }

}
