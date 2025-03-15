import domain.CardDeck;
import domain.GameResult;
import domain.card.Card;
import domain.dto.GameResultDto;
import domain.dto.ParticipantCardsDto;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Participant;
import domain.participant.Player;
import exception.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Game {

    private final GameParticipant gameParticipant;
    private final CardDeck cardDeck;

    public Game() {
        this.gameParticipant = new GameParticipant();
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
        ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = InputView.readPlayerNames();
            gameParticipant.registerPlayers(names);
        });
    }

    private void distributeGameInitialCards() {
        List<List<Card>> cardsStack = cardDeck.pickInitialCardsStack(gameParticipant.countParticipants());
        gameParticipant.distributeInitialCards(cardsStack);
        displayDistributedGameInitialCards();
    }

    private void distributeParticipantExtraCards() {
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            distributePlayerExtraCards(player);
        }
        distributeDealerExtraCard();
    }

    private void distributePlayerExtraCards(Player player) {
        while (player.ableToAddCard() && InputView.readAddPlayerCard(player.getName())) {
            gameParticipant.addExtraCard(player, cardDeck);
        }
        ParticipantCardsDto participantCardsDto = createParticipantCardsDto(player);
        OutputView.printParticipantCards(participantCardsDto);
    }

    private void distributeDealerExtraCard() {
        Dealer dealer = gameParticipant.getDealer();
        boolean dealerExtraCard = dealer.ableToAddCard();
        if (dealerExtraCard) {
            gameParticipant.addExtraCard(dealer, cardDeck);
        }
        ParticipantCardsDto dealerCardsDto = createParticipantCardsDto(dealer);
        OutputView.printDealerExtraCard(dealerCardsDto, dealerExtraCard);
    }

    private void determineParticipantFinalCards() {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        participantCardsDtos.add(createParticipantCardsDto(gameParticipant.getDealer()));
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            participantCardsDtos.add(createParticipantCardsDto(player));
        }
        OutputView.printParticipantsFinalCards(participantCardsDtos);
    }

    private void determineGameResult() {
        GameResult dealerGameResult = gameParticipant.determineDealerGameResult();
        GameResultDto dealerGameResultDto = createGameResultDto(dealerGameResult);

        List<GameResult> playerGameResults = gameParticipant.determinePlayerGameResults();
        List<GameResultDto> playerGameResultDtos = new ArrayList<>();
        for (GameResult playerGameResult : playerGameResults) {
            playerGameResultDtos.add(createGameResultDto(playerGameResult));
        }
        OutputView.printFinalGameResult(dealerGameResultDto, playerGameResultDtos);
    }

    private void displayDistributedGameInitialCards() {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
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

    private GameResultDto createGameResultDto(GameResult gameResult) {
        return new GameResultDto(gameResult.getName(), gameResult.getGameResult());
    }
}
