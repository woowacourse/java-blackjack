package blackjack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.participant.CardDrawCallback;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;
import blackjack.dto.CardDto;
import blackjack.dto.InitiallyDrewCardDto;
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
        playGame(deck, dealer, players);
    }

    private void playGame(final Deck deck, final Dealer dealer, final Players players) {
        proceedBettingTurn(players);

        announceInitiallyDistributedCards(dealer, players);

        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);

        announceFinalScoresOfParticipants(dealer, players);
        announceMatchResult(dealer, players);
    }

    private void proceedBettingTurn(final Players players) {
        final Map<String, Integer> bettingAmounts = blackjackView.requestPlayerBettingAmounts(players.getPlayerNames());
        players.betAmounts(bettingAmounts);
    }

    private void announceInitiallyDistributedCards(final Dealer dealer, final Players players) {
        final InitiallyDrewCardDto dealerInitiallyDrewCardDto = InitiallyDrewCardDto.toDto(dealer);
        final List<InitiallyDrewCardDto> playerInitiallyDrewCardDtos = players.getPlayers().stream()
                .map(InitiallyDrewCardDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printInitiallyDistributedCards(dealerInitiallyDrewCardDto, playerInitiallyDrewCardDtos);
    }


    private void proceedPlayersTurn(final Deck deck, final Players players) {
        players.drawCardsPerPlayer(deck, new CardDrawCallback() {
            @Override
            public boolean isContinuable(final String participantName) {
                return blackjackView.requestDrawingCardChoice(participantName);
            }

            @Override
            public void onUpdate(final String playerName, final List<Card> cards) {
                final List<CardDto> cardDtos = cards.stream()
                        .map(CardDto::toDto)
                        .collect(Collectors.toUnmodifiableList());
                blackjackView.printCurrentCardsOfPlayer(playerName, cardDtos);
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
            public void onUpdate(final String dealerName, final List<Card> cards) {
                blackjackView.printMessageOfDealerDrewCard(dealerName);
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
