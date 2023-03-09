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
        for (int i = 0; i < gamePlayer.getPlayers().count() + 1; i++) {
            cards.add(deck.draw());
            cards.add(deck.draw());
        }
        gamePlayer.initializeGamePlayer(cards);
    }

    public boolean isHitPlayerByIndex(int i) {
        return gamePlayer.isHitPlayerByIndex(i);
    }

    public boolean isHitDealer() {
        return gamePlayer.isHitDealer();
    }

    public void giveCardToPlayerByIndex(int i) {
        gamePlayer.giveCardToPlayerByIndex(i, deck.draw());
    }

    public void giveCardToDealer() {
        gamePlayer.giveCardToDealer(deck.draw());
    }

    public Players getPlayers() {
        return gamePlayer.getPlayers();
    }

    public Dealer getDealer() {
        return gamePlayer.getDealer();
    }

    public List<String> showPlayersName() {
        return getPlayers().getPlayersName();
    }

    public String showPlayerNameByIndex(int i) {
        return getPlayers().showPlayerNameByIndex(i);
    }

    public List<Card> showPlayerCardsByIndex(int i) {
        return getPlayers().showPlayerCardsByIndex(i);
    }

    public List<Card> showDealerCards() {
        return getDealer().getFirstCard();
    }

    public List<Card> showDealerAllCards() {
        return getDealer().getAllCards();
    }

    public int getPlayersCount() {
        return getPlayers().count();
    }

    public int getDealerScore() {
        return getDealer().calculateScore();
    }

    public int getPlayerScoreByIndex(int i) {
        return getPlayers().getPlayerScoreByIndex(i);
    }
}
