package blackjack.domain;

import blackjack.response.DealerScoreResponse;
import blackjack.response.FinalResultResponse;
import blackjack.response.InitialCardResponse;
import blackjack.response.PlayerCardsResponse;
import blackjack.response.PlayersCardsResponse;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(final Players players, final DeckFactory deckFactory) {
        participants = new Participants(players, new Dealer());
        deck = deckFactory.generate();
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

    public Player findPlayerByName(final String playerName) {
        return participants.findPlayerByName(playerName);
    }

    public boolean isDealerDrawable() {
        return participants.getDealer().isDrawable();
    }

    public Players getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public void calculateFinalResult() {
        participants.calculateFinalResult();
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

    public FinalResultResponse getFinalResultResponse() {
        return FinalResultResponse.from(participants.getDealer().getResult());
    }
}
