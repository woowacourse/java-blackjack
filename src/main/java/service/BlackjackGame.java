package service;

import domain.card.Deck;
import domain.card.RandomCardGenerator;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Participants;
import domain.player.Player;
import domain.result.GameResult;
import dto.ResultDto;

import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackGame {

    public static final int DEALER_PROFIT_MULTIPLIER = -1;

    private final Deck deck;

    private BlackjackGame(final Deck deck) {
        this.deck = deck;
    }

    public static BlackjackGame from(final Deck deck) {
        return new BlackjackGame(deck);
    }

    public static BlackjackGame create() {
        return new BlackjackGame((Deck.from(new RandomCardGenerator())));
    }

    public void distributeInitialCards(final Participants participants, final Dealer dealer) {
        dealer.takeCard(deck.distributeCard());
        participants.getParticipants()
                .forEach(this::distributeTwoCards);
    }

    private void distributeTwoCards(final Player player) {
        player.takeCard(deck.distributeCard());
        player.takeCard(deck.distributeCard());
    }

    public void distributeCard(final Player player) {
        player.takeCard(deck.distributeCard());
    }

    public ResultDto calculateRevenues(final Participants participants, final Dealer dealer) {
        Map<String, Integer> participantsResult = calculateParticipantRevenues(participants, dealer);
        int dealerResult = calculateDealerRevenue(participantsResult);

        return new ResultDto(dealerResult, participantsResult);
    }

    private Map<String, Integer> calculateParticipantRevenues(final Participants participants, final Dealer dealer) {
        return participants.getParticipants().stream()
                .collect(Collectors.toMap(
                        Participant::getName,
                        participant -> calculateParticipantRevenue(participant, dealer)
                ));
    }

    private int calculateParticipantRevenue(final Participant participant, final Dealer dealer) {
        return calculateGameResult(dealer, participant)
                .getWinningAmount(participant.getBetValue()) - participant.getBetValue();
    }

    private int calculateDealerRevenue(final Map<String, Integer> participantsResult) {
        return participantsResult.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum() * DEALER_PROFIT_MULTIPLIER;
    }

    private GameResult calculateGameResult(final Dealer dealer, final Participant participant) {
        if (participant.isBust() || isParticipantDefeat(dealer, participant)) {
            return GameResult.DEFEAT;
        }
        if (isDraw(dealer, participant)) {
            return GameResult.PUSH;
        }
        if (participant.isBlackJack())
            return GameResult.BLACKJACK;
        return GameResult.VICTORY;
    }

    private boolean isParticipantDefeat(final Dealer dealer, final Participant participant) {
        return !dealer.isBust() && dealer.getScore() > participant.getScore();
    }

    private boolean isDraw(final Dealer dealer, final Participant participant) {
        return participant.getScore() == dealer.getScore();
    }
}
