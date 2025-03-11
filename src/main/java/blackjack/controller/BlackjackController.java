package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameReport;
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
        Dealer dealer = new Dealer(new CardDeck(), new CardDump());
        dealer.initCardDeck();

        List<String> playerNames = inputView.readPlayerName();
        Players players = new Players();
        players.receiveEachCardDeckFromDealer(playerNames, dealer);

        outputView.displayCardDistribution(DistributedCardDto.from(dealer), DistributedCardDto.fromPlayers(players));

        hitExtraCardForPlayers(players, dealer);
        hitExtraCardForDealer(dealer);

        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), FinalResultDto.fromPlayers(players));

        generateGameResultAndDisplay(dealer, players);
    }

    private void hitExtraCardForPlayers(final Players players, final Dealer dealer) {
        for (Player player : players.getPlayers()) {
            processPlayerHit(player, dealer);
        }
    }

    private void processPlayerHit(final Player player, final Dealer dealer) {
        while (canContinuePlayerHit(player) && isPlayerHitOptionYes(player)) {
            player.addCard(dealer.giveCardToPlayer());
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
            dealer.addCard();
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void generateGameResultAndDisplay(final Dealer dealer, final Players players) {
        GameReport gameReport = new GameReport();
        List<Player> playerList = players.getPlayers();

        Map<GameResult, Integer> dealerResult = gameReport.getDealerResult(dealer, playerList);
        outputView.displayDealerResult(dealerResult);

        for (Player player : playerList) {
            GameResult playerResult = gameReport.getPlayerResult(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
