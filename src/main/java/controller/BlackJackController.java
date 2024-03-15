package controller;

import domain.Answer;
import domain.Profit;
import domain.card.CardDeck;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Hands;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = createPlayers(readNames());
        final Dealer dealer = createDealer();

        initHands(players, dealer);
        dealToPlayers(players, dealer);
        dealToDealer(players, dealer);

        printHandsResult(players, dealer);
        printProfits(players, dealer);
    }

    private Players createPlayers(final Names names) {
        final List<Player> players = new ArrayList<>();

        for (Name name : names.getNames()) {
            final Hands hands = Hands.createEmptyHands();
            final BetAmount betAmount = readBetAmountBy(name);
            players.add(new Player(name, hands, betAmount));
        }

        return new Players(players);
    }

    private Dealer createDealer() {
        return Dealer.from(CardDeck.generate());
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printInitHands(DealerHandsDto.from(dealer), ParticipantsDto.from(players));
    }

    private void dealToPlayers(final Players players, final Dealer dealer) {
        players.getPlayers().forEach(player -> deal(player, dealer));
    }

    private void printProfits(final Players players, final Dealer dealer) {
        printDealerProfit(players, dealer);
        printPlayersProfits(players, dealer);
    }

    private Names readNames() {
        return Names.from(inputView.readNames());
    }

    private BetAmount readBetAmountBy(final Name name) {
        return new BetAmount(inputView.readBetAmount(name.getValue()));
    }

    private void dealToDealer(final Players players, final Dealer dealer) {
        if (players.isAllBust()) {
            return;
        }

        dealer.deal();
        printDealerHandsChangedMessage(dealer.countAddedHands(), dealer.getName());
    }

    private void printHandsResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.from(dealer, players));
        outputView.printGameResultMessage();
    }

    private void printDealerProfit(final Players players, final Dealer dealer) {
        final Profit profit = dealer.calculateProfitBy(players);
        outputView.printGameResult(dealer, profit);
    }

    private void printPlayersProfits(final Players players, final Dealer dealer) {
        final Map<Player, Profit> profits = players.calculateProfits(dealer);
        for (Entry<Player, Profit> playerProfit : profits.entrySet()) {
            outputView.printGameResult(playerProfit.getKey(), playerProfit.getValue());
        }
    }

    private void printDealerHandsChangedMessage(final int turn, final String name) {
        for (int i = 0; i < turn; i++) {
            outputView.printDealerHandsChangedMessage(name);
        }
    }

    private void deal(final Player player, final Dealer dealer) {
        if (player.isBlackJack()) {
            outputView.printBlackJack();
            return;
        }

        boolean handsChanged = false;
        boolean turnEnded = false;

        while (!turnEnded) {
            final Answer answer = inputView.readAnswer(player.getName());
            dealer.deal(player, answer);

            printHands(player, handsChanged, answer);

            handsChanged = true;
            turnEnded = isTurnEnded(player, answer);
        }
    }

    private void printHands(final Player player, final boolean handsChanged, final Answer answer) {
        if (shouldShowHands(handsChanged, answer)) {
            outputView.printHands(ParticipantDto.from(player));
        }
    }

    private boolean isTurnEnded(final Player player, final Answer answer) {
        if (player.canDeal()) {
            return !answer.isHit();
        }

        outputView.printDealerEndMessage(player.isBust());
        return true;
    }

    private boolean shouldShowHands(final boolean handsChanged, final Answer answer) {
        return answer.isHit() || !handsChanged;
    }
}
