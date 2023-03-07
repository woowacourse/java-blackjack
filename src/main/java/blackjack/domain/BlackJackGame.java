package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ShufflingMachine;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.EnumMap;
import java.util.Map;

public class BlackJackGame {

    private static final int NUMBER_OF_INITIAL_CARD = 2;

    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final String inputNames) {
        this.dealer = new Dealer();
        this.players = new Players(inputNames);
    }

    public void handOutCardTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        final Card card = Deck.from(shufflingMachine.draw());
        participant.receiveCard(card);
    }

    public void handOutInitCards(final ShufflingMachine shufflingMachine) {
        handOutInitCardsTo(shufflingMachine, dealer);
        players.getPlayers()
                .forEach(player -> handOutInitCardsTo(shufflingMachine, player));
    }

    private void handOutInitCardsTo(final ShufflingMachine shufflingMachine, final Participant participant) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARD; i++) {
            final Card card = Deck.from(shufflingMachine.draw());
            participant.receiveCard(card);
        }
    }

    public Map<Result, Integer> findWinner(final PlayerResult playerResult) {
        final Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (final Player player : players.getPlayers()) {
            playerResult.calculatePlayerResult(player, dealer);
            final Result resultForDealer = changeResultForDealer(playerResult.getPlayerResult(player));
            dealerResult.put(resultForDealer, dealerResult.getOrDefault(resultForDealer, 0) + 1);
        }
        return dealerResult;
    }

    private Map<Result, Integer> calculateDealerResult(final PlayerResult playerResult) {
        final Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (final Player player : players.getPlayers()) {
            Result result = changeResultForDealer(playerResult.getPlayerResult(player));
            dealerResult.put(result, dealerResult.getOrDefault(result, 0) + 1);
        }
        return dealerResult;
    }

    private Result changeResultForDealer(final Result result) {
        if (result.equals(Result.WIN)) {
            return Result.LOSE;
        }
        if (result.equals(Result.LOSE)) {
            return Result.WIN;
        }
        return Result.PUSH;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public Players getPlayers() {
        return this.players;
    }
}
