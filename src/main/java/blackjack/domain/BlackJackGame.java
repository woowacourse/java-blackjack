package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import blackjack.response.DealerScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;
import java.util.List;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    private BlackJackGame(final Players players, final DeckFactory deckFactory) {
        participants = new Participants(players, new Dealer());
        deck = deckFactory.generate();
    }

    public static BlackJackGame of(final List<String> playerNames, final DeckFactory deckFactory) {
        return new BlackJackGame(Players.from(playerNames), deckFactory);
    }

    public void distributeInitialCard() {
        participants.distributeInitialCards(deck);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return participants.isPlayerDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName) {
        participants.drawPlayerCard(playerName, deck.removeCard());
    }

    public void drawDealerCard() {
        participants.drawDealerCard(deck.removeCard());
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public List<String> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public InitialCardResponse getInitialCardResponse() {
        return InitialCardResponse.of(participants.getPlayers(), participants.getDealer());
    }

    public PlayerCardsResponse getPlayerCardsResponse(final String playerName) {
        return PlayerCardsResponse.of(participants.findPlayerByName(playerName));
    }

    public DealerScoreResponse getDealerScoreResponse() {
        return DealerScoreResponse.from(participants.getDealer());
    }

    public PlayersCardsResponse getPlayersCardsResponse() {
        return PlayersCardsResponse.from(participants.getPlayers());
    }

    public FinalResultResponse createFinalResultResponse() {
        return FinalResultResponse.from(participants.calculateFinalResult());
    }
}
