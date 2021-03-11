package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;
import blackjack.util.GameInitializer;
import blackjack.view.ErrorView;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public static void play() {
        Deck deck = GameInitializer.initializeDeck();
        Players players = initializePlayers(deck);
        Dealer dealer = GameInitializer.initializeDealer(deck);
        List<BlackJackParticipant> participants = getBlackJackParticipants(players, dealer);
        OutputView.printGameInitializeMessage(participants, GameInitializer.STARTING_CARD_COUNT);

        playersDrawStage(deck, players);
        dealerDrawStage(deck, dealer);
        showGameResult(players, dealer, participants);
    }

    private static Players initializePlayers(Deck deck) {
        try {
            OutputView.printInputNames();
            List<Player> players = createPlayersByNameAndMoney(InputView.inputStringAndSplitByComma());
            return GameInitializer.initializePlayers(players, deck);
        } catch (InvalidNameInputException e) {
            ErrorView.printErrorMessage(e.getMessage());
            return initializePlayers(deck);
        }
    }

    private static List<Player> createPlayersByNameAndMoney(List<String> nameInputs) {
        List<Player> players = new ArrayList<>();
        for (String name : nameInputs) {
            OutputView.printInputMoney(name.trim());
            players.add(new Player(name, InputView.inputString()));
        }
        return players;
    }

    private static List<BlackJackParticipant> getBlackJackParticipants(Players players, Dealer dealer) {
        List<BlackJackParticipant> participants = new ArrayList<>(Collections.singletonList(dealer));
        participants.addAll(players.unwrap());
        return participants;
    }

    private static void playersDrawStage(Deck deck, Players players) {
        for (Player player : players.unwrap()) {
            playerDrawStage(deck, player);
        }
    }

    private static void playerDrawStage(Deck deck, Player player) {
        while (player.isContinue()) {
            OutputView.willDrawCard(player);
            drawOrStop(deck, player);
        }
    }

    private static void drawOrStop(Deck deck, Player player) {
        if (player.willContinue(InputView.inputString())) {
            player.draw(deck.draw());
            OutputView.printParticipantStatus(player);
        }
    }

    private static void dealerDrawStage(Deck deck, Dealer dealer) {
        while (dealer.isContinue()) {
            dealer.draw(deck.draw());
            OutputView.printDealerDrawCard(dealer);
        }
    }

    private static void showGameResult(Players players, Dealer dealer, List<BlackJackParticipant> participants) {
        OutputView.printParticipantsStatusWithScore(participants);
        OutputView.printResult(players.match(dealer));
    }
}

