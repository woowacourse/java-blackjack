package blackjack.controller;

import blackjack.domain.BlackjackManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.ParticipantDto;
import blackjack.view.dto.ResultDto;
import java.util.List;

public class BlackjackController {

    public void play() {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getPlayerNames());
        BlackjackManager blackjackManager = new BlackjackManager(dealer, players);

        initBlackjack(blackjackManager);
        hitCardPlayersAndDealer(blackjackManager);
        printHitCardResult(blackjackManager);
        printGameResult(blackjackManager);
    }

    private void initBlackjack(final BlackjackManager blackjackManager) {
        blackjackManager.initDrawCards();
        ParticipantDto dealerDto = blackjackManager.createDealerDto();
        List<ParticipantDto> playerDtos = blackjackManager.createPlayerDtos();
        OutputView.printInitHands(dealerDto, playerDtos);
    }

    private void hitCardPlayersAndDealer(final BlackjackManager blackjackManager) {
        hitCardPlayers(blackjackManager);
        OutputView.printNewLine();
        hitCardDealer(blackjackManager);
    }

    private void hitCardPlayers(BlackjackManager blackjackManager) {
        for (String name : blackjackManager.getPlayerNames()) {
            hitCardPlayer(blackjackManager.findPlayerByName(name), blackjackManager);
        }
    }

    private void hitCardPlayer(final Player player, final BlackjackManager blackjackManager) {
        while (player.isHitable() && InputView.getHitOrStay(player.getName())) {
            blackjackManager.drawCardPlayer(player);
            OutputView.printPlayerHand(blackjackManager.createPlayerDto(player));
        }
        if (player.isBurst()) {
            OutputView.printPlayerBurst(blackjackManager.createPlayerDto(player));
        }
        OutputView.printPlayerHand(blackjackManager.createPlayerDto(player));
    }

    private void hitCardDealer(final BlackjackManager blackjackManager) {
        while (blackjackManager.isDealerHitable()) {
            blackjackManager.drawCardDealer();
            OutputView.printDealerHit(blackjackManager.createDealerDto());
        }
    }

    private void printHitCardResult(final BlackjackManager blackjackManager) {
        ParticipantDto dealerDto = blackjackManager.createDealerDto();
        List<ParticipantDto> playerDtos = blackjackManager.createPlayerDtos();
        OutputView.printDealerAndPlayersHandWithScore(dealerDto, playerDtos);
    }

    private void printGameResult(final BlackjackManager blackjackManager) {
        ResultDto dealerResult = blackjackManager.createDealerResultDto();
        List<ResultDto> playerResultDtos = blackjackManager.createPlayerResultDtos();
        OutputView.printGameResult(dealerResult, playerResultDtos);
    }
}
