package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.strategy.RandomShuffleStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.RankView;
import blackjack.view.SuitView;
import java.util.List;

public class BlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame() {
        this.inputView = InputView.getInstance();
        this.outputView = OutputView.getInstance();
    }

    public void start() {
        Deck deck = new Deck(new RandomShuffleStrategy());

        List<String> names = inputView.readPlayersName();
        Dealer dealer = new Dealer(deck);
        Players players = Players.of(names, dealer);

        printCardDistribute(names, players, dealer);
        extraCardRequest(dealer, players);
        outputView.printFinalResult(names, players.createResult(dealer));
    }

    private void printCardDistribute(final List<String> names, final Players players, final Dealer dealer) {
        outputView.printCardDistribute(names);
        printHandCardsStatus(dealer, players);
    }

    private void printHandCardsStatus(final Dealer dealer, final Players players) {
        outputView.printHandCards(makeCardOutput(dealer.showFirstCard()));

        for (Player player : players.getPlayers()) {
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
        }
        outputView.printNewLine();
    }

    private void extraCardRequest(final Dealer dealer, final Players players) {
        players.getPlayers()
                .forEach(player -> readMoreCardChoice(player, dealer));

        printDealerExtraCard(dealer);
        printResultCardsStatus(dealer, players);
    }

    private void printDealerExtraCard(final Dealer dealer) {
        if (dealer.extraCard()) {
            outputView.printAddDealerCard();
            return;
        }
        outputView.printNewLine();
    }

    private void printResultCardsStatus(final Dealer dealer, final Players players) {
        outputView.printCardResultStatus(makeCardOutput(dealer.getHandCards()), dealer.getScore());

        for (Player player : players.getPlayers()) {
            outputView.printCardResultStatus(
                    player.getName(),
                    makeCardOutput(player.getHandCards()),
                    player.getScore()
            );
        }
    }

    private void readMoreCardChoice(final Player player, final Dealer dealer) {
        String choice = inputView.readMoreCardChoice(player.getName());

        if (!outputView.isMoreChoice(choice)) {
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
            return;
        }

        do {
            player.draw(dealer);
            outputView.printHandCards(player.getName(), makeCardOutput(player.getHandCards()));
        } while ((player.canReceiveCard()) && outputView.isMoreChoice(inputView.readMoreCardChoice(player.getName())));
    }

    private List<String> makeCardOutput(final List<Card> cards) {
        return cards.stream()
                .map(this::makeCardOutput)
                .toList();
    }

    private String makeCardOutput(final Card card) {
        return RankView.toSymbol(card.getRank()) + SuitView.toSuitView(card.getSuit());
    }
}
