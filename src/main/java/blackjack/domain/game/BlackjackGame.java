package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String NO_PLAYER_EXCEPTION_MESSAGE = "플레이어가 없는 게임은 존재할 수 없습니다.";
    private static final String DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE = "플레이어명은 중복될 수 없습니다.";

    private final CardStack cardDeck;
    private final GameParticipants participants;

    public BlackjackGame(final CardStack cardDeck,
                         final List<String> playerNames,
                         final CardBundleStrategy strategy) {

        validatePlayerNames(playerNames);
        this.cardDeck = cardDeck;
        this.participants = generateParticipantsFrom(playerNames, strategy);
    }

    private GameParticipants generateParticipantsFrom(final List<String> playerNames,
                                                      final CardBundleStrategy strategy) {

        return GameParticipants.of(initializeDealer(strategy), initializePlayers(playerNames, strategy));
    }

    private Dealer initializeDealer(final CardBundleStrategy strategy) {
        return Dealer.of(strategy.initCardBundle(cardDeck));
    }

    private List<Player> initializePlayers(final List<String> playerNames,
                                           final CardBundleStrategy strategy) {
        return playerNames.stream()
                .map(name -> Player.of(name, strategy.initCardBundle(cardDeck)))
                .collect(Collectors.toList());
    }

    private void validatePlayerNames(final List<String> playerNames) {
        validatePlayerExists(playerNames);
        validateNoDuplicateNames(playerNames);
    }

    private void validatePlayerExists(final List<String> playerNames) {
        if (playerNames.size() == 0) {
            throw new IllegalArgumentException(NO_PLAYER_EXCEPTION_MESSAGE);
        }
    }

    private void validateNoDuplicateNames(final List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException(DUPLICATE_PLAYER_NAMES_EXCEPTION_MESSAGE);
        }
    }

    public void distributeAllCards(final Function<String, Boolean> drawOrStayStrategy,
                                   final Consumer<Player> viewStrategy,
                                   final Runnable dealerViewStrategy) {
        for (Player player : getPlayers()) {
            player.drawAllCards(drawOrStayStrategy, this::popCard, viewStrategy);
        }
        drawAllDealerCards(dealerViewStrategy);
    }

    private void drawAllDealerCards(final Runnable dealerViewStrategy) {
        while (dealerCanDraw()) {
            drawDealerCard();
            dealerViewStrategy.run();
        }
    }

    private boolean dealerCanDraw() {
        return getDealer().canDraw();
    }

    private void drawDealerCard() {
        getDealer().receiveCard(popCard());
    }

    private Card popCard() {
        return cardDeck.pop();
    }

    public boolean isBlackjackDealer() {
        return getDealer().isBlackjack();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(participants.getPlayers());
    }

    public List<Participant> getParticipants() {
        return participants.getEveryone();
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
