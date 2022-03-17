package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackjackGame;
import blackjack.domain.result.MatchResult;
import blackjack.dto.CardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.dealer.DealerDto;
import blackjack.dto.dealer.DealerInitialCardDto;
import blackjack.dto.player.PlayerDto;
import blackjack.dto.player.PlayerInitialCardsDto;
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
        final DealerInitialCardDto dealerInitialCardDto = DealerInitialCardDto.toDto(blackjackGame.getDealerFirstCard());
        final List<PlayerInitialCardsDto> playerInitialCardDtos = blackjackGame.getPlayers().stream()
                .map(PlayerInitialCardsDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printInitiallyDistributedCards(dealerInitialCardDto, playerInitialCardDtos);
    }

    private void proceedPlayersTurn() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            proceedPlayerTurn(playerName);
        }
    }

    private void proceedPlayerTurn(final String playerName) {
        while (blackjackGame.isPlayerPossibleToDrawCard(playerName)) {
            final boolean needToDrawCard = blackjackView.requestDrawingCardChoice(playerName);
            blackjackGame.playerDrawCard(playerName, needToDrawCard);

            final List<CardDto> cardDtos = blackjackGame.getPlayerCards(playerName).stream()
                    .map(CardDto::toDto)
                    .collect(Collectors.toUnmodifiableList());
            blackjackView.printCurrentCardsOfPlayer(playerName, cardDtos);
        }
    }

    private void proceedDealerTurn() {
        while (blackjackGame.isDealerPossibleToDrawCard()) {
            blackjackGame.dealerDrawCard();
            blackjackView.printMessageOfDealerDrewCard();
        }
    }

    private void announceFinalScoresOfParticipants() {
        final DealerDto dealerDto = DealerDto.toDto(blackjackGame.getDealer());
        final List<PlayerDto> playerDtos = blackjackGame.getPlayers().stream()
                .map(PlayerDto::toDto)
                .collect(Collectors.toUnmodifiableList());

        blackjackView.printFinalScores(dealerDto, playerDtos);
    }

    private void announceMatchResult() {
        final MatchResult matchResult = blackjackGame.calculateMatchResult();
        final MatchResultDto matchResultDto = MatchResultDto.toDto(matchResult);

        blackjackView.printMatchResult(matchResultDto);
    }

}
