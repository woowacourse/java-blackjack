package blackjack;

import blackjack.dto.NameCards;
import blackjack.dto.NameCardsScore;
import blackjack.dto.NameProfit;
import blackjack.model.money.Bets;
import blackjack.model.money.Money;
import blackjack.model.deck.Deck;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.model.result.ResultCommand;
import blackjack.model.result.Referee;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        final Referee referee = new Referee(dealer);
        final Bets bets = registerBets(players);

        registerInitialCards(dealer, players);
        printInitialCards(dealer, players);

        playParticipantsTurn(players, dealer);
        printFinalCardsAndScores(dealer, players);
        printResultProfits(referee, players, bets);
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

    private Bets registerBets(final Players players) {
        return players.createBets(this::readBetMoney);
    }

    private int readBetMoney(final String name) {
        try {
            return inputView.readBetMoney(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readBetMoney(name);
        }
    }

    private void registerInitialCards(final Dealer dealer, final Players players) {
        dealer.receiveInitialCards(dealer.distributeInitialCard());
        players.receiveInitialCards(dealer::distributeInitialCard);
        outputView.printDistributionSubject(players.getNames());
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

    private void playParticipantsTurn(final Players players, final Dealer dealer) {
        playPlayersTurn(players, dealer);
        playDealerTurn(dealer);
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
            player.draw(dealer.distributeCard());
            outputView.printNameAndCards(NameCards.from(player));
            playPlayerTurn(player, dealer);
        }
    }

    private void playDealerTurn(final Dealer dealer) {
        while (dealer.canHit()) {
            outputView.printDealerHit();
            dealer.draw(dealer.distributeCard());
        }
    }

    private void printFinalCardsAndScores(final Dealer dealer, final Players players) {
        outputView.println();
        NameCardsScore dealerNameCardsScore = new NameCardsScore(OutputView.DEALER_NAME, dealer.openCards(),
                dealer.notifyScore());
        List<NameCardsScore> playerNameCardsScore = players.collectFinalResults();
        outputView.printFinalCardsAndScore(dealerNameCardsScore);
        outputView.printFinalCardsAndScore(playerNameCardsScore);
    }

    private void printResultProfits(final Referee referee, final Players players, final Bets bets) {
        final Map<Player, ResultCommand> playerResultCommands = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResultCommands.put(player, referee.judgePlayerResult(player));
        }
        final Map<Player, Money> playerProfits = bets.calculatePlayersProfit(playerResultCommands);
        final List<NameProfit> nameProfits = NameProfit.createNameProfits(playerProfits);
        final Money dealerProfit = bets.calculateDealerProfit(playerResultCommands);
        outputView.printDealerProfit(dealerProfit.getMoney());
        outputView.printPlayersProfit(nameProfits);
    }
}
