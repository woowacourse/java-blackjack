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
        dealWithPlayers(players, dealer);

        if (!players.isAllBust()) {
            dealer.deal();
            printDealerTurnMessage(dealer.countAddedHands());
        }

        printFinalResult(players, dealer);
    }

    private void printDealerTurnMessage(final int turn) {
        for (int i = 0; i < turn; i++) {
            outputView.printDealerTurnMessage();
        }
    }

    private void dealWithPlayers(final Players players, final Dealer dealer) {
        for (Player player : players.getPlayers()) {
            deal(player, dealer);
        }
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printStartDeal(DealerHandsDto.from(dealer), ParticipantsDto.of(players));
    }

    private void printFinalResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.of(dealer, players));
        outputView.printGameResult(dealer.getDealerResult(players), players.getPlayersResult(dealer));
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
