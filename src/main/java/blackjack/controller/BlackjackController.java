package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameReport;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlackjackController {
    private final InputVIew inputView;
    private final OutputView outputView;
    private final CardDump cardDump;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardDump = new CardDump();
    }

    public void run() {
        Dealer dealer = new Dealer(new CardDeck(), cardDump);
        dealer.createInitialCardDeck();
        List<Player> players = createAndDistributeCardToPlayers(dealer);
        outputView.displayCardDistribution(DistributedCardDto.from(dealer), DistributedCardDto.fromPlayers(players));

        hitExtraCardForPlayers(players, dealer);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), FinalResultDto.fromPlayers(players));

        generateGameResultAndDisplay(dealer, players);
    }

    private List<Player> createAndDistributeCardToPlayers(final Dealer dealer) {
        String[] playerNames = getPlayerNames();
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            Player player = new Player(playerName, new CardDeck());
            player.receiveInitialCardDeck(dealer.giveCardsToPlayer());
            players.add(player);
        }
        return players;
    }

    private String[] getPlayerNames() {
        return processAndReturn(inputView::readPlayerName);
    }

    private <T> T processAndReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.displayError(e.getMessage());
            }
        }
    }

    private void hitExtraCardForPlayers(final List<Player> players, final Dealer dealer) {
        for (Player player : players) {
            processPlayerHit(player, dealer);
        }
    }

    private void processPlayerHit(final Player player, final Dealer dealer) {
        process(() -> {
            while (true) {
                if (!player.canHit()) {
                    outputView.displayBustNotice();
                    break;
                }
                String answer = inputView.readOneMoreCard(player.getName());
                HitOption option = HitOption.from(answer);
                if (option.isNo()) {
                    break;
                }
                player.addCard(dealer.giveCardToPlayer());
                outputView.displayCardInfo(DistributedCardDto.from(player));
            }
        });
    }

    private void process(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (Exception e) {
                outputView.displayError(e.getMessage());
            }
        }
    }

    private void hitExtraCardForDealer(final Dealer dealer) {
        while (dealer.canHit()) {
            dealer.addCard();
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void generateGameResultAndDisplay(final Dealer dealer, final List<Player> players) {
        GameReport gameReport = new GameReport();
        Map<GameResult, Integer> dealerResult = gameReport.getDealerResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = gameReport.getPlayerResult(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }
}
