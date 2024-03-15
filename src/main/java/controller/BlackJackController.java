package controller;

import domain.amount.Amount;
import domain.participant.Answer;
import domain.amount.BetAmount;
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
import view.OutputView;

//TODO 컨트롤러가 하는 일이 좀 많은듯...?
// GAME이라는 객체를 만들어 보는 것은 어떨까?
public class BlackJackController {

    private final InputController inputController;
    private final OutputView outputView;

    public BlackJackController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        final Names names = inputController.getNames();
        final Dealer dealer = new Dealer();

        final List<Player> playerList = new ArrayList<>();
        for (final Name name : names.getPlayerNames()) {
            final BetAmount betAmount = inputController.getBetAmount(name.getValue());
            playerList.add(new Player(name, betAmount));
        }

        final Players players = new Players(playerList);

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
        players.getPlayers()
                .forEach(player -> deal(player, dealer));
    }

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printStartDeal(DealerHandsDto.from(dealer), ParticipantsDto.of(players));
    }

    private void printFinalResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.of(dealer, players));
        final Map<Player, Amount> finalResult = players.calculateResult(dealer);
        final Amount dealerAmount = dealer.calculateRevenue(finalResult);
        outputView.printGameResult(finalResult, dealerAmount);
    }

    private void deal(final Player player, final Dealer dealer) {
        if (isBlackJack(player)) {
            return;
        }
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

    private boolean isBlackJack(final Player player) {
        if (player.isBlackJack()) {
            outputView.printBlackJack(player.getName());
            return true;
        }
        return false;
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
        outputView.printPlayerEndMessage(player.isBust());
        return true;
    }

    private boolean shouldShowHands(final boolean handsChanged, final Answer answer) {
        return answer.isHit() || !handsChanged;
    }
}
