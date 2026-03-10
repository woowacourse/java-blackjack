package domain;

import java.util.List;

public class Game {
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public Game(List<String> playerNames, Deck deck){
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = playerNames.stream()
                .map(Player::new)
                .toList();
        initCards();
    }

    private void initCards(){
        players.forEach(player -> {
            player.draw(deck.drawCard());
            player.draw(deck.drawCard());
        });
        dealer.draw(deck.drawCard());
        dealer.draw(deck.drawCard());
    }

    public void hitPlayer(Player player) {
        player.draw(deck.drawCard());
    }

    public void hitDealer() {
        dealer.draw(deck.drawCard());
    }

    public boolean dealerShouldHit() {
        return dealer.shouldHit();
    }

    public int getPlayerHandSize(Player player){
        return player.getHandSize();
    }

    public int getDealerHandSize(){
        return dealer.getHandSize();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
