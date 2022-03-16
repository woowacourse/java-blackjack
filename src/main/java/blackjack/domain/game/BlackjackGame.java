package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.GameParticipants;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import blackjack.strategy.CardBundleSupplier;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final String DEALER_NOT_FOUND_EXCEPTION_MESSAGE = "해당 게임에 딜러가 존재하지 않습니다.";

    private final CardStack cardDeck;
    private final GameParticipants participants;

    public BlackjackGame(final CardStack cardDeck,
                         final List<String> playerNames,
                         final CardBundleStrategy strategy) {

        this.cardDeck = cardDeck;
        this.participants = generateParticipantsFrom(playerNames, strategy);
    }

    private GameParticipants generateParticipantsFrom(final List<String> playerNames,
                                                      final CardBundleStrategy strategy) {

        CardBundleSupplier cardBundleSupplier = () -> strategy.initCardBundle(cardDeck);
        return GameParticipants.of(playerNames, cardBundleSupplier);
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

    public List<Participant> getParticipants() {
        return participants.getValue();
    }

    public Dealer getDealer() {
        return (Dealer) participants.getValue()
                .stream()
                .filter(participant -> participant instanceof Dealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(DEALER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public List<Player> getPlayers() {
        return participants.getValue()
                .stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "cardDeck=" + cardDeck +
                ", participants=" + participants +
                '}';
    }
}
