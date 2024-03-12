package domain;

import java.util.List;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
    }

    public void initialDealing(Deck deck) {
        dealer.pickTwoCards(deck);
        players.pickTwoCards(deck);
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
        return dealer.hit(deck);
    }

    public BlackJackResult getGameResult() {
        return new BlackJackResult(
                dealer.getTotalScore(),
                players.getPlayersTotalScores());
    }

    public int getDealerScore() {
        return dealer.getTotalScore();
    }

    public int getScoreFromName(Name name) {
        return players.getTotalScore(name);
    }
}
