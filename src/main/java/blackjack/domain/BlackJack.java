package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    private static final int MAX_SCORE = 21;
    private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";
    private static final int MAX_PLAYER_NUMBER = 8;
    private static final int STARTING_CARDS_COUNT = 2;

    private final CardDeck cardDeck;
    private final Participants participants;

    private BlackJack(Participant dealer, List<Participant> players) {
        this.cardDeck = new CardDeck();
        this.participants = new Participants(dealer, players);
    }

    public static BlackJack createFrom(List<String> playerNames) {
        validatePlayerNumber(playerNames);
        return new BlackJack(Participant.createDealer(), createPlayers(playerNames));
    }

    private static List<Participant> createPlayers(List<String> playerNames) {
        return playerNames.stream()
            .map(Participant::createPlayer)
            .collect(Collectors.toList());
    }

    private static void validatePlayerNumber(List<String> players) {
        if (players.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
        }
    }

    public static boolean isMaxScore(int score) {
        return score == MAX_SCORE;
    }

    public static boolean isOverMaxScore(int score) {
        return score > MAX_SCORE;
    }

    public void handOutStartingCards() {
        for (int i = 0; i < STARTING_CARDS_COUNT; i++) {
            participants.handOutCardToAll(cardDeck);
        }
    }

    public void handOutCardTo(Participant participant) {
        participants.handOutCardTo(participant, cardDeck.pickCard());
    }

    public Participants getParticipants() {
        return participants;
    }
}
