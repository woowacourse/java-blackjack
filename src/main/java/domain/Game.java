package domain;

import domain.participant.Answer;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Game {

    private final Dealer dealer;
    private final Players players;

    public Game(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initHands(BiConsumer<DealerHandsDto, ParticipantsDto> handsPrinter) {
        dealer.initHands(players);
        handsPrinter.accept(DealerHandsDto.from(dealer), ParticipantsDto.of(players));
    }

    public boolean isAlreadyEnd() {
        return dealer.isBlackJack();
    }

    public void checkingPlayersBlackJack(Consumer<String> blackJackPrinter) {
        players.filter(Participant::isBlackJack)
                .forEach(player -> blackJackPrinter.accept(player.getName()));
    }

    public void dealWithPlayers(Function<String, Answer> answerReader,
                                Consumer<ParticipantDto> handsPrinter,
                                Consumer<Boolean> endMessagePrinter) {
        players.forEach(player -> deal(player, answerReader,
                handsPrinter,
                endMessagePrinter
        ));
    }

    public void deal(final Player player, Function<String, Answer> answerReader,
                     Consumer<ParticipantDto> handsPrinter,
                     Consumer<Boolean> endMessagePrinter) {
        if (player.isBlackJack()) {
            return;
        }
        boolean handsChanged = false;
        boolean turnEnded = false;

        while (!turnEnded) {
            final Answer answer = answerReader.apply(player.getName());
            dealer.deal(player, answer);
            printHandsIfRequired(player, handsChanged, answer, handsPrinter);
            handsChanged = true;
            turnEnded = isTurnEnded(player, answer, endMessagePrinter);
        }
    }

    public void dealerTurn(Runnable dealerTurnMessagePrinter) {
        if (players.isAllBust()) {
            return;
        }
        dealer.deal();
        for (int i = 0; i < dealer.countAddedHands(); i++) {
            dealerTurnMessagePrinter.run();
        }
    }

    private void printHandsIfRequired(final Player player, final boolean handsChanged, final Answer answer,
                                      Consumer<ParticipantDto> handsPrinter) {
        if (shouldShowHands(handsChanged, answer)) {
            handsPrinter.accept(ParticipantDto.from(player));
        }
    }

    private boolean isTurnEnded(final Player player, final Answer answer, Consumer<Boolean> endMessagePrinter) {
        if (player.canDeal()) {
            return !answer.isHit();
        }
        endMessagePrinter.accept(player.isBust());
        return true;
    }

    private boolean shouldShowHands(final boolean handsChanged, final Answer answer) {
        return answer.isHit() || !handsChanged;
    }
}
