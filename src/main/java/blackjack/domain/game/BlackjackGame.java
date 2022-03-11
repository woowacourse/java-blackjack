package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";
    public static final String DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE = "플레이어명은 중복될 수 없습니다.";

    private final CardStack cardDeck;
    private final GameParticipants participants;

    public BlackjackGame(CardStack cardDeck, List<String> playerNames) {
        validatePlayerNames(playerNames);
        this.cardDeck = cardDeck;
        this.participants = GameParticipants.of(initializeDealer(), initializePlayers(playerNames));
    }

    private Dealer initializeDealer() {
        return Dealer.of(initializeCardBundle());
    }

    private List<Player> initializePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> Player.of(name, initializeCardBundle()))
                .collect(Collectors.toList());
    }

    private void validatePlayerNames(List<String> playerNames) {
        validatePlayerExists(playerNames);
        validateNoDuplicateNames(playerNames);
    }

    private void validatePlayerExists(List<String> playerNames) {
        if (playerNames.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    private void validateNoDuplicateNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE);
        }
    }

    public boolean giveCardToDealer() {
        Dealer dealer = getDealer();

        if (!dealer.canReceive()) {
            return false;
        }

        dealer.receiveCard(cardDeck.pop());
        return true;
    }

    public Card popCard() {
        return cardDeck.pop();
    }

    public GameParticipants getParticipants() {
        return participants;
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(participants.getPlayers());
    }

    private CardBundle initializeCardBundle() {
        return CardBundle.of(cardDeck.pop(), cardDeck.pop());
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
