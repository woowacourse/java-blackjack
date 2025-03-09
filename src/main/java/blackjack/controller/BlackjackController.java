package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameReport;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {
    public void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CardDump cardDump = new CardDump();

        Dealer dealer = new Dealer(new CardDeck(), cardDump);
        dealer.createInitialCardDeck();
        List<Player> players = distributeCardToPlayers(dealer, inputView);
        outputView.displayCardDistribution(DistributedCardDto.from(dealer), DistributedCardDto.fromPlayers(players));

        hitExtraCardForPlayers(players, dealer, inputView, outputView);
        hitExtraCardForDealer(dealer, outputView);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), FinalResultDto.fromPlayers(players));

        generateGameResultAndDisplay(dealer, players, outputView);
    }

    private List<Player> distributeCardToPlayers(final Dealer dealer, final InputView inputView) {
        String[] playerNames = inputView.readPlayerName();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(playerName, new CardDeck());
            player.receiveInitialCardDeck(dealer.giveCardsToPlayer());
            players.add(player);
        }
        return players;
    }

    private void hitExtraCardForPlayers(final List<Player> players, final Dealer dealer,
                                        final InputView inputView, final OutputView outputView) {
        for (Player player : players) {
            processPlayerHit(player, dealer, inputView, outputView);
        }
    }

    private void processPlayerHit(final Player player, final Dealer dealer,
                                  final InputView inputView, final OutputView outputView) {
        while (canContinuePlayerHit(player, outputView)) {
            String answer = inputView.readOneMoreCard(player.getName());
            if (HitOption.from(answer).isNo()) {
                break;
            }
            player.addCard(dealer.giveCardToPlayer());
            outputView.displayCardInfo(DistributedCardDto.from(player));
        }
    }

    private boolean canContinuePlayerHit(final Player player, final OutputView outputView) {
        if (!player.canHit()) {
            outputView.displayBustNotice();
        }
        return player.canHit();
    }

    private void hitExtraCardForDealer(final Dealer dealer, final OutputView outputView) {
        while (dealer.canHit()) {
            dealer.addCard();
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void generateGameResultAndDisplay(final Dealer dealer, final List<Player> players,
                                              final OutputView outputView) {
        GameReport gameReport = new GameReport();
        Map<GameResult, Integer> dealerResult = gameReport.getDealerResult(dealer, players);
        outputView.displayDealerResult(dealerResult);

        for (Player player : players) {
            GameResult playerResult = gameReport.getPlayerResult(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
