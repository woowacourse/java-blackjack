package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Judge;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.PlayerInfo;
import blackjack.strategy.RandomShuffleStrategy;
import blackjack.view.CardView;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackGame {

    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
    }

    public void start() {
        Dealer dealer = new Dealer(new Deck(new RandomShuffleStrategy()));
        Players players = createPlayers();

        initializeGame(dealer, players);
        printCardDistribute(dealer, players);
        requestExtraCard(dealer, players);
        outputView.printTotalProfit(Judge.judge(dealer, players));
    }

    private Players createPlayers() {
        List<PlayerInfo> playerInfos = inputView.readPlayerInfos();
        return Players.of(playerInfos);
    }

    private void initializeGame(final Dealer dealer, final Players players) {
        dealer.draw(BLACKJACK_INIT_CARD_AMOUNT);

        IntStream.range(0, BLACKJACK_INIT_CARD_AMOUNT)
                .forEach(i -> players.getPlayers()
                        .forEach(player -> player.draw(dealer.draw())));
    }

    private void printCardDistribute(final Dealer dealer, final Players players) {
        outputView.printCardDistribute(players.getNames());
        printHandCardsStatus(dealer, players);
    }

    private void printHandCardsStatus(final Dealer dealer, final Players players) {
        outputView.printHandCards(CardView.makeCardOutput(dealer.showFirstCard()));

        for (Player player : players.getPlayers()) {
            outputView.printHandCards(player.getName(), CardView.makeCardOutput(player.getHandCards()));
        }
        outputView.printNewLine();
    }

    private void requestExtraCard(final Dealer dealer, final Players players) {
        players.getPlayers()
                .forEach(player -> readMoreCardChoice(player, dealer));

        addDealerExtraCard(dealer);
        printResultCardsStatus(dealer, players);
    }

    private void addDealerExtraCard(final Dealer dealer) {
        if (dealer.requestExtraCard()) {
            outputView.printAddDealerCard();
        }
    }

    private void printResultCardsStatus(final Dealer dealer, final Players players) {
        outputView.printCardResultStatus(CardView.makeCardOutput(dealer.getHandCards()), dealer.getScore());

        for (Player player : players.getPlayers()) {
            outputView.printCardResultStatus(
                    player.getName(), CardView.makeCardOutput(player.getHandCards()), player.getScore()
            );
        }
    }

    private void readMoreCardChoice(final Player player, final Dealer dealer) {
        String choice = inputView.readMoreCardChoice(player.getName());

        if (!outputView.isMoreChoice(choice)) {
            outputView.printHandCards(player.getName(), CardView.makeCardOutput(player.getHandCards()));
            return;
        }

        do {
            player.draw(dealer.draw());
            outputView.printHandCards(player.getName(), CardView.makeCardOutput(player.getHandCards()));
        } while ((player.canReceiveCard()) && outputView.isMoreChoice(inputView.readMoreCardChoice(player.getName())));
    }
}
