package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameManager;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        GameManager gameManager = new GameManager();
        Dealer dealer = gameManager.inviteDealer();
        List<String> playerNames = inputView.readPlayerName();
        Players players = gameManager.invitePlayers(playerNames, dealer);
        outputView.displayCardDistribution(DistributedCardDto.from(dealer), DistributedCardDto.fromPlayers(players));

        hitExtraCardForPlayers(players, dealer);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), FinalResultDto.fromPlayers(players));

        Map<GameResult, Integer> dealerResult = gameManager.generateDealerFinalResult(dealer, players);
        Map<Player, GameResult> playersResult = gameManager.generatePlayersFinalResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Map.Entry<Player, GameResult> entry : playersResult.entrySet()) {
            outputView.displayPlayerResult(entry.getKey(), entry.getValue());
        }
    }

    private void hitExtraCardForPlayers(final Players players, final Dealer dealer) {
        for (Player player : players.members()) {
            processPlayerHit(player, dealer);
        }
    }

    private void processPlayerHit(final Player player, final Dealer dealer) {
        while (canContinuePlayerHit(player) && isPlayerHitOptionYes(player)) {
            player.addCard(dealer.drawCard());
            outputView.displayCardInfo(DistributedCardDto.from(player));
        }
    }

    private boolean isPlayerHitOptionYes(final Player player) {
        String answer = inputView.readOneMoreCard(player.getName());
        return HitOption.from(answer).isYes();
    }

    private boolean canContinuePlayerHit(final Player player) {
        if (!player.canHit()) {
            outputView.displayBustNotice();
        }
        return player.canHit();
    }

    private void hitExtraCardForDealer(final Dealer dealer) {
        while (dealer.canHit()) {
            dealer.addCard(dealer.drawCard());
            outputView.displayExtraDealerCardStatus();
        }
    }
}
