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
import dto.hands.DealerHandsDto;
import dto.hands.ParticipantHandsDto;
import dto.hands.ParticipantsHandsDto;
import dto.profit.ParticipantProfitDto;
import dto.profit.ParticipantsProfitDto;
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
        final Dealer dealer = Dealer.from(CardDeck.generate());

        initHands(players, dealer);
        dealToPlayers(players, dealer);
        dealToDealer(players, dealer);

        printGameResult(players, dealer);
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

    private void initHands(final Players players, final Dealer dealer) {
        dealer.initHands(players);
        outputView.printInitHands(DealerHandsDto.from(dealer), ParticipantsHandsDto.from(players));
    }

    private void dealToPlayers(final Players players, final Dealer dealer) {
        players.getPlayers().forEach(player -> deal(player, dealer));
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
        printDealerOneMoreCardMessage(dealer.countAddedHands(), dealer.getName());
    }

    private void printGameResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsHandsDto.of(dealer, players));
        outputView.printProfitResult(ParticipantProfitDto.of(players, dealer),
                ParticipantsProfitDto.of(players, dealer));
    }

    private void printDealerOneMoreCardMessage(final int turn, final String name) {
        for (int i = 0; i < turn; i++) {
            outputView.printDealerOneMoreCardMessage(name);
        }
    }

    private void deal(final Player player, final Dealer dealer) {
        if (player.isBlackJack()) {
            outputView.printBlackJack(player);
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
            outputView.printHands(ParticipantHandsDto.from(player));
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
