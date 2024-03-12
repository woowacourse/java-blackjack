package blackjack;

import blackjack.dto.DealerResultCount;
import blackjack.dto.NameCards;
import blackjack.dto.NameCardsScore;
import blackjack.dto.PlayerNameFinalResult;
import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.model.result.Referee;
import blackjack.model.result.Rule;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class BlackJackGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Dealer dealer = new Dealer(new Deck());
        final Players players = registerPlayers();
        final Referee referee = new Referee(new Rule(dealer));

        registerInitialCards(dealer, players);
        outputView.printDistributionSubject(players.getNames());
        printInitialCards(dealer, players);

        playPlayersTurn(players, dealer);
        playDealerTurn(dealer);

        printFinalResult(dealer, players, referee);
    }

    private Players registerPlayers() {
        try {
            final List<String> names = inputView.readPlayerName();
            return Players.from(names);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return registerPlayers();
        }
    }

    private void registerInitialCards(final Dealer dealer, final Players players) {
        dealer.receiveInitialCards(dealer.distributeInitialCard());
        players.receiveInitialCards(dealer::distributeInitialCard);
    }

    private void printInitialCards(final Dealer dealer, final Players players) {
        final List<NameCards> nameCards = createDealerPlayersNameCards(dealer, players);
        outputView.printPlayersNamesAndCards(nameCards);
    }

    private List<NameCards> createDealerPlayersNameCards(final Dealer dealer, final Players players) {
        List<NameCards> dealerNameCards = new ArrayList<>(
                List.of(new NameCards(OutputView.DEALER_NAME, dealer.openFirstCard())));
        List<NameCards> playersNameCards = players.stream()
                .map(NameCards::from)
                .toList();
        return Stream.of(dealerNameCards, playersNameCards)
                .flatMap(Collection::stream)
                .toList();
    }

    private void playPlayersTurn(final Players players, final Dealer dealer) {
        players.playEachPlayerTurns(this::playPlayerTurn, dealer);
    }

    private void playPlayerTurn(final Player player, final Dealer dealer) {
        if (!player.isBust() && player.canHit()) {
            distributeIfPlayerWant(isPlayerWantHit(player.getName()), player, dealer);
        }
    }

    private boolean isPlayerWantHit(final String name) {
        try {
            return inputView.readHitOrNot(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return isPlayerWantHit(name);
        }
    }

    private void distributeIfPlayerWant(final boolean isHit, final Player player, final Dealer dealer) {
        if (isHit) {
            distributeNewCard(player, dealer.distributeCard());
            outputView.printNameAndCards(NameCards.from(player));
            playPlayerTurn(player, dealer);
        }
    }

    private void playDealerTurn(final Dealer dealer) {
        while (dealer.canHit()) {
            outputView.printDealerHit();
            distributeNewCard(dealer, dealer.distributeCard());
        }
    }

    private void distributeNewCard(final Participant player, final Card card) {
        player.draw(card);
    }

    private void printFinalResult(final Dealer dealer, final Players players, final Referee referee) {
        printFinalCardsAndScores(dealer, players);
        printFinalResultCommand(referee, players);
    }

    private void printFinalCardsAndScores(final Dealer dealer, final Players players) {
        outputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(OutputView.DEALER_NAME, dealer.openCards(),
                dealer.notifyScore());
        List<NameCardsScore> playerNameCardsScore = players.collectFinalResults();
        outputView.printFinalCardsAndScore(dealerNameCardsScore);
        outputView.printFinalCardsAndScore(playerNameCardsScore);
    }

    private void printFinalResultCommand(final Referee referee, final Players players) {
        printDealerFinalResultCount(referee, players);
        List<PlayerNameFinalResult> playerNameFinalResults = players.stream()
                .map(player -> PlayerNameFinalResult.from(player, referee.judgePlayerResult(player)))
                .toList();
        outputView.printFinalResults(playerNameFinalResults);
    }

    private void printDealerFinalResultCount(final Referee referee, final Players players) {
        List<DealerResultCount> dealerResults = referee.judgeDealerResult(players)
                .entrySet().stream()
                .map(dealerResult -> new DealerResultCount(dealerResult.getKey(), dealerResult.getValue()))
                .toList();
        outputView.printDealerFinalResult(dealerResults);
    }
}
