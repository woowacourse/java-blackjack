package controller;

import domain.BlackJack;
import domain.card.Hand;
import domain.card.DeckOfCards;
import domain.gameresult.GameResultReadOnly;
import domain.player.*;
import domain.strategy.RandomBasedIndexGenerator;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackApplication {
    public void startGame() {
        BlackJack blackJack = new BlackJack(
                new Players(new Dealer(Hand.makeEmptyHolder()), Gambler.from(getGamblerNameAndBets())),
                DeckOfCards.create(new RandomBasedIndexGenerator())
        );
        initializeBlackjackGame(blackJack);

        giveCardToPlayers(blackJack);
        OutputView.printPlayersFinalInformation(blackJack.getPlayers());
        GameResultReadOnly gameResult = blackJack.battle();
        OutputView.printPlayersGameResults(gameResult);
    }

    private Map<Name, Bet> getGamblerNameAndBets() {
        Map<Name, Bet> nameAndBet = new HashMap<>();
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

    private void giveCardToPlayers(BlackJack blackJack) {
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
