package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.gameplayer.Dealer;
import blackjack.domain.gameplayer.Player;
import blackjack.domain.gameplayer.Players;

import java.util.List;

public class Game {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public Game(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
        init();
    }

    private void init() {
        initDealerCards();
        initPlayerCards();
    }

    private void initPlayerCards() {
        for (Player player : players) {
            giveCardTo(player);
            giveCardTo(player);
        }
    }

    private void initDealerCards() {
        giveCardToDealer();
        giveCardToDealer();
    }

    public boolean isHitDealer() { // 메서드명 변경 필요
        return dealer.canContinue();
    }

    public void giveCardTo(Player player) {
        player.addCard(deck.draw());
    }

    public void giveCardToDealer() {
        dealer.addCard(deck.draw());
    }

    public Players getPlayers() {
        return players;
    }

    public List<String> showPlayersName() {
        return players.getPlayersName();
    }

    public List<Card> showDealerCards() {
        return dealer.showCards();
    }

    public List<Card> showDealerAllCards() {
        return dealer.showAllCards();
    }

    public Score getDealerScore() {
        return dealer.calculateScore();
    }
}
