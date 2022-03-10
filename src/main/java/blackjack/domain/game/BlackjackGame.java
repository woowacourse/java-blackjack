package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";

    private final CardStack cardDeck;
    private final Dealer dealer;
    private final List<Player> participants = new ArrayList<>();

    public BlackjackGame(CardStack cardDeck, String... playerNames) {
        validatePlayerNames(playerNames);

        this.cardDeck = cardDeck;
        this.dealer = new Dealer(initializeCardBundle());
        participants.addAll(initializePlayers(playerNames));
    }

    private List<Player> initializePlayers(String[] playerNames) {
        return Arrays.stream(playerNames)
                .map(name -> new Player(name, initializeCardBundle()))
                .collect(Collectors.toList());
    }

    private void validatePlayerNames(String[] playerNames) {
        if (playerNames.length == 0) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    public boolean giveCardToDealer() {
        if (!dealer.canReceive()) {
            return false;
        }

        dealer.receiveCard(cardDeck.pop());
        return true;
    }

    public Card popCard() {
        return cardDeck.pop();
    }

    public List<Player> getParticipants() {
        return participants;
    }

    private CardBundle initializeCardBundle() {
        return CardBundle.of(cardDeck.pop(), cardDeck.pop());
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", dealer=" + dealer +
                ", participants=" + participants +
                '}';
    }
}
