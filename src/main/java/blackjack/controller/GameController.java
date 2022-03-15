package blackjack.controller;

import blackjack.domain.Record;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomGeneratingStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.CardDto;
import blackjack.dto.DealerRecordDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.PlayerRecordDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerAnswer;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;

    public GameController() {
        this.deck = Deck.from(new RandomGeneratingStrategy());
        this.players = initPlayers();
        this.dealer = new Dealer();
    }

    private Players initPlayers() {
        try {
            return new Players(InputView.getPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return initPlayers();
        }
    }

    public void initParticipants() {
        dealer.initCards(deck);
        players.initCards(deck);

        final List<String> allNames = players.getNames();
        final CardDto firstCard = CardDto.from(dealer.openFirstCard());

        OutputView.printAssignmentResult(allNames);
        OutputView.printDealerFirstCard(firstCard);

        final List<ParticipantDto> allPlayers = players.getValue().stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
        for (ParticipantDto allPlayer : allPlayers) {
            OutputView.printCards(allPlayer);
        }
    }

    public void progressPlayerTurns() {
        while (players.isDrawablePlayerExist()) {
            final String playerName = players.findHitPlayer().getName();
            final PlayerAnswer playerAnswer = InputView.getHitOrStay(playerName);

            final ParticipantDto dto = progressPlayerTurn(playerName, playerAnswer);
            OutputView.printCards(dto);
        }
    }

    private ParticipantDto progressPlayerTurn(final String playerName, final PlayerAnswer playerAnswer) {
        final Player player = players.findByName(playerName);
        if (playerAnswer.isDraw()) {
            player.hit(deck);
        }
        if (!playerAnswer.isDraw()) {
            player.stay();
        }

        return ParticipantDto.from(player);
    }

    public void progressDealerTurn() {
        int count = 0;
        while (dealer.canDrawCard()) {
            dealer.hit(deck);
            count++;
        }

        OutputView.printDealerTurnResult(new DealerTurnResultDto(count));
    }

    public void endGame() {
        printAllCards();
        printAllRecords();
    }

    private void printAllCards() {
        final List<ParticipantResultDto> dto = players.getValue().stream()
                .map(ParticipantResultDto::from)
                .collect(Collectors.toList());
        dto.add(0, ParticipantResultDto.from(dealer));

        OutputView.breakLine();
        dto.forEach(OutputView::printCardsAndScore);
    }

    private void printAllRecords() {
        final int dealerScore = dealer.getScore();
        final List<PlayerRecordDto> playerRecordDtos = players.getValue().stream()
                .map(player -> PlayerRecordDto.of(player.getName(), Record.of(dealerScore, player.getScore())))
                .collect(Collectors.toList());
        final DealerRecordDto dealerRecordDto = DealerRecordDto.from(playerRecordDtos);

        OutputView.printDealerRecord(dealerRecordDto);
        playerRecordDtos.forEach(OutputView::printPlayerRecord);
    }
}