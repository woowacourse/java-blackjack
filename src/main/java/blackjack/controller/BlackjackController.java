package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.player.Participant;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.ShowDeckDto;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public static void start() {
        Game game = Game.of(InputView.informations());
        printStartGame(game);
        if (game.isAnyParticipantBlackjack()) {
            printResult(game);
            return;
        }
        drawCardToParticipants(game);
        drawCardToDealer(game);
        printResult(game);
    }

    private static void printStartGame(Game game) {
        List<ShowDeckDto> participants = game.participantsAsList()
            .stream()
            .map(ShowDeckDto::new)
            .collect(Collectors.toList());
        OutputView.drawTwoCards(new ShowDeckDto(game.dealer()), participants);
    }

    private static void printResult(Game game) {
        List<ShowDeckDto> showDeckDtos = game
            .allPlayers()
            .stream()
            .map(ShowDeckDto::new)
            .collect(Collectors.toList());
        OutputView.printFinalCardForm(showDeckDtos);
        OutputView.printMoneyResult(game.result().winningMoneyResult());
    }

    private static void drawCardToParticipants(Game game) {
        for (Participant participant : game.participantsAsList()) {
            drawCardToParticipant(game, participant);
        }
    }

    private static void drawCardToParticipant(Game game, Participant participant) {
        while (participant.drawable() && InputView.drawable(participant.name())) {
            game.drawCardTo(participant);
            OutputView.printCurrentDeck(new ShowDeckDto(participant));
        }
    }

    private static void drawCardToDealer(Game game) {
        if (game.drawableDealer()) {
            OutputView.printDealerDrawCard();
            game.drawCardToDealer();
        }
    }


}
