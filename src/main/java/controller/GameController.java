package controller;

import domain.CardDeck;
import domain.CardDeckGenerator;
import domain.Dealer;
import domain.Game;
import domain.GameResult;
import domain.Participant;
import domain.Player;
import domain.card.Card;
import domain.dto.GameResultDto;
import domain.dto.ParticipantCardsDto;
import java.util.ArrayList;
import java.util.List;
import exception.ExceptionHandler;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;

public class GameController {

    private final Game game;
    private final CardDeck cardDeck;

    public GameController() {
        this.game = new Game();
        this.cardDeck = CardDeckGenerator.generate();
    }

    public void run() {
        registerGamePlayers();
        distributeGameInitialCards();
        distributeParticipantExtraCards();
        determineFinalParticipantCards();
        determineGameResult();
    }

    private void registerGamePlayers() {
        ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = InputView.readPlayerNames();
            game.registerPlayers(names);
        });
    }

    private void distributeGameInitialCards() {
        List<List<Card>> cardsStack = cardDeck.pickInitialCardsStack(game.countParticipants());
        game.distributeInitialCards(cardsStack);
        displayDistributedGameInitialCards();
    }

    private void distributeParticipantExtraCards() {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            distributePlayerExtraCards(player);
        }
        distributeDealerExtraCard();
    }

    private void distributePlayerExtraCards(Player player) {
        while (player.ableToAddCard() && InputView.readAddPlayerCard(player.getName())) {
            Card card = cardDeck.pickCard();
            player.addCard(card);
        }
        ParticipantCardsDto participantCardsDto = createParticipantCardsDto(player);
        OutputView.printParticipantCards(participantCardsDto);
    }

    private void distributeDealerExtraCard() {
        Dealer dealer = game.getDealer();
        boolean dealerExtraCard = dealer.ableToAddCard();
        if (dealerExtraCard) {
            dealer.addCard(cardDeck.pickCard());
        }
        ParticipantCardsDto dealerCardsDto = createParticipantCardsDto(dealer);
        OutputView.printDealerExtraCard(dealerCardsDto, dealerExtraCard);
    }

    private void determineFinalParticipantCards() {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        participantCardsDtos.add(createParticipantCardsDto(game.getDealer()));
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            participantCardsDtos.add(createParticipantCardsDto(player));
        }
        OutputView.printFinalParticipantsCards(participantCardsDtos);
    }

    private void determineGameResult() {
        Map<Participant, GameResult> dealerGameResult = game.determineDealerGameResult();
        Entry<Participant, GameResult> dealerEntry = dealerGameResult.entrySet().iterator().next();
        GameResultDto dealerGameResultDto = createGameResultDto(dealerEntry.getKey(), dealerEntry.getValue());

        Map<Participant, GameResult> playerGameResults = game.determinePlayersGameResult();
        List<GameResultDto> playerGameResultDtos = new ArrayList<>();
        playerGameResults.forEach((key, value) -> playerGameResultDtos.add(createGameResultDto(key, value)));
        OutputView.printFinalGameResult(dealerGameResultDto, playerGameResultDtos);
    }

    private void displayDistributedGameInitialCards() {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();
        List<ParticipantCardsDto> playerCardsDtos = new ArrayList<>();
        for (Player player : players) {
            playerCardsDtos.add(createParticipantInitialCardsDto(player));
        }
        ParticipantCardsDto dealerCardsDto = createParticipantInitialCardsDto(dealer);
        OutputView.printParticipantInitialCards(dealerCardsDto, playerCardsDtos);
    }

    private ParticipantCardsDto createParticipantInitialCardsDto(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getInitialCards(),
                participant.getCardsScore());
    }

    private ParticipantCardsDto createParticipantCardsDto(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getCards(), participant.getCardsScore());
    }

    private GameResultDto createGameResultDto(Participant participant, GameResult gameResult) {
        return new GameResultDto(participant.getName(), gameResult.getGameResult());
    }
}
