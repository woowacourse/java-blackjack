package controller;

import domain.Amount;
import domain.Answer;
import domain.BetAmount;
import domain.participant.Dealer;
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
import repository.BetAmountRepository;
import view.OutputView;

public class BlackJackController {

    private final InputController inputController;
    private final OutputView outputView;
//    private final BetAmountRepository repository = new BetAmountRepository(); // TODO 리팩터링 필요


    public BlackJackController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        final Names names = inputController.getPlayers();
        final Dealer dealer = new Dealer();

        final List<Player> temp = new ArrayList<>();
        for (final Name name : names.getPlayerNames()) {
            BetAmount betAmount = inputController.getBetAmount(name.getValue());
            temp.add(new Player(name, betAmount));
//            repository.put(player, betAmount);
        }

        final Players players = new Players(temp);

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
            outputView.printDealerBlackJack();
            return;
        }
        for (Player player : players.getPlayers()) {
            if (player.isBlackJack()) {
                outputView.printBlackJack();
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
        Map<Player, Amount> finalResult = players.calculateResult(dealer);
        Amount dealerAmount = dealer.calculateRevenue(finalResult);
        outputView.printGameResult(finalResult, dealerAmount);
//        outputView.printGameResult(repository.calculateResult(dealer), repository.calculateDealerAmount());
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
