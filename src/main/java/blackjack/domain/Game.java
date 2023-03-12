package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

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
        List<Card> cards = new ArrayList<>();
        int playersCount = getPlayersCount();
        for (int i = 0; i < playersCount + 1; i++) {
            cards.add(deck.draw());
            cards.add(deck.draw());
        }
        gamePlayer.initializeGamePlayer(cards);
    }

    public int getDealerScore() {
        return gamePlayer.getDealer().calculateScore();
    }

    public List<Integer> getPlayersScore() {
        return gamePlayer.getPlayers().getPlayersScore();
    }

    public void dealerHit() {
        gamePlayer.dealerHit(deck.draw());
    }

    public void playerHit(int i) {
        gamePlayer.playerHit(i, deck.draw());
    }

    public boolean isDealerHitPossible() {
        return gamePlayer.getDealer().isHitPossible();
    }

    public boolean isPlayerHitPossibleByIndex(int i) {
        return gamePlayer.getPlayers().getPlayer(i).isHitPossible();
    }

    public String getPlayerNameByIndex(int i) {
        return gamePlayer.getPlayers().getPlayer(i).getName();
    }

    public List<Card> getPlayerCardsByIndex(int i) {
        return gamePlayer.getPlayers().getPlayer(i).getAllCards();
    }

    public Card getDealerFirstCard() {
        return gamePlayer.getDealer().getFirstCard();
    }

    public List<Card> getDealerAllCards() {
        return gamePlayer.getDealer().getAllCards();
    }

    public int getPlayersCount() {
        return gamePlayer.getPlayers().getCount();
    }
}
