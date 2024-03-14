package controller;

import domain.Answer;
import domain.BetAmount;
import domain.Bettings;
import domain.Result;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
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
        final Players players = Players.from(inputView.readNames());
        final Dealer dealer = Dealer.from(CardDeck.generate());

        final Bettings bettings = new Bettings();

        for (Player player : players.getPlayers()) {
            final BetAmount betAmount = BetAmount.from(inputView.readBetAmount(player.getName()));
            bettings.save(player, betAmount);
        }

        initHands(players, dealer);
        dealToPlayers(players, dealer);
        dealToDealerIfPossible(players, dealer);

        printFinalResult(bettings, players, dealer);
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

    private void printFinalResult(final Bettings bettings, final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.from(dealer, players));
        outputView.printGameResultMessage();

        final Map<Player, Result> playersResult = players.calculateResultBy(dealer);

        for (Entry<Player, Result> entry : playersResult.entrySet()) {
            BetAmount betAmount = bettings.calculateBy(entry);
            outputView.printGameResult(entry.getKey(), betAmount);
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
