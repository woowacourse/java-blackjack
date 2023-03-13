package domain.game;

import domain.deck.DeckStrategy;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.FinalResult;

import java.util.List;

public final class BlackJack {

    public static final int INITIAL_DRAW_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final DeckStrategy deck;

    private BlackJack(final List<Name> playerNames, final List<Integer> bets, final DeckStrategy deck) {
        this.dealer = Dealer.create();
        this.players = Players.create(playerNames, bets);
        this.deck = deck;
        initGame(deck, INITIAL_DRAW_CARD_COUNT);
    }

    private void initGame(final DeckStrategy deck, final int count) {
        dealer.takeInitialCards(deck, count);
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

    public void giveCard(final Player player) {
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

    public FinalResult getGameResult() {
        return FinalResult.of(dealer, players);
    }
}
