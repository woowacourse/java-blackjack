package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.List;

public class BlackjackGame {

    public static final int INITIAL_CARDS = 2;
    public static final int DEALER_MIN_SCORE = 16;
    public static final int BLACKJACK_SCORE = 21;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    private BlackjackGame(CardDeck cardDeck, Dealer dealer, Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame of(CardDeck cardDeck, Dealer dealer, Players players) {
        return new BlackjackGame(cardDeck, dealer, players);
    }

    public void distributeCards() {
        for (int count = 0; count < INITIAL_CARDS; count++) {
            players.receiveCards(cardDeck);
            dealer.receive(cardDeck.popCard());
        }
    }

    public void passCardToPlayer(String name) {
        players.passCardByName(name, cardDeck.popCard());
    }

    public boolean passCardToDealer() {
        if (dealer.getScore() > DEALER_MIN_SCORE) { // TODO: 판단을 여기서 하지 말고, 딜러에게 메시지를 던져 판단
            return false;
        }
        dealer.receive(cardDeck.popCard());
        return true;
    }

    // TODO: 직접적으로 게임의 스코어를 반환하는 것 보다, 해당 사용자가 Blackjack인지 판별하는 것이 더 나아보임
    public int getScoreOf(String name) {
        return players.getScoreOf(name);
    }

    // TODO: 모델에서 이름을 빼는 것이 맞는 것일까? Controller에 이미 정보가 있지 않은가? 하지만 모델에서 정보를 빼내는 것이 더 정확하긴 하다. 고민
    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public Player getPlayerByName(String name) {
        return players.findByName(name);
    }
}
