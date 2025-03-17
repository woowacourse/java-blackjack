package blackjack.domain.participant.participant;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;
import static blackjack.domain.result.ResultStatus.BLACKJACK;
import static blackjack.domain.result.ResultStatus.LOSE;
import static blackjack.domain.result.ResultStatus.PUSH;
import static blackjack.domain.result.ResultStatus.WIN;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.GameRule;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ResultStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Dealer extends Participant implements GameRule {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_THRESHOLD = 16;
    private static final int SPREAD_CARD_SIZE = 2;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public Dealer() {
        this(new Hand(new ArrayList<>()));
    }

    @Override
    public Hand showInitialCards() {
        return new Hand(List.of(hand.getFirstCard()));
    }

    @Override
    public boolean canHit() {
        int score = hand.calculateWithHardHand();
        return score <= DEALER_THRESHOLD;
    }

    public void dealInitialCards(final Players players, final Hand hand) {
        receiveCards(hand.subHand(0, SPREAD_CARD_SIZE));
        players.receiveCardsByCount(hand.subHand(SPREAD_CARD_SIZE, hand.getSize()), SPREAD_CARD_SIZE);
    }

    public ProfitResult calculateProfit(final Map<Player, Hand> playerScores) {
        final Map<Player, ResultStatus> result = playerScores.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey,
                        entry -> determinePlayerResult(entry.getValue()),
                        (e1, e2) -> e1, LinkedHashMap::new));
        return new ProfitResult(this, result);
    }

    private ResultStatus determinePlayerResult(final Hand playerHand) {
        if (hand.isBlackjack() && !playerHand.isBlackjack()) {
            return WIN;
        }
        if (!hand.isBlackjack() && playerHand.isBlackjack()) {
            return BLACKJACK;
        }
        return determineRegularGameResult(hand, playerHand);
    }

    private ResultStatus determineRegularGameResult(final Hand dealerHand, final Hand playerHand) {
        int dealerScore = dealerHand.calculateResult();
        int playerScore = playerHand.calculateResult();
        if (dealerScore <= BURST_THRESHOLD) {
            return determineResultWhenDealerNotBusted(dealerScore, playerScore);
        }
        return determineResultWhenDealerBusted(playerScore);
    }

    private ResultStatus determineResultWhenDealerNotBusted(final int dealerScore, final int playerScore) {
        if (playerScore <= BURST_THRESHOLD) {
            return determineResultWhenPlayerNotBusted(dealerScore, playerScore);
        }
        return WIN;
    }

    private ResultStatus determineResultWhenPlayerNotBusted(final int dealerScore, final int playerScore) {
        if (dealerScore > playerScore) {
            return WIN;
        }
        if (dealerScore == playerScore) {
            return PUSH;
        }
        return LOSE;
    }

    private ResultStatus determineResultWhenDealerBusted(final int playerScore) {
        if (playerScore > BURST_THRESHOLD) {
            return WIN;
        }
        return LOSE;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Dealer dealer)) {
            return false;
        }
        return Objects.equals(hand, dealer.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hand);
    }

    @Override
    public String getNickname() {
        return NICKNAME;
    }

    public int getSpreadCardSize() {
        return SPREAD_CARD_SIZE;
    }
}
