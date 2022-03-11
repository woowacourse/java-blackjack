package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.DeckGenerator;
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
    private final DeckGenerator deckGenerator;

    public BlackjackApplication(final BlackjackView blackjackView, final DeckGenerator deckGenerator) {
        this.blackjackView = blackjackView;
        this.deckGenerator = deckGenerator;
    }

    public void run() {
        final Deck deck = Deck.generate(deckGenerator);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Players players = Players.readyToPlay(blackjackView.requestPlayerNames(), deck);

        printParticipantsStatuses(dealer, players);
        playGame(deck, players, dealer);
        printMatchResult(dealer, players);
    }

    private void printParticipantsStatuses(final Dealer dealer, final Players players) {
        final String dealerFirstCardName = dealer.getFirstCardName();
        final List<ParticipantDto> playerDtos = players.getPlayers().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printFirstDistributedCards(dealerFirstCardName, playerDtos);
    }

    private void playGame(final Deck deck, final Players players, final Dealer dealer) {
        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);
    }

    private void proceedPlayersTurn(final Deck deck, final Players players) {
        players.drawCardsPerPlayer(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return blackjackView.requestContinuable(participantName);
            }

            @Override
            public void onUpdate(final String playerName, final List<String> cardNames) {
                blackjackView.printDistributedCards(playerName, cardNames);
            }
        });
    }

    private void proceedDealerTurn(final Deck deck, final Dealer dealer) {
        dealer.drawCards(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return true;
            }

            @Override
            public void onUpdate(final String participantName, final List<String> cardNames) {
                blackjackView.printMessageOfDealerDrawCard();
            }
        });
    }

    private void printMatchResult(final Dealer dealer, final Players players) {
        final List<ParticipantDto> participantDtos = players.getPlayers().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toList());
        participantDtos.add(0, ParticipantDto.toDto(dealer));

        final MatchResult result = players.judgeWinners(dealer);
        final MatchResultDto matchResultDto = MatchResultDto.toDto(result);

        blackjackView.printMatchResult(participantDtos, matchResultDto);
    }

}
