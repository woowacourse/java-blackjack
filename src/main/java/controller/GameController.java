package controller;

import domain.CardDeck;
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
import view.InputView;
import view.OutputView;

public class GameController {

    private final Game game;
    private final CardDeck cardDeck;

    public GameController() {
        this.game = new Game();
        this.cardDeck = new CardDeck();
    }

    public void run() {
        registerGamePlayers();
        distributeGameInitialCards();
        distributeParticipantExtraCards();
        determineParticipantFinalCards();
        determineGameResult();
    }

    private void registerGamePlayers() {
        ExceptionHandler.repeatUntilSuccess(() ->{
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
            game.addExtraCard(player, cardDeck);
        }
        ParticipantCardsDto participantCardsDto = createParticipantCardsDto(player);
        OutputView.printParticipantCards(participantCardsDto);
    }

    private void distributeDealerExtraCard() {
        Dealer dealer = game.getDealer();
        boolean dealerExtraCard = dealer.ableToAddCard();
        if (dealerExtraCard) {
            game.addExtraCard(dealer, cardDeck);
        }
        ParticipantCardsDto dealerCardsDto = createParticipantCardsDto(dealer);
        OutputView.printDealerExtraCard(dealerCardsDto, dealerExtraCard);
    }

    private void determineParticipantFinalCards() {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        participantCardsDtos.add(createParticipantCardsDto(game.getDealer()));
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            participantCardsDtos.add(createParticipantCardsDto(player));
        }
        OutputView.printParticipantsFinalCards(participantCardsDtos);
    }

    private void determineGameResult() {
        GameResult dealerGameResult = game.determineDealerGameResult();
        GameResultDto dealerGameResultDto = createGameResultDto(dealerGameResult);

        List<GameResult> playerGameResults = game.determinePlayerGameResults();
        List<GameResultDto> playerGameResultDtos = new ArrayList<>();
        for (GameResult playerGameResult : playerGameResults) {
            playerGameResultDtos.add(createGameResultDto(playerGameResult));
        }
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
        return new ParticipantCardsDto(participant.getName(), participant.getInitialCards(), participant.getCardsScore());
    }

    private ParticipantCardsDto createParticipantCardsDto(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getCards(), participant.getCardsScore());
    }

    private GameResultDto createGameResultDto(GameResult gameResult) {
        return new GameResultDto(gameResult.getName(), gameResult.getGameResult());
    }
}
