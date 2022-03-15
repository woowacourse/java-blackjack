package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";

    private final CardStack cardDeck;
    private final Dealer dealer;
    private final List<Player> participants = new ArrayList<>();

    public BlackjackGame(CardStack cardDeck, List<String> playerNames) {
        validatePlayerNames(playerNames);

        this.cardDeck = cardDeck;
        this.dealer = Dealer.of(initializeHand());
        participants.addAll(initializePlayers(playerNames));
    }

    private List<Player> initializePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> Player.of(name, initializeHand()))
                .collect(Collectors.toList());
    }

    private void validatePlayerNames(List<String> playerNames) {
        if (playerNames.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    public int giveExtraCardsToDealer() {
        int extraCardCount = 0;
        while (dealer.canReceive()) {
            dealer.receiveCard(cardDeck.pop());
            extraCardCount++;
        }

        return extraCardCount;
    }

    public Card popCard() {
        return cardDeck.pop();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getParticipants() {
        return List.copyOf(participants);
    }

    private Hand initializeHand() {
        return Hand.of(cardDeck.pop(), cardDeck.pop());
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
