package blackjack.domain;

import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import blackjack.domain.result.WinningStatus;
import blackjack.domain.user.Users;
import blackjack.domain.result.CardResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Users users;

    public BlackJackGame(final List<String> playerNames, final DeckGenerator deckGenerator) {
        this.deck = new Deck(deckGenerator);
        this.users = new Users(playerNames, deck);
    }

    public Map<String, CardGroup> getStatus() {
        return users.getStatus();
    }

    public Map<String, CardGroup> getFirstOpenCardGroups() {
        return users.getFirstOpenCardGroups();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
    }

    public boolean isPlayerBust(final String name) {
        return users.isPlayerBust(name);
    }

    public int playDealerTurn() {
        int drawCount = 0;
        while (users.isDealerUnderDrawLimit()) {
            users.drawDealer(deck);
            drawCount++;
        }
        return drawCount;
    }

    public void playPlayer(final String userName) {
        users.drawCard(userName, deck);
    }

    public Map<String, CardResult> getCardResult() {
        final Map<String, CardResult> cardResult = new HashMap<>();
        final Map<String, CardGroup> status = users.getStatus();

        for (final String name : status.keySet()) {
            cardResult.put(name, new CardResult(status.get(name), status.get(name).getScore()));
        }

        return cardResult;
    }

    public Map<String, WinningStatus> getWinningResult() {
        return users.getWinningResult();
    }

    public boolean isBlackJackScore(final String name) {
        return users.isBlackJackScore(name);
    }
}
