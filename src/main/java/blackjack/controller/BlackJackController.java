package blackjack.controller;

import blackjack.controller.dto.ParticipantResponse;
import blackjack.controller.dto.ParticipantResponses;
import blackjack.controller.dto.ParticipantResultResponse;
import blackjack.domain.BlackJackGame;
import blackjack.domain.betting.Betting;
import blackjack.domain.betting.BettingTable;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerName;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final BlackJackGame blackJackGame = createBlackJackGame();
        initialGame(blackJackGame);

        drawCardsForPlayers(blackJackGame);

        printResult(blackJackGame);
    }

    private BlackJackGame createBlackJackGame() {
        final Participants participants = new Participants(new Dealer(), gatherPlayers());
        final BettingTable bettingTable = createBettingTable(participants.getPlayerNames());
        return new BlackJackGame(participants, bettingTable);
    }

    private List<Player> gatherPlayers() {
        return inputView.readPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private BettingTable createBettingTable(final List<PlayerName> playerNames) {
        final Map<PlayerName, Betting> bettingTable = new HashMap<>();
        playerNames.forEach(
                playerName -> bettingTable.put(playerName, new Betting(inputView.readBetting(playerName.getValue()))));
        return new BettingTable(bettingTable);
    }

    private void initialGame(final BlackJackGame blackJackGame) {
        blackJackGame.initialDraw();
        final ParticipantResponse dealer = ParticipantResponse.ofDealerForHidden(blackJackGame);
        final List<ParticipantResponse> players = ParticipantResponses.listOfPlayer(blackJackGame);
        outputView.printDealCards(dealer, players, blackJackGame.getInitialDrawCount());
    }

    private void drawCardsForPlayers(final BlackJackGame blackJackGame) {
        final List<PlayerName> playerNames = blackJackGame.getPlayerNames();
        playerNames.forEach(playerName -> drawCard(playerName, blackJackGame));
    }

    private void drawCard(final PlayerName playerName, final BlackJackGame blackJackGame) {
        while (blackJackGame.isDrawablePlayer(playerName) && inputView.readMoreDraw(playerName.getValue())) {
            blackJackGame.dealCard(playerName);
            outputView.printCardsWithoutScore(ParticipantResponse.ofPlayer(playerName, blackJackGame));
        }
    }

    private void printResult(final BlackJackGame blackJackGame) {
        final List<ParticipantResponse> participants = ParticipantResponses.listOfPlayer(blackJackGame);
        outputView.printCardsWithScore(participants);
        outputView.printFinalResult(createParticipantResultResponses(blackJackGame));
    }

    private List<ParticipantResultResponse> createParticipantResultResponses(final BlackJackGame blackJackGame) {
        final List<ParticipantResultResponse> participants = new ArrayList<>();
        participants.add(ParticipantResultResponse.forDealer(blackJackGame));
        return participants;
    }
}
