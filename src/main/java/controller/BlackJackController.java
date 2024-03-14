package controller;

import domain.Answer;
import domain.BetAmount;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
import repository.BetAmountRepository;
import view.OutputView;

public class BlackJackController {

    private final InputController inputController;
    private final OutputView outputView;
    private final BetAmountRepository repository = new BetAmountRepository(); // TODO 리팩터링 필요


    public BlackJackController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        final Players players = inputController.getPlayers();
        final Dealer dealer = new Dealer();

        for (Player player : players.getPlayers()) {
            BetAmount betAmount = inputController.getBetAmount(player.getName());
            repository.put(player, betAmount);
        }

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
        if (dealer.isBlackJack()) {
            System.out.println("딜러가 블랙잭임");
            return;
        }
        for (Player player : players.getPlayers()) {
            if (player.isBlackJack()) {
                System.out.println("플레이어가 블랙잭임");
                continue;
            }
            deal(player, dealer);
        }
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printStartDeal(DealerHandsDto.from(dealer), ParticipantsDto.of(players));
    }

    private void printFinalResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.of(dealer, players));
        outputView.printGameResult(repository.calculateResult(dealer), repository.calculateDealerAmount());
    }

    private void deal(final Player player, final Dealer dealer) {
        boolean handsChanged = false;
        boolean turnEnded = false;

        while (!turnEnded) {
            final Answer answer = inputController.getAnswer(player.getName());
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
        outputView.printDealEndMessage(player.isBust());
        return true;
    }

    private boolean shouldShowHands(final boolean handsChanged, final Answer answer) {
        return answer.isHit() || !handsChanged;
    }
}
