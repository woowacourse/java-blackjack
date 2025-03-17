import domain.CardDeck;
import domain.GameStatus;
import domain.card.Card;
import domain.dto.GameResultDto;
import domain.dto.ParticipantCardsDto;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Participant;
import domain.participant.Player;
import exception.ExceptionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        GameParticipant gameParticipant = new GameParticipant();
        CardDeck cardDeck = new CardDeck();
        
        registerGamePlayers(gameParticipant);
        Map<String, Integer> bettings = registerParticipantBettings(gameParticipant);
        distributeGameInitialCards(gameParticipant, cardDeck);
        distributeParticipantExtraCards(gameParticipant, cardDeck);
        determineParticipantFinalCards(gameParticipant);
        determineGameResult(gameParticipant, bettings);
    }

    private void registerGamePlayers(GameParticipant gameParticipant) {
        ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = inputView.readPlayerNames();
            gameParticipant.registerPlayers(names);
        });
    }

    private Map<String, Integer> registerParticipantBettings(GameParticipant gameParticipant) {
        Map<String, Integer> bettings = new HashMap<>();
        Dealer dealer = gameParticipant.getDealer();
        bettings.put(dealer.getName(), 0);
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            registerPlayerBettingMoney(bettings, player);
        }
        return bettings;
    }

    private void registerPlayerBettingMoney(Map<String, Integer> bettings, Player player) {
        ExceptionHandler.repeatUntilSuccess(() -> {
            String playerName = player.getName();
            int bettingMoney = inputView.readPlayerBettingMoney(playerName);
            bettings.put(playerName, bettingMoney);
        });
    }

    private void distributeGameInitialCards(GameParticipant gameParticipant, CardDeck cardDeck) {
        List<List<Card>> cardsStack = cardDeck.pickInitialCardsStack(gameParticipant.countParticipants());
        gameParticipant.distributeInitialCards(cardsStack);
        displayDistributedGameInitialCards(gameParticipant);
    }

    private void distributeParticipantExtraCards(GameParticipant gameParticipant, CardDeck cardDeck) {
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            distributePlayerExtraCards(gameParticipant, cardDeck, player);
        }
        distributeDealerExtraCard(gameParticipant, cardDeck);
    }

    private void distributePlayerExtraCards(GameParticipant gameParticipant, CardDeck cardDeck, Player player) {
        while (player.ableToAddCard() && inputView.readAddPlayerCard(player.getName())) {
            gameParticipant.addExtraCard(player, cardDeck);
            ParticipantCardsDto participantCardsDto = createParticipantCardsDto(player);
            outputView.printParticipantCards(participantCardsDto);
        }
    }

    private void distributeDealerExtraCard(GameParticipant gameParticipant, CardDeck cardDeck) {
        Dealer dealer = gameParticipant.getDealer();
        boolean dealerExtraCard = dealer.ableToAddCard();
        if (dealerExtraCard) {
            gameParticipant.addExtraCard(dealer, cardDeck);;
        }
        ParticipantCardsDto dealerCardsDto = createParticipantCardsDto(dealer);
        outputView.printDealerExtraCard(dealerCardsDto, dealerExtraCard);
    }

    private void determineParticipantFinalCards(GameParticipant gameParticipant) {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        participantCardsDtos.add(createParticipantCardsDto(gameParticipant.getDealer()));
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            participantCardsDtos.add(createParticipantCardsDto(player));
        }
        outputView.printParticipantsFinalCards(participantCardsDtos);
    }

    private void determineGameResult(GameParticipant gameParticipant, Map<String, Integer> bettings) {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            int bettingMoney = bettings.get(player.getName());
            GameStatus gameStatus = determineGameStatus(dealer, player);
            int betting = (int) (bettingMoney * gameStatus.getProfiteRate());
            bettings.put(player.getName(), betting);
            bettings.put(dealer.getName(), bettings.get(dealer.getName()) - betting);
        }
        displayGameResults(gameParticipant, bettings);
    }

    private GameStatus determineGameStatus(Dealer dealer, Player player) {
        if (dealer.isBust()) {
            return GameStatus.WIN;
        }
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return GameStatus.TIE;
        }
        if (player.isBlackjack()) {
            return GameStatus.BLACKJACK;
        }
        if (player.isBust()) {
            return GameStatus.LOSE;
        }
        return player.determineGameStatusByScore(dealer);
    }

    private void displayDistributedGameInitialCards(GameParticipant gameParticipant) {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
        List<ParticipantCardsDto> playerCardsDtos = new ArrayList<>();
        for (Player player : players) {
            playerCardsDtos.add(createParticipantInitialCardsDto(player));
        }
        ParticipantCardsDto dealerCardsDto = createParticipantInitialCardsDto(dealer);
        outputView.printParticipantInitialCards(dealerCardsDto, playerCardsDtos);
    }

    private void displayGameResults(GameParticipant gameParticipant, Map<String, Integer> bettings) {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
        GameResultDto dealerGameResultDto = createGameResultDto(dealer, bettings);
        List<GameResultDto> playersGameResultDtos = new ArrayList<>();
        for (Player player : players) {
            GameResultDto playersResultDto = createGameResultDto(player, bettings);
            playersGameResultDtos.add(playersResultDto);
        }
        outputView.printFinalGameResult(dealerGameResultDto, playersGameResultDtos);
    }

    private ParticipantCardsDto createParticipantInitialCardsDto(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getInitialCards(),
                participant.getCardsScore());
    }

    private ParticipantCardsDto createParticipantCardsDto(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.getCards(), participant.getCardsScore());
    }

    private GameResultDto createGameResultDto(Participant participant, Map<String, Integer> bettings) {
        String participantName = participant.getName();
        return new GameResultDto(participantName, bettings.get(participantName));
    }
}
