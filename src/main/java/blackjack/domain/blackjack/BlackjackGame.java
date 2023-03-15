package blackjack.domain.blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.dto.CardResponse;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Participants participants;
    private final Deck deck;

    private BlackjackGame(final Participants participants, final Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackjackGame of(final List<String> playerNames, final List<Integer> bettingMoneys, final Deck deck) {
        return new BlackjackGame(Participants.of(playerNames, bettingMoneys), deck);
    }

    public void distributeInitialCards() {
        participants.distributeInitialCards(deck);
    }

    public CardResponse getDealerFirstCard() {
        return participants.getDealerFirstCard();
    }

    public Map<String, List<CardResponse>> getPlayersCards() {
        return participants.getPlayersCards();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public void drawDealerCard() {
        participants.drawDealerCard(deck.popCard());
    }

    public boolean isPlayerDrawable(final String playerName) {
        return participants.isPlayerDrawable(playerName);
    }

    public List<CardResponse> getPlayerCards(final String playerName) {
        return participants.getPlayerCards(playerName);
    }

    public void drawPlayerCard(final String playerName) {
        participants.drawPlayerCard(playerName, deck.popCard());
    }

    public int getDealerScore() {
        return participants.getDealerScore();
    }

    public List<CardResponse> getDealerCards() {
        return participants.getDealerCards();
    }

    public Map<String, Integer> getPlayersScores() {
        return participants.getPlayersScores();
    }

    public Map<String, Integer> calculateMoney() {
        final Map<String, Integer> playerMoney = new LinkedHashMap<>();
        final BlackJackRule blackJackRule = new BlackJackRule();
        for (final Player player : participants.getPlayers()) {
            final int resultMoney = blackJackRule.calculatePlayerProfit(player, participants.getDealer());
            playerMoney.put(player.getName(), resultMoney);
        }
        return playerMoney;
    }
}
