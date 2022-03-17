package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.result.MatchResult;
import blackjack.dto.CardDto;
import blackjack.dto.InitiallyDrewCardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.BlackjackView;

public class BlackjackApplication {

    private final BlackjackGame blackjackGame;
    private final BlackjackView blackjackView;

    public BlackjackApplication(final BlackjackGame blackjackGame, final BlackjackView blackjackView) {
        this.blackjackGame = blackjackGame;
        this.blackjackView = blackjackView;
    }

    public void run() {
        registerPlayers();
        proceedBettingTurn();
        announceInitiallyDistributedCards();

        proceedPlayersTurn();
        proceedDealerTurn();
        announceFinalScoresOfParticipants();
        announceMatchResult();
    }

    private void registerPlayers() {
        final List<String> playerNames = blackjackView.requestPlayerNames();
        blackjackGame.registerPlayers(playerNames);
    }

    private void proceedBettingTurn() {
        for (final String playerName : blackjackGame.getPlayerNames()) {
            final int bettingAmount = blackjackView.requestPlayerBettingAmount(playerName);
            blackjackGame.playerBet(playerName, bettingAmount);
        }
    }

    private void announceInitiallyDistributedCards() {
        final InitiallyDrewCardDto dealerInitiallyDrewCardDto = InitiallyDrewCardDto.toDto(blackjackGame.getDealer());
        final List<InitiallyDrewCardDto> playerInitiallyDrewCardDtos = blackjackGame.getPlayers().stream()
                .map(InitiallyDrewCardDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printInitiallyDistributedCards(dealerInitiallyDrewCardDto, playerInitiallyDrewCardDtos);
    }

    private void proceedPlayersTurn() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            proceedPlayerTurn(playerName);
        }
    }

    private void proceedPlayerTurn(final String playerName) {
        while (isPlayerPossibleToProceedTurn(playerName)) {
            blackjackGame.playerDrawCard(playerName);

            final List<CardDto> cardDtos = blackjackGame.getPlayerCards(playerName).stream()
                    .map(CardDto::toDto)
                    .collect(Collectors.toUnmodifiableList());
            blackjackView.printCurrentCardsOfPlayer(playerName, cardDtos);
        }
    }

    private boolean isPlayerPossibleToProceedTurn(final String playerName) {
        return blackjackGame.isPlayerPossibleToDrawCard(playerName)
                && blackjackView.requestDrawingCardChoice(playerName);
    }

    private void proceedDealerTurn() {
        while (blackjackGame.isDealerPossibleToDrawCard()) {
            blackjackGame.dealerDrawCard();
            blackjackView.printMessageOfDealerDrewCard(blackjackGame.getDealerName());
        }
    }

    private void announceFinalScoresOfParticipants() {
        final ParticipantDto dealerDto = ParticipantDto.toDto(blackjackGame.getDealer());
        final List<ParticipantDto> playerDtos = blackjackGame.getPlayers().stream()
                .map(ParticipantDto::toDto)
                .collect(Collectors.toUnmodifiableList());

        blackjackView.printFinalScoresOfParticipants(dealerDto, playerDtos);
    }

    private void announceMatchResult() {
        final MatchResult matchResult = blackjackGame.calculateMatchResult();
        final MatchResultDto matchResultDto = MatchResultDto.toDto(matchResult);

        blackjackView.printMatchResult(matchResultDto);
    }

}
