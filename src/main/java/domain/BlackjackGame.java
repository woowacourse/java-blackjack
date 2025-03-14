package domain;

import domain.card.CardDeck;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public class BlackjackGame {

    public static final int INITIAL_CARDS = 2;
    private static final int DEALER_MIN_SCORE = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final CardDeck cardDeck;
    private final Participants participants;

    private BlackjackGame(CardDeck cardDeck, Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackjackGame of(CardDeck cardDeck, Participants participants) {
        return new BlackjackGame(cardDeck, participants);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            participants.passCardToPlayers(cardDeck);
            participants.passCardToDealer(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        participants.passCardToPlayer(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (participants.getScoreOfDealer() > DEALER_MIN_SCORE) {
            return false;
        }
        participants.passCardToDealer(cardDeck.popCard());
        return true;
    }

    // TODO: 직접적으로 게임의 스코어를 반환하는 것 보다, 해당 사용자가 Blackjack인지 판별하는 것이 더 나아보임
    public int getScoreOf(String name) {
        return participants.getScoreOfPlayer(name);
    }

    // TODO: 모델에서 이름을 빼는 것이 맞는 것일까? Controller에 이미 정보가 있지 않은가? 하지만 모델에서 정보를 빼내는 것이 더 정확하긴 하다. 고민
    public List<String> getPlayersName() {
        return participants.getPlayersName();
    }

    public Player getPlayerByName(String name) {
        return participants.getPlayerByName(name);
    }
}
