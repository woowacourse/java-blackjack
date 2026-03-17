package blackjack.controller;

import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.cardDeck.RandomPickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.service.BlackjackService;
import blackjack.service.dto.GameState;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DistributionCompleteOutputRequest;
import blackjack.view.dto.ParticipantCardsOutputRequest;
import blackjack.view.dto.ParticipantCardsWithScoreOutputRequest;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private static final PickStrategy PICK_STRATEGY = new RandomPickStrategy();

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackService blackjackService;

    public BlackjackController(
            InputView inputView,
            OutputView outputView,
            BlackjackService blackjackService
    ) {
        validate(inputView, outputView, blackjackService);

        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackService = blackjackService;
    }

    private void validate(
            InputView inputView,
            OutputView outputView,
            BlackjackService blackjackService
    ) {
        if (inputView == null) {
            throw new IllegalArgumentException("inputView가 null입니다.");
        }

        if (outputView == null) {
            throw new IllegalArgumentException("outputView가 null입니다.");
        }

        if (blackjackService == null) {
            throw new IllegalArgumentException("blackjackService가 null입니다.");
        }
    }

    public void run() {
        GameState gameState = startGame();
        GameState cardDistributed = distributeInitialCards(gameState);

        GameState bustApplied = askHitOrStand(cardDistributed);
        drawUntilSeventeen(bustApplied);

        GameState awarded = awardPlayers(bustApplied);
        outputFinalResult(awarded);
    }

    private GameState startGame() {
        List<String> names = inputPlayerNames();
        List<Integer> betAmounts = inputBetAmounts(names);

        return blackjackService.setUp(
                names,
                betAmounts,
                PICK_STRATEGY
        );
    }

    private List<String> inputPlayerNames() {
        outputView.printPlayerNamesInputPrompt();
        return inputView.inputPlayerNames();
    }

    private List<Integer> inputBetAmounts(List<String> names) {
        List<Integer> betAmounts = new ArrayList<>();

        for (String name: names) {
            outputView.printBetAmountInputPrompt(name);
            betAmounts.add(inputView.inputBetAmount());
        }

        return betAmounts;
    }

    private GameState distributeInitialCards(GameState gameState) {
        Players distributedPlayers = blackjackService.distributeInitialCards(gameState);
        GameState distributed = gameState.updatePlayers(distributedPlayers);

        outputView.printCardDistributionCompleted(
                DistributionCompleteOutputRequest.from(distributed)
        );

        openDealerHands(gameState.dealer());
        openPlayerHands(distributedPlayers);

        return distributed;
    }
    private void openDealerHands(Dealer dealer) {
        outputView.printParticipantCards(
                ParticipantCardsOutputRequest.from(dealer)
        );
    }

    private void openPlayerHands(Players distributedPlayers) {
        blackjackService.forEach(
                distributedPlayers,
                player -> outputView.printParticipantCards(
                        ParticipantCardsOutputRequest.from(player)
                )
        );
    }


    private GameState askHitOrStand(GameState gameState) {
        blackjackService.forEach(
                gameState.players(),
                player -> {
                    while (!player.isBust() && inputIsContinued(player)) {
                        blackjackService.pickAdditionalCard(player, gameState.cardDeck());
                        outputView.printParticipantCards(ParticipantCardsOutputRequest.from(player));
                    }
                }
        );

        Players bustAppliedPlayers = blackjackService.applyBustToPlayers(gameState.players());

        return new GameState(
                bustAppliedPlayers,
                gameState.dealer(),
                gameState.cardDeck()
        );
    }

    private boolean inputIsContinued(Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        return inputView.inputMoreCard();
    }

    private void drawUntilSeventeen(GameState gameState) {
        int drawnCount = blackjackService.drawDealerCardUntilSeventeen(gameState);

        for (int outputCount = 0; outputCount < drawnCount; outputCount++) {
            outputView.printDealerPicksCard();
        }
    }

    private GameState awardPlayers(GameState bustApplied) {
        Players awardedPlayers = blackjackService.awardPlayers(bustApplied);
        return bustApplied.updatePlayers(awardedPlayers);
    }

    private void outputFinalResult(GameState gameState) {
        openDealerHandsWithScore(gameState.dealer());
        openPlayersHandsWithScore(gameState.players());
        openPlayerPrizes(gameState);
    }

    private void openDealerHandsWithScore(Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                ParticipantCardsWithScoreOutputRequest.from(dealer)
        );
    }

    private void openPlayersHandsWithScore(Players players) {
        blackjackService.forEach(
                players,
                player -> outputView.printParticipantCardsWithScore(
                        ParticipantCardsWithScoreOutputRequest.from(player)
                )
        );
    }

    private void openPlayerPrizes(GameState gameState) {
        outputView.printPlayerPrizes(
                gameState.players(),
                gameState.dealer().getName()
        );
    }
}
