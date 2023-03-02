package controller;

import domain.area.CardArea;
import domain.deck.CardDeck;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Participant;
import domain.player.State;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackJackController {

    public void run() {

        final List<Name> participantNames = createParticipantNames();

        final CardDeck cardDeck = CardDeck.shuffledFullCardDeck();

        final List<Participant> participants = dealParticipantsCards(cardDeck, participantNames);
        final Dealer dealer = dealDealerCars(cardDeck);

        OutputView.printAfterDeal(participants);
        OutputView.showDealerState(dealer);
        OutputView.showPlayersState(participants);

        hitForParticipants(cardDeck, participants);
        hitForDealer(cardDeck, dealer);

        //최종 승패
        gameStatistic(participants, dealer);
    }

    private void hitForDealer(final CardDeck cardDeck, final Dealer dealer) {
        while (dealer.canHit()) {
            OutputView.dealerOneMoreCard();
            dealer.hit(cardDeck.draw());
        }
    }

    private void hitForParticipants(final CardDeck cardDeck, final List<Participant> participants) {
        for (final Participant participant : participants) {
            hitForParticipant(cardDeck, participant);
        }
    }

    private void hitForParticipant(final CardDeck cardDeck, final Participant participant) {
        while (participant.canHit()) {
            participant.changeState(inputHitOrStay(participant));
            hitOrStayForParticipant(cardDeck, participant);
        }
    }

    private void hitOrStayForParticipant(final CardDeck cardDeck, final Participant participant) {
        if (participant.wantHit()) {
            participant.hit(cardDeck.draw());
        }
        OutputView.showPlayerState(participant);
    }

    private State inputHitOrStay(final Participant participant) {
        if (InputView.readMoreCard(participant).equals("y")) {
            return State.HIT;
        }
        return State.STAY;
    }

    private Dealer dealDealerCars(final CardDeck cardDeck) {
        // TODO 수정
        return new Dealer(new Name("딜러"), new CardArea(cardDeck.draw(), cardDeck.draw()));
    }

    private List<Participant> dealParticipantsCards(final CardDeck cardDeck, final List<Name> participantNames) {
        return participantNames.stream()
                .map(it -> new Participant(it, new CardArea(cardDeck.draw(), cardDeck.draw())))
                .collect(Collectors.toList());
    }

    private List<Name> createParticipantNames() {
        return InputView.readParticipantsName()
                .stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private void gameStatistic(final List<Participant> participants, final Dealer dealer) {
        final Map<Participant, PlayerResult> collect1 = participants.stream().collect(Collectors.toMap(Function.identity(), it -> PlayerResult.judge(it, dealer)));
        OutputView.showGameStatistic(new ResultDto(collect1, participants, dealer));
    }

    public enum PlayerResult {
        WINNER,
        LOSER,
        DRAWER;

        public static PlayerResult judge(final Participant it, final Dealer dealer) {
            if (it.isBurst()) {
                return PlayerResult.LOSER;
            }
            if (dealer.isBurst()) {
                return PlayerResult.WINNER;
            }
            if (it.score() > dealer.score()) {
                return PlayerResult.WINNER;
            }
            if (it.score() == dealer.score()) {
                return PlayerResult.DRAWER;
            }
            return PlayerResult.LOSER;
        }

        public PlayerResult reverse() {
            if (this == WINNER) {
                return LOSER;
            }
            if (this == LOSER) {
                return WINNER;
            }
            return this;
        }
    }

    public class ResultDto {
        private final Map<Participant, PlayerResult> participantsResult;
        private final List<Participant> participants;
        private final Dealer dealer;

        public ResultDto(final Map<Participant, PlayerResult> participantsResult, final List<Participant> participants, final Dealer dealer) {
            this.participantsResult = participantsResult;
            this.participants = participants;
            this.dealer = dealer;
        }

        public Map<Participant, PlayerResult> participantsResult() {
            return participantsResult;
        }

        public List<Participant> participants() {
            return participants;
        }

        public Dealer dealer() {
            return dealer;
        }
    }
}
