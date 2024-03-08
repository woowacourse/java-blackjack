package blackjack.domain;

public class Game {
    //TODO 멤버 줄이기
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    private Game(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public static Game of(Deck deck, Dealer dealer, Players players) {
        Game game = new Game(deck, dealer, players);
        game.handOutInitialCards();
        return game;
    }

    private void handOutInitialCards() {
        players.handOutInitialCards(deck);
        dealer.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public GameResult makeGameResult(){
        return GameResult.of(dealer, players);
    }
}
