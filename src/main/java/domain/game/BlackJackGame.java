package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final String DEALER_NAME = "딜러";
    private static final int BLACK_JACK_NUMBER = 21;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
        distributeTwoCards();
    }

    private void distributeTwoCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());

        for (Player player : players.getPlayers()) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public void drawCard(final String playerName) {
        if (isDealer(playerName)) {
            dealer.drawCard(deck.popCard());
        }
        players.getPlayer(playerName)
                .drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public Map<Name, Outcome> decidePlayersOutcome() {
        Map<Name, Outcome> result = new LinkedHashMap<>();

        players.getPlayers()
                .forEach(player ->
                        result.put(player.getName(), decideOutcome(dealer.getScore(), player.getScore()))
                );

        return result;
    }

    private Outcome decideOutcome(final int dealerScore, final int playerScore) {
        if (isPlayerWin(dealerScore, playerScore)) {
            return Outcome.WIN;
        }
        if (isPlayerDraw(dealerScore, playerScore)) {
            return Outcome.DRAW;
        }
        return Outcome.LOSE;
    }

    private boolean isPlayerWin(final int dealerScore, final int playerScore) {
        if (isBurst(dealerScore) && !isBurst(playerScore)) {
            return true;
        }
        return !isBurst(playerScore) && playerScore > dealerScore;
    }

    private boolean isPlayerDraw(final int dealerScore, final int playerScore) {
        if (isBurst(dealerScore) && isBurst(playerScore)) {
            return true;
        }
        return dealerScore == playerScore;
    }

    private boolean isBurst(int score) {
        return score > BLACK_JACK_NUMBER;
    }

    public List<Card> getCards(final String playerName) {
        if (isDealer(playerName)) {
            return dealer.getCards();
        }
        return players.getPlayer(playerName).getCards();
    }

    private boolean isDealer(final String name) {
        return name.equals(DEALER_NAME);
    }

    public int getScore(final String playerName) {
        if (isDealer(playerName)) {
            return dealer.getScore();
        }
        return players.getPlayer(playerName).getScore();
    }

    public EnumMap<Outcome, Integer> calculateDealerResult() {
        Map<Name, Outcome> result = decidePlayersOutcome();
        EnumMap<Outcome, Integer> dealerResult = initializeDealerResult();
        for (Name key : result.keySet()) {
            final Outcome outcome = result.get(key);
            dealerResult.put(outcome, dealerResult.get(outcome) + 1);
        }
        return dealerResult;
    }

    private static EnumMap<Outcome, Integer> initializeDealerResult() {
        EnumMap<Outcome, Integer> enumMap = new EnumMap<>(Outcome.class);

        for (Outcome outcome : Outcome.values()) {
            enumMap.put(outcome, 0);
        }

        return enumMap;
    }
}
