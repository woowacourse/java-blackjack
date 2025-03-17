package Blackjack.controller;

import Blackjack.domain.card.Cards;
import Blackjack.domain.game.CardDeck;
import Blackjack.domain.game.CardDeckGenerator;
import Blackjack.domain.game.Game;
import Blackjack.domain.game.GameResult;
import Blackjack.domain.game.Participants;
import Blackjack.dto.GameResultDto;
import Blackjack.dto.ParticipantCardsDto;
import Blackjack.exception.ExceptionHandler;
import Blackjack.view.InputView;
import Blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class GameController {


    public void run() {
        CardDeck cardDeck = CardDeckGenerator.generate();
        Participants participants = registerPlayers();
        Game game = new Game(cardDeck, participants);
        game.distributeInitialCards();
        displayInitialParticipantsCards(participants);
        distributePlayersExtraCard(game, participants);
        distributeDealerExtraCard(game);
        displayFinalParticipantsCards(participants);
//        displayGameResult(participants);
    }

    private Participants registerPlayers() {
        return ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = InputView.readPlayerNames();
            return new Participants(names);
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

    /*private void displayGameResult(Participants participants) {
        Map<String, GameResult> gameResults = participants.determineGameResult();
        List<GameResultDto> gameResultDtos = new ArrayList<>();
        gameResults.forEach((key, value) -> gameResultDtos.add(createGameResultDto(key, value)));
        OutputView.printGameResult(gameResultDtos);
    }*/

    private ParticipantCardsDto createParticipantCardsDto(String name, Cards cards) {
        return new ParticipantCardsDto(name, cards.getCards(), cards.calculateScore());
    }

    private GameResultDto createGameResultDto(String name, GameResult gameResult) {
        return new GameResultDto(name, gameResult.getGameResult());
    }
}
