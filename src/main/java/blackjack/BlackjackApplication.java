package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.participant.CardDrawCallback;
import blackjack.domain.participant.Dealer;
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
        announceInitiallyDistributedCards(dealer, players);

        playGame(deck, players, dealer);
        announceFinalScoresOfParticipants(dealer, players);
        announceMatchResult(dealer, players);
    }

    private void announceInitiallyDistributedCards(final Dealer dealer, final Players players) {
        final String firstCardNameOfDealer = dealer.getFirstCardName();
        final List<ParticipantDto> playerDtos = players.getPlayers().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printInitiallyDistributedCards(firstCardNameOfDealer, playerDtos);
    }

    private void playGame(final Deck deck, final Players players, final Dealer dealer) {
        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);
    }

    private void proceedPlayersTurn(final Deck deck, final Players players) {
        players.drawCardsPerPlayer(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return blackjackView.requestDrawingCardChoice(participantName);
            }

            @Override
            public void onUpdate(final String playerName, final List<String> cardNames) {
                blackjackView.printCurrentCardsOfPlayer(playerName, cardNames);
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
                blackjackView.printMessageOfDealerDrewCard();
            }
        });
    }

    private void announceFinalScoresOfParticipants(final Dealer dealer, final Players players) {
        final ParticipantDto dealerDto = ParticipantDto.toDto(dealer);
        final List<ParticipantDto> playerDtos = players.getPlayers().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toUnmodifiableList());

        blackjackView.printFinalScoresOfParticipants(dealerDto, playerDtos);
    }

    private void announceMatchResult(final Dealer dealer, final Players players) {
        final MatchResult matchResult = players.judgeWinners(dealer);
        final MatchResultDto matchResultDto = MatchResultDto.toDto(matchResult);

        blackjackView.printMatchResult(matchResultDto);
    }

}
