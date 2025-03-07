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
import java.util.function.Supplier;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardDump cardDump;

    public BlackjackController(InputView inputView, OutputView outputView, CardDump cardDump) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardDump = cardDump;
    }

    public void run() {
        List<Player> players = createAndDistributeCardToPlayers();
        Dealer dealer = createDealerWithInitialDeck();

        outputView.displayCardDistribution(DistributedCardDto.from(dealer), DistributedCardDto.fromPlayers(players));

        hitExtraCardForPlayers(players);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(FinalResultDto.from(dealer), FinalResultDto.fromPlayers(players));

        generateGameResultAndDisplay(dealer, players);
    }

    private List<Player> createAndDistributeCardToPlayers() {
        String[] playerNames = getPlayerNames();

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            Player player = createPlayerWithInitialDeck(playerName);
            players.add(player);
        }
        return players;
    }

    private String[] getPlayerNames() {
        return getReturnWithRetry(inputView::readPlayerName);
    }

    private Player createPlayerWithInitialDeck(String playerName) {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());

        return new Player(playerName, cardDeck, cardDump);
    }

    private Dealer createDealerWithInitialDeck() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());
        return new Dealer(cardDeck, cardDump);
    }

    private void hitExtraCardForPlayers(final List<Player> players) {
        for (Player player : players) {
            processPlayerHit(player);
        }
    }

    private void processPlayerHit(final Player player) {
        while (true) {
            if (!player.canHit()) {
                outputView.displayBustNotice();
                break;
            }

            HitOption option = getHitOption(player);
            if (option.isNo()) break;

            player.addCard();
            outputView.displayCardInfo(DistributedCardDto.from(player));
        }
    }

    private HitOption getHitOption(Player player) {
        return getReturnWithRetry(() -> {
            String answer = inputView.readOneMoreCard(player.getName());
            return HitOption.from(answer);
        });
    }

    private void hitExtraCardForDealer(final Dealer dealer) {
        while (dealer.didHit()) {
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

    private <T> T getReturnWithRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.displayError(e.getMessage());
            }
        }
    }
}
