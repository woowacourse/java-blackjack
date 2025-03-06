package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.CardDump;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.GameRule;
import blackjack.domain.Player;
import blackjack.dto.DistributedCardDto;
import blackjack.dto.FinalResultDto;
import blackjack.view.InputVIew;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlackjackController {
    private final InputVIew inputView;
    private final OutputView outputView;
    private final CardDump cardDump;
    private final GameRule gameRule;

    public BlackjackController() {
        this.inputView = new InputVIew();
        this.outputView = new OutputView();
        this.cardDump = new CardDump();
        this.gameRule = new GameRule();
    }

    public void run() {
        List<Player> players = createAndDistributeCardToPlayers();
        Dealer dealer = createDealerWithInitialDeck();
        outputView.displayFirstCardOfDealer(DistributedCardDto.from(dealer));
        players.stream()
                .map(DistributedCardDto::from)
                .toList()
                .forEach(outputView::displayCardInfo);

        hitExtraCardForPlayers(players);
        hitExtraCardForDealer(dealer);
        outputView.displayFinalCardStatus(
                FinalResultDto.from(dealer),
                players.stream().map(FinalResultDto::from).toList()
        );

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
        return processAndReturn(inputView::readPlayerName);
    }

    private Player createPlayerWithInitialDeck(String playerName) {
        CardDeck cardDeck = new CardDeck();
        cardDeck.add(cardDump.drawCard());
        cardDeck.add(cardDump.drawCard());

        return new Player(playerName, cardDeck, cardDump);
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
                player.addCard();
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
        while (dealer.didHit()) {
            outputView.displayExtraDealerCardStatus();
        }
    }

    private void generateGameResultAndDisplay(final Dealer dealer, final List<Player> players) {
        Map<GameResult, Integer> dealerResult = evaluateDealerFinalResult(dealer, players);
        outputView.displayDealerResult(dealerResult);
        for (Player player : players) {
            GameResult playerResult = evaluateGameResultFromPlayer(player, dealer);
            outputView.displayPlayerResult(player, playerResult);
        }
    }

    private Map<GameResult, Integer> evaluateDealerFinalResult(final Dealer dealer, final List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = gameRule.evaluateDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    private GameResult evaluateGameResultFromPlayer(final Player player, final Dealer dealer) {
        return gameRule.evaluatePlayerWin(player, dealer);
    }
}
