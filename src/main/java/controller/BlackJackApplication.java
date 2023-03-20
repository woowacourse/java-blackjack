package controller;

import domain.BlackJack;
import domain.card.Deck;
import domain.card.Hand;
import domain.gameresult.GameResult;
import domain.player.*;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackApplication {
    public void startGame() {
        BlackJack blackJack = startBlackJack();

        drawAllPlayers(blackJack);
        OutputView.printPlayersFinalInformation(blackJack.getPlayers());

        compareScore(blackJack);
    }

    private static void compareScore(BlackJack blackJack) {
        GameResult gameResult = blackJack.compareScore();
        OutputView.printPlayersGameResults(gameResult);
    }

    private BlackJack startBlackJack() {
        BlackJack blackJack = new BlackJack(
                Players.from(
                        new Player(Hand.withEmptyHolder(), Name.dealerName(), Bet.from(10000)),
                        Player.from(getGamblerNameAndBets())
                ),
                Deck.create()
        );
        initializeBlackjackGame(blackJack);
        return blackJack;
    }

    private Map<Name, Bet> getGamblerNameAndBets() {
        Map<Name, Bet> nameAndBet = new LinkedHashMap<>();
        List<Name> gamblerNames = getGamblerNames();
        for (Name gamblerName : gamblerNames) {
            nameAndBet.put(gamblerName, getBet(gamblerName));
        }
        return nameAndBet;
    }

    private Bet getBet(Name gamblerName) {
        OutputView.printBetNameGuide(gamblerName.getName());
        return InputView.repeat(InputView::inputBet);
    }

    private List<Name> getGamblerNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }

    private void initializeBlackjackGame(BlackJack blackJack) {
        blackJack.initializeCardsOfPlayers();
        OutputView.printPlayersInformation(blackJack.getPlayers());
    }

    private void drawAllPlayers(BlackJack blackJack) {
        giveCardToParticipants(blackJack);
        if (!blackJack.shouldDealerGetCard()) {
            return;
        }
        blackJack.giveCardToDealer();
        OutputView.printGiveDealerCardMessage();
    }

    private void giveCardToParticipants(BlackJack blackJack) {
        List<PlayerReadOnly> participants = blackJack.getParticipants();
        for (PlayerReadOnly participant : participants) {
            giveCardToParticipant(blackJack, participant);
        }
    }

    private void giveCardToParticipant(BlackJack blackJack, PlayerReadOnly participant) {
        Command command = getCommand(participant.getName());
        if (command.isYes()) {
            blackJack.giveCard(participant);
            OutputView.printParticipantCardCondition(List.of(participant));
        }
        if (isStoppingGivingCardCondition(participant, command)) {
            stopGivingCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJack, participant);
    }

    private static boolean isStoppingGivingCardCondition(PlayerReadOnly participant, Command command) {
        return command.isNo() || participant.isBust();
    }

    private Command getCommand(String participantName) {
        OutputView.printAddCardGuide(participantName);
        return InputView.repeat(InputView::inputAddCardCommand);
    }

    private void stopGivingCard(PlayerReadOnly participant, Command command) {
        if (command.isNo()) {
            OutputView.printParticipantCardCondition(List.of(participant));
            return;
        }

        if (participant.isBust()) {
            OutputView.printBurstMessage(participant.getName());
        }
    }
}
