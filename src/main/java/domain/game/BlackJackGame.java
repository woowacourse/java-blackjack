package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Deck deck;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new Players(playerNames);
        players.drawTwoCardsAtFirstTime(deck);
    }

    public void drawCard(final String playerName) {
        players.findPlayer(playerName).drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return findDealer().isDealerDraw();
    }

    private Dealer findDealer() {
        return players.findDealer();
    }

    public EnumMap<Outcome, Integer> decideDealerOutcome() {
        Map<String, Outcome> playerOutcome = decidePlayersOutcome();
        EnumMap<Outcome, Integer> dealerOutcome = initializeDealerOutcome();
        for (String key : playerOutcome.keySet()) {
            final Outcome outcome = playerOutcome.get(key);
            dealerOutcome.put(reverseOutcome(outcome), dealerOutcome.get(outcome) + 1);
        }
        return dealerOutcome;
    }

    private static EnumMap<Outcome, Integer> initializeDealerOutcome() {
        EnumMap<Outcome, Integer> dealerOutcome = new EnumMap<>(Outcome.class);

        for (Outcome outcome : Outcome.values()) {
            dealerOutcome.put(outcome, 0);
        }

        return dealerOutcome;
    }

    public Map<String, Outcome> decidePlayersOutcome() {
        Map<String, Outcome> playerOutcome = new LinkedHashMap<>();
        final int dealerScore = findDealer().getScore();
        final List<Player> players = this.players.getPlayersWithOutDealer();

        players.forEach((player ->
                playerOutcome.put(player.getName(), decideOutcome(dealerScore, player))
        ));

        return playerOutcome;
    }

    private Outcome reverseOutcome(Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        if(outcome == Outcome.LOSE) {
            return Outcome.WIN;
        }
        return Outcome.DRAW;
    }

    private Outcome decideOutcome(final int dealerScore, final Player player) {
        if (player.isWin(dealerScore)) {
            return Outcome.WIN;
        }
        if (player.isDraw(dealerScore)) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    public List<Card> getCards(final String playerName) {
        return players.findPlayer(playerName).getCards();
    }

    public int getScore(final String playerName) {
        return players.findPlayer(playerName).getScore();
    }
}
