package domain;

import domain.card.CardDeck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BlackjackResult;
import dto.ResultDto;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    private static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;

    private final CardDeck cardDeck;
    private final Participants participants;

    private GameManager(CardDeck cardDeck, Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static GameManager of(CardDeck cardDeck, Participants participants) {
        return new GameManager(cardDeck, participants);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            participants.receiveCards(cardDeck);
        }
    }

    public void passCardToPlayer(String name) {
        Player player = participants.findByName(name);
        player.receive(cardDeck.popCard());
    }

    public int getScoreOf(String name) {
        Player player = participants.findByName(name);
        return player.getScore();
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(participants.getPlayersName());
    }

    public boolean passCardToDealer() {
        Participant dealer = participants.getDealer();
        if (dealer.getScore() > DEALER_MIN_SCORE) {
            return false;
        }
        dealer.receive(cardDeck.popCard());
        return true;
    }

    public ResultDto calculateResult() {
        Participant dealer = participants.getDealer();
        List<Participant> players = participants.getPlayers();

        EnumMap<BlackjackResult, Integer> dealerResults = new EnumMap<>(BlackjackResult.class);
        for (BlackjackResult result : BlackjackResult.values()) {
            dealerResults.put(result, 0);
        }
        HashMap<Participant, BlackjackResult> playerResults = new HashMap<>();

        for (Participant player : players) {
            BlackjackResult dealerResult = BlackjackResult.getDealerResult(dealer, player);
            BlackjackResult playerResult = BlackjackResult.getOpposite(dealerResult);
            playerResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.getOrDefault(dealerResult, 0) + 1);
        }
        return new ResultDto(dealerResults, playerResults);
    }
}
