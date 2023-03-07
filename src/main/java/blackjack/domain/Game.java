package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.GamePlayer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Deck deck;
    private final GamePlayer gamePlayer;

    public Game(Deck deck, GamePlayer gamePlayer) {
        this.deck = deck;
        this.gamePlayer = gamePlayer;
        init();
    }

    private void init() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < gamePlayer.getPlayers().count() + 1; i++) {
            cards.add(deck.draw());
            cards.add(deck.draw());
        }
        gamePlayer.init(cards);
    }

    public boolean isHitPlayerByIndex(int i) {
        return gamePlayer.isHitPlayerByIndex(i);
    }

    public boolean isHitDealer() {
        return gamePlayer.isHitDealer();
    }

    public void giveCardTo(int i) {
        gamePlayer.giveCardToPlayerByIndex(i, deck.draw());
    }

    public void giveCardTo(Player player) {
        gamePlayer.giveCardTo(player, deck.draw());
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
        return getDealer().showCards();
    }

    public List<Card> showDealerAllCards() {
        return getDealer().showAllCards();
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
