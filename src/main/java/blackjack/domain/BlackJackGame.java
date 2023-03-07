package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.user.Users;
import blackjack.dto.CardResult;

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

    public Map<String, List<Card>> getStatus() {
        return users.getStatus();
    }

    public Map<String, List<Card>> getInitialStatus() {
        return users.getInitialStatus();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
    }

    public boolean isBust(final String name) {
        return users.getUser(name).isBust();
    }

    public int playDealerTurn() {
        int drawCount = 0;
        while (!users.isDealerOverDrawLimit()) {
            users.drawDealer(deck);
            drawCount++;
        }
        return drawCount;
    }

    public void playPlayer(final String userName) {
        users.getUser(userName).drawCard(deck);
    }

    public Map<String, CardResult> getCardResult() {
        final Map<String, CardResult> cardResult = new HashMap<>();
        final Map<String, List<Card>> status = users.getStatus();

        for (final String name : status.keySet()) {
            cardResult.put(name, new CardResult(status.get(name), users.getUser(name).getScore()));
        }

        return cardResult;
    }

    public Map<String, GameResult> getWinningResult() {
        return users.getWinningResult();
    }

    public boolean isBlackJackScore(final String name) {
        return users.getUser(name).isBlackJack();
    }
}
