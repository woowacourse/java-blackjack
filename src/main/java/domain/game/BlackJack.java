package domain.game;

import domain.deck.DeckStrategy;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Players;

import java.util.List;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final DeckStrategy deck;

    private BlackJack(final List<Name> playerNames, final List<Integer> bets, final DeckStrategy deck) {
        this.dealer = Dealer.create(bets);
        this.players = Players.create(playerNames, bets);
        this.deck = deck;
        initGame(deck, INITIAL_DRAW_CARD_COUNT);
    }

    private void initGame(final DeckStrategy deck, final int count) {
        dealer.takeCard(deck, count);
        players.takeCard(deck, count);
    }

    public static BlackJack getInstance(final List<Name> playerNames,
                                        final List<Integer> bets,
                                        final DeckStrategy deck) {
        return new BlackJack(playerNames, bets, deck);
    }

    public boolean isBusted(final Participant participant) {
        return participant.isBusted();
    }

    public void drawCard(final Participant player) {
        player.takeCard(deck.drawCard());
        if (isBusted(player)) {
            player.applyBust();
        }
    }

    public int getAdditionalCardCount() {
        int count = 0;
        while (dealer.needMoreCard()) {
            dealer.takeCard(deck.drawCard());
            count += 1;
        }
        return count;
    }

    public void calculateBets() {

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
