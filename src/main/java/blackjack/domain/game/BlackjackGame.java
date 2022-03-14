package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";
    private static final String DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE = "플레이어명은 중복될 수 없습니다.";

    private final CardStack cardDeck;
    private final GameParticipants participants;

    public BlackjackGame(CardStack cardDeck, List<String> playerNames, CardBundleStrategy strategy) {
        validatePlayerNames(playerNames);
        this.cardDeck = cardDeck;
        this.participants = generateParticipantsFrom(playerNames, strategy);
    }

    private GameParticipants generateParticipantsFrom(List<String> playerNames, CardBundleStrategy strategy) {
        return GameParticipants.of(initializeDealer(strategy), initializePlayers(playerNames, strategy));
    }

    private Dealer initializeDealer(CardBundleStrategy strategy) {
        return Dealer.of(strategy.initCardBundle(cardDeck));
    }

    private List<Player> initializePlayers(List<String> playerNames, CardBundleStrategy strategy) {
        return playerNames.stream()
                .map(name -> Player.of(name, strategy.initCardBundle(cardDeck)))
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

    public boolean dealerCanDraw() {
        Dealer dealer = getDealer();
        return dealer.canDraw();
    }

    public void drawDealerCard() {
        Dealer dealer = getDealer();
        dealer.receiveCard(popCard());
    }

    public boolean isBlackjackDealer() {
        return participants.getDealer().isBlackjack();
    }

    public Card popCard() {
        return cardDeck.pop();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(participants.getPlayers());
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
