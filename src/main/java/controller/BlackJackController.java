package controller;

import domain.Answer;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
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

        initHands(players, dealer);
        dealToPlayers(players, dealer);
        dealToDealerIfPossible(players, dealer);

        printFinalResult(players, dealer);
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printInitHands(DealerHandsDto.from(dealer), ParticipantsDto.from(players));
    }

    private void dealToPlayers(final Players players, final Dealer dealer) {
        players.getPlayers().forEach(player -> deal(player, dealer));
    }

    private void dealToDealerIfPossible(Players players, Dealer dealer) {
        if (players.isAllBust()) {
            return;
        }

        dealer.deal();
        printDealerHandsChangedMessage(dealer.countAddedHands());
    }

    private void printFinalResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.from(dealer, players));
        outputView.printGameResult(dealer.calculateResultBy(players), players.calculateResultBy(dealer));
    }

    private void printDealerHandsChangedMessage(final int turn) {
        for (int i = 0; i < turn; i++) {
            outputView.printDealerHandsChangedMessage();
        }
    }

    private void deal(final Player player, final Dealer dealer) {
        boolean handsChanged = false;
        boolean turnEnded = false;

        while (!turnEnded) {
            final Answer answer = Answer.from(inputView.readAnswer(player.getName()));
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
        if (player.isBust()) {
            outputView.printBust();
            return true;
        }

        if (player.isBlackJack()) {
            outputView.printBlackJack();
            return true;
        }

        return !answer.isHit();
    }

    private boolean shouldShowHands(final boolean handsChanged, final Answer answer) {
        return answer.isHit() || !handsChanged;
    }
}
