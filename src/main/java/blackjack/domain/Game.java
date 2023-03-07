package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.GamePlayer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

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
        initDealerCards();
        initPlayerCards();
    }

    private void initPlayerCards() {
        for (Player player : gamePlayer.getPlayers()) {
            player.addCard(deck.draw());
            player.addCard(deck.draw());
        }
    }

    private void initDealerCards() {
        giveCardToDealer();
        giveCardToDealer();
    }

    public boolean isHitDealer() {
        return gamePlayer.isHitDealer();
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

    public List<Card> showDealerCards() {
        return getDealer().showCards();
    }

    public List<Card> showDealerAllCards() {
        return getDealer().showAllCards();
    }

    public Score getDealerScore() {
        return getDealer().calculateScore();
    }
}
