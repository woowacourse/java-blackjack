package blackjack;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackjackGame;
import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.result.MatchResult;
import blackjack.dto.CardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.dealer.DealerDto;
import blackjack.dto.dealer.DealerInitialCardDto;
import blackjack.dto.player.PlayerDto;
import blackjack.dto.player.PlayerInitialCardsDto;
import blackjack.view.BlackjackView;

public class BlackjackApplication {

    private final DeckGenerator deckGenerator;
    private final BlackjackView blackjackView;

    public BlackjackApplication(final DeckGenerator deckGenerator, final BlackjackView blackjackView) {
        this.deckGenerator = deckGenerator;
        this.blackjackView = blackjackView;
    }

    public void run() {
        final BlackjackGame blackjackGame = initializeBlackjackGame();
        proceedBettingTurn(blackjackGame);
        announceInitiallyDistributedCards(blackjackGame);

        proceedPlayersTurn(blackjackGame);
        proceedDealerTurn(blackjackGame);
        announceFinalScoresOfParticipants(blackjackGame);
        announceMatchResult(blackjackGame);
    }

    private BlackjackGame initializeBlackjackGame() {
        final List<String> playerNames = blackjackView.requestPlayerNames();
        return BlackjackGame.initialize(deckGenerator, playerNames);
    }

    private void proceedBettingTurn(final BlackjackGame blackjackGame) {
        for (final String playerName : blackjackGame.getPlayerNames()) {
            final int bettingAmount = blackjackView.requestPlayerBettingAmount(playerName);
            blackjackGame.playerBet(playerName, bettingAmount);
        }
    }

    private void announceInitiallyDistributedCards(final BlackjackGame blackjackGame) {
        final DealerInitialCardDto dealerInitialCardDto = DealerInitialCardDto.toDto(blackjackGame.getDealerFirstCard());
        final List<PlayerInitialCardsDto> playerInitialCardDtos = blackjackGame.getPlayers().stream()
                .map(PlayerInitialCardsDto::toDto)
                .collect(Collectors.toList());

        blackjackView.printInitiallyDistributedCards(dealerInitialCardDto, playerInitialCardDtos);
    }

    private void proceedPlayersTurn(final BlackjackGame blackjackGame) {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        for (final String playerName : playerNames) {
            proceedPlayerTurn(blackjackGame, playerName);
        }
    }

    private void proceedPlayerTurn(final BlackjackGame blackjackGame, final String playerName) {
        while (blackjackGame.isPlayerPossibleToDrawCard(playerName)) {
            final boolean needToDrawCard = blackjackView.requestDrawingCardChoice(playerName);
            blackjackGame.playerDrawCard(playerName, needToDrawCard);

            final List<CardDto> cardDtos = blackjackGame.getPlayerCards(playerName).stream()
                    .map(CardDto::toDto)
                    .collect(Collectors.toUnmodifiableList());
            blackjackView.printCurrentCardsOfPlayer(playerName, cardDtos);
        }
    }

    private void proceedDealerTurn(final BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerPossibleToDrawCard()) {
            blackjackGame.dealerDrawCard();
            blackjackView.printMessageOfDealerDrewCard();
        }
    }

    private void announceFinalScoresOfParticipants(final BlackjackGame blackjackGame) {
        final DealerDto dealerDto = DealerDto.toDto(blackjackGame.getDealer());
        final List<PlayerDto> playerDtos = blackjackGame.getPlayers().stream()
                .map(PlayerDto::toDto)
                .collect(Collectors.toUnmodifiableList());

        blackjackView.printFinalScores(dealerDto, playerDtos);
    }

    private void announceMatchResult(final BlackjackGame blackjackGame) {
        final MatchResult matchResult = blackjackGame.calculateMatchResult();
        final MatchResultDto matchResultDto = MatchResultDto.toDto(matchResult);

        blackjackView.printMatchResult(matchResultDto);
    }

}
