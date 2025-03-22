package blackjack.controller;

import blackjack.domain.card.Cards;
import blackjack.domain.game.CardDeck;
import blackjack.domain.game.CardDeckGenerator;
import blackjack.domain.game.Game;
import blackjack.domain.game.Participants;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantCardsDto;
import blackjack.exception.ExceptionHandler;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class GameController {

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Players players = registerPlayers();
        players.initializeBet(InputView::readPlayerBet);
        Participants participants = new Participants(players);
        Game game = new Game(cardDeck, participants);
        game.distributeInitialCards();
        displayInitialParticipantsCards(participants);
        distributePlayersExtraCard(game, participants);
        distributeDealerExtraCard(game);
        displayFinalParticipantsCards(participants);
        displayProfitResult(participants);
    }

    private void displayProfitResult(final Participants participants) {
        int dealerProfit = participants.calculateDealerProfit();
        Map<String, Integer> playerProfits = participants.calculatePlayerProfits();
        OutputView.printProfitResult(dealerProfit, playerProfits);
    }

    private Players registerPlayers() {
        return ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = InputView.readPlayerNames();
            return Players.of(names);
        });
    }

    private void distributePlayersExtraCard(final Game game, final Participants participants) {
        List<String> playerNames = participants.getPlayerNames();
        for (String playerName : playerNames) {
            distributeExtraCardTo(playerName, participants, game);
        }
    }

    private void distributeExtraCardTo(final String playerName, final Participants participants, final Game game) {
        while (game.isAbleToAddCard(playerName) && InputView.readAddPlayerCard(playerName)) {
            game.distributeExtraCardTo(playerName);
            Cards cards = participants.getCardsOf(playerName);
            OutputView.printParticipantCards(createParticipantCardsDto(playerName, cards));
        }
    }

    private void distributeDealerExtraCard(Game game) {
        boolean isAdded = game.isAddedExtraCardToDealer();
        OutputView.printDealerExtraCard(isAdded);
    }

    private void displayInitialParticipantsCards(Participants participants) {
        displayParticipantsCards(participants.getInitialCardsOfAll(), OutputView::printInitialParticipantsCards);
    }

    private void displayFinalParticipantsCards(Participants participants) {
        displayParticipantsCards(participants.getCardsOfAll(), OutputView::printFinalParticipantsCards);
    }

    private void displayParticipantsCards(Map<String, Cards> cardsOfAll, Consumer<List<ParticipantCardsDto>> printer) {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        for (Entry<String, Cards> entry : cardsOfAll.entrySet()) {
            participantCardsDtos.add(createParticipantCardsDto(entry.getKey(), entry.getValue()));
        }
        printer.accept(participantCardsDtos);
    }

    private ParticipantCardsDto createParticipantCardsDto(String name, Cards cards) {
        return new ParticipantCardsDto(name, cards.getCards(), cards.calculateScore());
    }
}
