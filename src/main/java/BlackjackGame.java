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

    private final GameParticipant gameParticipant;
    private final CardDeck cardDeck;

    public BlackjackGame() {
        this.gameParticipant = new GameParticipant();
        this.cardDeck = new CardDeck();
    }

    public void run() {
        registerGamePlayers();
        Map<String, Integer> playersBettings = registerParticipantBettings();
        distributeGameInitialCards();
        distributeParticipantExtraCards();
        determineParticipantFinalCards();
        determineGameResult(playersBettings);
    }

    private void registerGamePlayers() {
        ExceptionHandler.repeatUntilSuccess(() -> {
            List<String> names = InputView.readPlayerNames();
            gameParticipant.registerPlayers(names);
        });
    }

    private Map<String, Integer> registerParticipantBettings() {
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
            int bettingMoney = InputView.readPlayerBettingMoney(playerName);
            bettings.put(playerName, bettingMoney);
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

    private void determineGameResult(Map<String, Integer> bettings) {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
        for (Player player : players) {
            int bettingMoney = bettings.get(player.getName());
            GameStatus gameStatus = determineGameeStatus(dealer, player);
            int betting = (int) (bettingMoney * gameStatus.getProfiteRate());
            bettings.put(player.getName(), betting);
            bettings.put(dealer.getName(), bettings.get(dealer.getName()) - betting);
        }
        createGameResultDtos(bettings);
    }

    private void createGameResultDtos(Map<String, Integer> bettings) {
        Dealer dealer = gameParticipant.getDealer();
        List<Player> players = gameParticipant.getPlayers();
        GameResultDto dealerGameResultDto = createGameResultDto(dealer, bettings);
        List<GameResultDto> playersGameResultDtos = new ArrayList<>();
        for (Player player : players) {
            GameResultDto playersResultDto = createGameResultDto(player, bettings);
            playersGameResultDtos.add(playersResultDto);
        }
        OutputView.printFinalGameResult(dealerGameResultDto, playersGameResultDtos);
    }

    private GameStatus determineGameeStatus(Dealer dealer, Player player) {
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

    private GameResultDto createGameResultDto(Participant participant, Map<String, Integer> bettings) {
        String participantName = participant.getName();
        return new GameResultDto(participantName, bettings.get(participantName));
    }
}
