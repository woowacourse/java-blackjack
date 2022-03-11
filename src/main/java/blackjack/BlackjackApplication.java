package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.RandomCardStrategy;
import blackjack.domain.participant.CardDrawCallback;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.BlackjackView;

public class BlackjackApplication {

    private final BlackjackView blackjackView;

    public BlackjackApplication(final BlackjackView blackjackView) {
        this.blackjackView = blackjackView;
    }

    public void run() {
        final Deck deck = Deck.generate(new RandomCardStrategy());
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        final Players players = Players.startWithTwoCards(blackjackView.requestPlayerNames(), deck);

        printParticipantsStatuses(dealer, players);
        playGame(deck, players, dealer);
        printMatchResult(dealer, players);
    }

    private void printParticipantsStatuses(final Dealer dealer, final Players players) {
        final ParticipantDto dealerDto = ParticipantDto.toDtoOfDealer(dealer);
        final List<ParticipantDto> playerDtos = players.getStatuses().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printFirstDistributedCards(dealerDto, playerDtos);
    }

    private void playGame(final Deck deck, final Players players, final Dealer dealer) {
        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);
    }

    private void proceedPlayersTurn(final Deck deck, final Players players) {
        players.play(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return blackjackView.requestContinuable(participantName);
            }

            @Override
            public void onUpdate(Participant participant) {
                blackjackView.printDistributedCards(ParticipantDto.toDto(participant));
            }
        });
    }

    private void proceedDealerTurn(final Deck deck, final Dealer dealer) {
        dealer.continueDraw(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return true;
            }

            @Override
            public void onUpdate(Participant participant) {
                blackjackView.printMessageOfDealerDrawCard();
            }
        });
    }

    private void printMatchResult(final Dealer dealer, final Players players) {
        final List<ParticipantDto> participantDtos = players.getStatuses().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toList());
        participantDtos.add(0, ParticipantDto.toDto(dealer));

        MatchResult result = players.judgeWinners(dealer);
        MatchResultDto matchResultDto = MatchResultDto.toDto(result);

        blackjackView.printMatchResult(participantDtos, matchResultDto);
    }
}
