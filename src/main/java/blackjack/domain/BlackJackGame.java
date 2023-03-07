package blackjack.domain;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, String dealerName, List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(dealerName, playerNames);
    }

    public void handOut() {
        participants.handOut(deck);
    }

    public void handOneCard(String playerName) {
        participants.handCardsByPlayerName(playerName, deck.draw(1));
    }

    public Map<String, List<Card>> openHandOutCards() {
        return participants.openHandOutCardsByName();
    }

    public List<String> findAvailablePlayerNames() {
        return participants.findAvailablePlayerNames();
    }

    public boolean isAvailablePlayer(String name) {
        return participants.isAvailablePlayer(name);
    }

    public List<Card> openCardsByName(String participantName) {
        return participants.findHandCardsByName(participantName);
    }

    public int hitOrStayForDealer() {
        return participants.repeatHandToDealerUntilAvailable(deck);
    }

    public Map<String, FinalCards> openAllFinalCards() {
        return participants.openFinalCardsByName();
    }

    public PlayerJudgeResults computeJudgeResultsByPlayer() {
        return participants.computeJudgeResultsByPlayer();
    }
}
