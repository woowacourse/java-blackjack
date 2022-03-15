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

    // TODO: 필드 3개를 2개로 줄여야함. ParticipantGroup 도메인을 만들어 해결할 예정.
    private final CardStack cardDeck;
    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();

    public BlackjackGame(CardStack cardDeck, List<String> playerNames) {
        validatePlayerNamesNotEmpty(playerNames);
        validatePlayerNamesNotDuplicate(playerNames);

        this.cardDeck = cardDeck;
        this.dealer = Dealer.of(generateInitialHand());
        players.addAll(initializePlayers(playerNames));
    }

    private List<Player> initializePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> Player.of(name, generateInitialHand()))
                .collect(Collectors.toList());
    }

    private void validatePlayerNamesNotDuplicate(List<String> playerNames) {
        int originalSize = playerNames.size();
        int distinctSize = (int) playerNames.stream()
                .distinct()
                .count();

        if (originalSize != distinctSize) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerNamesNotEmpty(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    private Hand generateInitialHand() {
        return Hand.of(cardDeck.pop(), cardDeck.pop());
    }

    public boolean giveExtraCardToPlayer(Player player) {
        player.receiveCard(cardDeck.pop());
        return player.canReceive();
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

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", dealer=" + dealer +
                ", players=" + players +
                '}';
    }
}
