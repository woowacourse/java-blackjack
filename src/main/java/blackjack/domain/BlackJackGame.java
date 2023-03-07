package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.result.FinalCards;
import blackjack.domain.result.PlayerJudgeResults;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int INITIAL_HAND_OUT_COUNT = 2;
    private static final int HIT_CARD_COUNT = 1;

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(final DeckGenerator deckGenerator, final String dealerName, final List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(dealerName, playerNames);
    }

    public void handOut() {
        deck.handCardsTo(participants.dealer(), INITIAL_HAND_OUT_COUNT);
        deck.handCardsTo(participants.players(), INITIAL_HAND_OUT_COUNT);
    }

    public void hitByName(final String participantName) {
        final Participant participant = participants.findParticipantByName(participantName);
        hit(participant);
    }

    private void hit(final Participant participant) {
        deck.handCardsTo(participant, HIT_CARD_COUNT);
    }

    public int hitOrStayForDealer() {
        int hitCount = 0;
        final Dealer dealer = participants.dealer();
        while (dealer.isAvailable()) {
            hit(dealer);
            hitCount++;
        }
        return hitCount;
    }

    public Map<String, List<Card>> openHandOutCards() {
        final Dealer dealer = participants.dealer();
        final Map<String, List<Card>> cardsByParticipants = new LinkedHashMap<>();
        cardsByParticipants.put(dealer.getName(), List.of(dealer.openFirstCard()));
        participants.players()
                .forEach(player -> cardsByParticipants.put(player.getName(), player.getCards()));
        return cardsByParticipants;
    }

    public List<Card> openCardsByName(final String participantName) {
        final Participant participant = participants.findParticipantByName(participantName);
        return participant.getCards();
    }

    public boolean isAvailable(final String participantName) {
        final Participant participant = participants.findParticipantByName(participantName);
        return participant.isAvailable();
    }

    public Map<String, FinalCards> openAllFinalCards() {
        final Map<String, FinalCards> finalCardsByPlayerName = new LinkedHashMap<>();
        final Dealer dealer = participants.dealer();
        finalCardsByPlayerName.put(dealer.getName(), FinalCards.of(dealer));
        participants.players()
                .forEach(player -> finalCardsByPlayerName.put(player.getName(), FinalCards.of(player)));
        return finalCardsByPlayerName;
    }

    public PlayerJudgeResults computeJudgeResultsByPlayer() {
        return new PlayerJudgeResults(participants.collectPlayerJudgeResults());
    }
}
