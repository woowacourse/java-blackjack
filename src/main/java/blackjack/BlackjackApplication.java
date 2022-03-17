package blackjack;

import static blackjack.view.InputView.requestDrawCommand;
import static blackjack.view.OutputView.printDealerDrawMessage;
import static blackjack.view.OutputView.printInitGameMessage;
import static blackjack.view.OutputView.printOpenCard;
import static blackjack.view.OutputView.printPlayerCards;
import static blackjack.view.OutputView.printPlayersResult;
import static blackjack.view.OutputView.printResult;

import blackjack.domain.BettingAmount;
import blackjack.domain.BlackjackGame;
import blackjack.domain.Deck;
import blackjack.domain.Name;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Participants;
import blackjack.domain.ScoreResult;
import blackjack.domain.Selection;
import blackjack.dto.ParticipantDto;
import blackjack.view.InputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackApplication {

    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(requestPlayers(), new Deck());

        startSetting(blackjackGame);
        takeTurns(blackjackGame);
        endGame(blackjackGame);
    }

    private static void startSetting(BlackjackGame blackjackGame) {
        blackjackGame.drawStartingCard();

        List<ParticipantDto> participantDtos = toDto(blackjackGame.getPlayers());
        ParticipantDto dealerDto = toDto(blackjackGame.getDealer());
        printInitGameMessage(participantDtos, dealerDto);
        printOpenCard(participantDtos, dealerDto);
    }

    private static void takeTurns(BlackjackGame blackjackGame) {
        takeTurnPlayers(blackjackGame);
        takeTurnDealer(blackjackGame);
    }

    private static void takeTurnPlayers(BlackjackGame blackjackGame) {
        while (!blackjackGame.isEndAllPlayersTurn()) {
            Selection selection = Selection.from(requestDrawCommand(blackjackGame.getNowTurnPlayerName()));
            takeTurn(blackjackGame, selection);
        }
    }

    private static void takeTurn(BlackjackGame blackjackGame, Selection selection) {
        if (selection == Selection.NO) {
            printPlayerCards(toDto(blackjackGame.getNowTurnPlayer()));
            blackjackGame.proceedTurn();
            return;
        }
        blackjackGame.drawPlayerCard();
        printPlayerCards(toDto(blackjackGame.getNowTurnPlayer()));
        if (blackjackGame.isBustCurrentTurn()) {
            blackjackGame.proceedTurn();
        }
    }

    private static void endGame(BlackjackGame blackjackGame) {
        printPlayersResult(toDto(blackjackGame.getPlayers()), toDto(blackjackGame.getDealer()));
        ScoreResult result = blackjackGame.makeResults();
        printResult(result);
    }

    private static void takeTurnDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.checkDealerDrawable()) {
            printDealerDrawMessage();
            blackjackGame.drawDealerCard();
        }
    }

    private static ParticipantDto toDto(Participant player) {
        return ParticipantDto.from(player);
    }

    private static List<ParticipantDto> toDto(Participants participants) {
        return participants.getValue()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
    }

    private static Participants requestPlayers() {
        List<Name> names = requestNames();
        List<Participant> players = new ArrayList<>();

        for (Name name : names) {
            BettingAmount bettingAmount = requestBettingAmount(name.getValue());
            players.add(new Player(name, bettingAmount));
        }
        return new Participants(players);
    }

    private static BettingAmount requestBettingAmount(String name) {
        try {
            return new BettingAmount(InputView.requestBattingAmount(name));
        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자를 입력해주세요.");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }
        return requestBettingAmount(name);
    }

    private static List<Name> requestNames() {
        List<String> inputNames = InputView.requestNames();

        try {
            return inputNames.stream()
                    .map(String::trim)
                    .map(Name::new)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return requestNames();
        }
    }
}
