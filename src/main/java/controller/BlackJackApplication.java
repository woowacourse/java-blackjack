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
        blackJack.giveCard(Name.DEALER_NAME);
        OutputView.printGiveDealerCardMessage();
    }

    private void giveCardToParticipants(BlackJack blackJack) {
        List<PlayerReadOnly> gamblers = blackJack.getGamblers();
        for (PlayerReadOnly gambler : gamblers) {
            giveCardToParticipant(blackJack, gambler);
        }
    }

    private void giveCardToParticipant(BlackJack blackJack, PlayerReadOnly gambler) {
        Command command = getCommand(gambler.getName());
        if (command.isYes()) {
            blackJack.giveCard(gambler.getName());
            OutputView.printParticipantCardCondition(List.of(gambler));
        }
        if (isStoppingGivingCardCondition(gambler, command)) {
            stopGivingCard(gambler, command);
            return;
        }
        giveCardToParticipant(blackJack, gambler);
    }

    private static boolean isStoppingGivingCardCondition(PlayerReadOnly gambler, Command command) {
        return command.isNo() || gambler.isBust();
    }

    private Command getCommand(String gamblerName) {
        OutputView.printAddCardGuide(gamblerName);
        return InputView.repeat(InputView::inputAddCardCommand);
    }

    private void stopGivingCard(PlayerReadOnly gambler, Command command) {
        if (command.isNo()) {
            OutputView.printParticipantCardCondition(List.of(gambler));
            return;
        }

        if (gambler.isBust()) {
            OutputView.printBurstMessage(gambler.getName());
        }
    }
}
