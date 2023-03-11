package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.betting.BettingTable;
import blackjack.domain.betting.Profit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerStateResponse;
import blackjack.view.dto.ParticipantResponse;
import blackjack.view.dto.ParticipantResultResponse;
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
        drawCardsForDealer(blackJackGame);

        printResult(blackJackGame);
    }

    private BlackJackGame createBlackJackGame() {
        final Participants participants = new Participants(new Dealer(), gatherPlayers());
        final BettingTable bettingTable = createBettingTable(participants.getPlayers());
        return new BlackJackGame(participants, bettingTable);
    }

    private List<Player> gatherPlayers() {
        return inputView.readPlayerNames()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private BettingTable createBettingTable(final List<Player> players) {
        final Map<Player, Profit> betting = new HashMap<>();
        players.forEach(player -> betting.put(player, new Profit(inputView.readBetting(player.getName()))));
        return new BettingTable(betting);
    }

    private void initialGame(final BlackJackGame blackJackGame) {
        blackJackGame.initialDraw();
        final ParticipantResponse dealer = ParticipantResponse.hiddenForDealer(blackJackGame.getDealer());
        final List<ParticipantResponse> players = createParticipantResponses(blackJackGame.getPlayers());
        outputView.printDealCards(dealer, players, blackJackGame.getInitialDrawCount());
    }

    private List<ParticipantResponse> createParticipantResponses(final List<? extends Participant> participants) {
        return participants.stream()
                .map(ParticipantResponse::from)
                .collect(Collectors.toList());
    }

    private void drawCardsForPlayers(final BlackJackGame blackJackGame) {
        final List<Player> players = blackJackGame.getPlayers();
        players.forEach(player -> drawCard(player, blackJackGame));
    }

    private void drawCard(final Player player, final BlackJackGame blackJackGame) {
        while (player.isDrawable() && inputView.readMoreDraw(player.getName())) {
            blackJackGame.dealCard(player);
            outputView.printCardsWithoutScore(ParticipantResponse.from(player));
        }
    }

    private void drawCardsForDealer(final BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.getDealer();
        if (dealer.isDrawable()) {
            blackJackGame.dealCard(dealer);
            outputView.printDealerDrawn(new DealerStateResponse(true, dealer.getMaximumDrawableScore()));
        }
        blackJackGame.applyPlayersProfit();
    }

    private void printResult(final BlackJackGame blackJackGame) {
        final List<ParticipantResponse> participants = createParticipantResponses(blackJackGame.getParticipants());
        outputView.printCardsWithScore(participants);
        outputView.printFinalResult(createParticipantResultResponses(blackJackGame));
    }

    private List<ParticipantResultResponse> createParticipantResultResponses(final BlackJackGame blackJackGame) {
        final List<ParticipantResultResponse> participants = new ArrayList<>();
        participants.add(ParticipantResultResponse.forDealer(blackJackGame));
        participants.addAll(createPlayerResultResponses(blackJackGame));
        return participants;
    }

    private List<ParticipantResultResponse> createPlayerResultResponses(final BlackJackGame blackJackGame) {
        return blackJackGame.getPlayers()
                .stream()
                .map(player -> ParticipantResultResponse.forPlayer(player, blackJackGame))
                .collect(Collectors.toList());
    }
}
