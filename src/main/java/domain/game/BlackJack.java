package domain.game;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Players;

import java.util.List;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private BlackJack(List<Name> playerNames, Deck deck) {
        this.dealer = Dealer.create();
        this.players = Players.create(playerNames);
        this.deck = deck;
        initGame(deck, INITIAL_DRAW_CARD_COUNT);
    }

    private void initGame(final Deck deck, final int count) {
        dealer.takeCard(deck, count);
        players.takeCard(deck, count);
    }

    public static BlackJack getInstance(List<Name> playerNames, Deck deck) {
        return new BlackJack(playerNames, deck);
    }

    public boolean isBusted(Participant participant) {
        return participant.isBusted();
    }

    public void drawCard(final Participant player) {
        player.takeCard(deck.drawCard());
    }

    public int getAdditionalCardCount() {
        int count = 0;
        while (dealer.needMoreCard()) {
            dealer.takeCard(deck.drawCard());
            count += 1;
        }
        return count;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public GameResult getGameResult() {
        return GameResult.of(dealer, players);
    }
}
