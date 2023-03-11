package domain.game;

import domain.CardShuffler;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.ParticipantInfo;
import domain.participant.ParticipantMoney;

import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

import static domain.game.GameReferee.FIRST_TURN_DEALER_WIN;
import static domain.game.GameReferee.FIRST_TURN_PLAYER_WIN;
import static domain.game.GameReferee.IS_ALL_BLACKJACK;
import static domain.game.GameReferee.IS_DEALER_WIN;
import static domain.game.GameReferee.IS_PLAYER_WIN;

public final class GameManager {

    private final Deck deck;
    private final ParticipantInfo participantInfo;

    private GameManager(final Deck deck, final ParticipantInfo participantInfo) {
        this.deck = deck;
        this.participantInfo = participantInfo;
    }

    public static GameManager create(final CardShuffler cardShuffler,
                                     final Map<Participant, ParticipantMoney> participantInfo) {
        return new GameManager(Deck.create(cardShuffler), ParticipantInfo.create(participantInfo));
    }

    public void handFirstCards() {
        participantInfo.getParticipants()
                .forEach(this::addTwoCard);
    }

    public void judgeFirstBettingResult() {
        final Participant dealer = participantInfo.findDealerInfo();
        participantInfo.getParticipants()
                .stream()
                .filter(Predicate.not(dealer::equals))
                .forEach(player -> {
                    judgePlayerLose(dealer, player);
                    judgePlayerWin(dealer, player);
                });
    }

    public void handCard(final Participant player) {
        player.addCard(deck.draw());
    }

    public void losePlayerMoney(final Participant player) {
        participantInfo.losePlayerMoney(player);
    }

    public void calculateProfit(final Map<Participant, ParticipantMoney> initMoneyInfo) {
        final Participant dealer = participantInfo.findDealerInfo();
        if (dealer.isBust()) {
            resetPlayerBettingMoney(dealer, initMoneyInfo);
            return;
        }
        calculatePlayerMoney(dealer, initMoneyInfo);
    }

    private void addTwoCard(final Participant participant) {
        participant.addCard(deck.draw(), deck.draw());
    }

    private void judgePlayerLose(final Participant dealer, final Participant player) {
        if (FIRST_TURN_DEALER_WIN.getReferee().test(dealer, player)) {
            participantInfo.losePlayerMoney(player);
        }
    }

    private void judgePlayerWin(final Participant dealer, final Participant player) {
        if (FIRST_TURN_PLAYER_WIN.getReferee().test(dealer, player)) {
            participantInfo.earnPlayerBonusMoney(player);
        }
    }

    private void calculatePlayerMoney(final Participant dealer,
                                      final Map<Participant, ParticipantMoney> initMoneyInfo) {
        participantInfo.getParticipants().stream()
                .filter(Predicate.not(Participant::isBust))
                .forEach(player -> judgeBettingResult(dealer, player, initMoneyInfo));
    }

    private void judgeBettingResult(final Participant dealer, final Participant player,
                                    final Map<Participant, ParticipantMoney> initMoneyInfo) {
        if (player.isBust()) {
            return;
        }
        if (IS_ALL_BLACKJACK.getReferee().test(dealer, player)) {
            resetPlayerBettingMoney(dealer, initMoneyInfo);
            return;
        }
        judgeWinner(dealer, player);
    }

    private void judgeWinner(final Participant dealer, final Participant player) {
        if (IS_DEALER_WIN.getReferee().test(dealer, player)) {
            participantInfo.losePlayerMoney(player);
        }
        if (IS_PLAYER_WIN.getReferee().test(dealer, player)) {
            participantInfo.earnPlayerMoney(player);
        }
    }

    private void resetPlayerBettingMoney(final Participant dealer,
                                         final Map<Participant, ParticipantMoney> initMoneyInfo) {
        participantInfo.getParticipants()
                .forEach(participant -> resetPlayerMoney(dealer, participant, initMoneyInfo));
    }

    private void resetPlayerMoney(final Participant dealer, final Participant player,
                                  final Map<Participant, ParticipantMoney> initMoneyInfo) {
        final Map<Participant, ParticipantMoney> allParticipantInfo = participantInfo.getParticipantInfo();
        if (player.equals(dealer)) {
            allParticipantInfo.put(dealer, ParticipantMoney.zero());
            return;
        }
        allParticipantInfo.put(player, initMoneyInfo.get(player));
    }

    public Map<String, ParticipantMoney> getPlayerGameResults() {
        return Collections.unmodifiableMap(participantInfo.getParticipantInfoWithName());
    }

    public ParticipantInfo getParticipantInfo() {
        return participantInfo;
    }
}
