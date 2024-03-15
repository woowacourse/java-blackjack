package controller;

import domain.Answer;
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
        dealToDealerIfPossible(players, dealer);

        printHandsResult(players, dealer);
        printFinalProfit(players, dealer);
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

    private Names readNames() {
        return Names.from(inputView.readNames());
    }

    private BetAmount readBetAmountBy(final Name name) {
        return BetAmount.from(inputView.readBetAmount(name.getValue()));
    }

    private Dealer createDealer() {
        return Dealer.from(CardDeck.generate());
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printInitHands(DealerHandsDto.from(dealer), ParticipantsDto.from(players));
    }

    private void dealToPlayers(final Players players, final Dealer dealer) {
        for (Player player : players.getPlayers()) {
            if (player.isBlackJack()) {
                outputView.printBlackJack();
                return;
            }

            deal(player, dealer);
        }
    }

    private void dealToDealerIfPossible(Players players, Dealer dealer) {
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

    private void printFinalProfit(final Players players, final Dealer dealer) {
        for (Player player : players.getPlayers()) {
            final BetAmount betAmount = player.calculateProfitBy(dealer);
            outputView.printGameResult(player, betAmount);
        }
    }

    private void printDealerHandsChangedMessage(final int turn, final String name) {
        for (int i = 0; i < turn; i++) {
            outputView.printDealerHandsChangedMessage(name);
        }
    }

    private void deal(final Player player, final Dealer dealer) {
        boolean handsChanged = false;
        boolean turnEnded = false;

        while (!turnEnded) {
            final Answer answer = inputView.readAnswer(player.getName());
            dealer.deal(player, answer);

            printHandsIfRequired(player, handsChanged, answer);

            handsChanged = true;
            turnEnded = isTurnEnded(player, answer);
        }
    }

    private void printHandsIfRequired(final Player player, final boolean handsChanged, final Answer answer) {
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
