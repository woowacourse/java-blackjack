package blackjack.domain;

public class Game {
    private final Deck deck;
    private final GamePlayer gamePlayer;

    private Game(Deck deck, GamePlayer gamePlayer) {
        this.deck = deck;
        this.gamePlayer = gamePlayer;
        initializeGame();
    }

    public static Game from(GamePlayer gamePlayer) {
        return new Game(Deck.of(new ShuffleCardsGenerator().generator()), gamePlayer);
    }

    private void initializeGame() {
        Players players = getPlayers();
        Dealer dealer = getDealer();
        for (int i = 0; i < 2; i++) {
            Hit(dealer);
        }
        for (Player player : players.getPlayers()) {
            Hit(player);
        }
    }

    public void Hit(Person person) {
        person.addCard(deck.draw());
    }

    public Dealer getDealer() {
        return gamePlayer.getDealer();
    }

    public Players getPlayers() {
        return gamePlayer.getPlayers();
    }
}
