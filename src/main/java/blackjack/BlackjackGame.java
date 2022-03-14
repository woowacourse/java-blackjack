package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.card.strategy.CardStrategy;
import blackjack.domain.card.strategy.RandomCardStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.MatchResult;
import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.reader.ConsoleReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private static final ConsoleReader reader = new ConsoleReader();
    private static final InputView inputView = new InputView(reader);
    private static final OutputView outputView = new OutputView();
    private static final CardStrategy strategy = new RandomCardStrategy();

    public void run() {
        final Deck deck = Deck.generate(strategy);
        final Dealer dealer = Dealer.startWithTwoCards(deck);
        final Players players = initPlayers(deck);

        printParticipantsStatuses(dealer, players);

        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);

        printMatchResult(dealer, players);
    }

    private Players initPlayers(final Deck deck) {
        outputView.printMessageOfRequestPlayerNames();
        final List<String> playerNames = inputView.requestPlayerNames();
        return Players.startWithTwoCards(playerNames, deck);
    }

    private void printParticipantsStatuses(final Dealer dealer, final Players players) {
        final ParticipantDto dealerDto = ParticipantDto.toShowFirstCards(dealer);
        final List<ParticipantDto> playerDtos = players.getStatuses().stream()
                .map(ParticipantDto::toShowFirstCards)
                .collect(Collectors.toList());

        outputView.printMessageOfPlayerStatuses(dealerDto, playerDtos);
    }

    private void proceedDealerTurn(final Deck deck, final Dealer dealer) {
        while (dealer.shouldDraw()) {
            dealer.drawCard(deck);
            outputView.printMessageOfDealerDrawCard();
        }
    }

    private void proceedPlayersTurn(final Deck deck, final Players players) {
        for (Player player : players.getStatuses()) {
            takeTurn(deck, player);
        }
    }

    private void takeTurn(Deck deck, Player player) {
        while (!player.isBust() && requestContinue(player)) {
            player.drawCard(deck);
            outputView.printDistributedCards(ParticipantDto.toOpenAllCards(player));
        }
    }

    private boolean requestContinue(final Player player) {
        outputView.printMessageOfRequestContinuable(player);
        return inputView.requestContinuable();
    }

    private void printMatchResult(final Dealer dealer, final Players players) {
        final List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.toOpenAllCards(dealer));
        participantDtos.addAll(players.getStatuses().stream()
                .map(ParticipantDto::toOpenAllCards)
                .collect(Collectors.toList()));

        MatchResult result = players.judgeWinners(dealer);

        outputView.printScores(participantDtos);
        outputView.printMatchResult(MatchResultDto.toDto(result));
    }
}
