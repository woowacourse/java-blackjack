package domain;

import java.util.List;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void initialDealing(Deck deck) {
        dealer.pickCards(deck, INITIAL_CARD_COUNT);
        players.pickCardsToPlayer(deck, INITIAL_CARD_COUNT);
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getCardsFromName(Name name) {
        return players.getCardsFromName(name);
    }

    public void hitFromName(Name name, Deck deck) {
        players.hitFromName(name, deck);
    }

    public boolean isBustFromName(Name name) {
        return players.isBustFromName(name);
    }

    public int hitDealer(Deck deck) {
        int hitCount = 0;
        while (dealer.canHit()) {
            dealer.hit(deck);
            hitCount++;
        }
        return hitCount;
    }

    public BlackJackResult getGameResult() {
        return new BlackJackResult(dealer, players);
    }

    public int getDealerScore() {
        return dealer.getTotalScore();
    }

    public int getScoreFromName(Name name) {
        return players.getTotalScore(name);
    }
}
