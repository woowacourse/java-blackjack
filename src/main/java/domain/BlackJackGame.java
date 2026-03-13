package domain;

import common.ErrorMessage;
import domain.state.GameState;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.GameStateDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.List;
import java.util.Optional;

public class BlackJackGame {
    private final Deck totalDeck;
    private final MultiPlayers multiPlayers;
    private Dealer dealer;

    private BlackJackGame(Deck totalDeck, Dealer dealer, MultiPlayers multiPlayers) {
        this.totalDeck = totalDeck;
        this.dealer = dealer;
        this.multiPlayers = multiPlayers;
    }

    public static BlackJackGame ready(List<String> playerNames, CardCreationStrategy strategy) {
        Deck totalDeck = Deck.createDeck(strategy);
        return new BlackJackGame(
                totalDeck,
                createNewDealer(totalDeck),
                MultiPlayers.of(playerNames, totalDeck)
        );
    }

    private static Dealer createNewDealer(Deck totalDeck) {
        List<Card> dealersInitialCards = totalDeck.drawTwoCards();
        Hand initialDealerHand = Hand.of(
                dealersInitialCards.get(0),
                dealersInitialCards.get(1)
        );
        return Dealer.from(
                GameState.createInitialGameState(initialDealerHand)
        );
    }

    public Optional<Player> whoseTurn() {
        return multiPlayers.findNotStayPlayer();
    }

    public ParticipantDto doHitProcess() {
        Player newPlayer = multiPlayers.findNotStayPlayer()
                .map(player -> multiPlayers.executeHit(player, totalDeck::drawCard))
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_MORE_PLAYABLE_PLAYER.getMessage()));
        return ParticipantDto.from(newPlayer);
    }

    public ParticipantDto doStandProcess() {
        Player newPlayer = multiPlayers.findNotStayPlayer()
                .map(multiPlayers::executeStand)
                .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_MORE_PLAYABLE_PLAYER.getMessage()));
        return ParticipantDto.from(newPlayer);
    }

    public boolean doDealerHitOrStandProcess() {
        Dealer newDealer = dealer.addCard(totalDeck::drawCard);
        updateDealer(newDealer);
        return !newDealer.gameState.isFinished();
    }

    public GameStateDto getGameSettingState() {
        return GameStateDto.from(
                ParticipantDto.consistWithInitialInfo(dealer),
                multiPlayers.getInitialStates()
        );
    }

    public GameResultDto getGameResults() {
        List<PlayerResultDto> playersGameResults = multiPlayers.checkPlayersGameResult(dealer);
        DealerResultDto dealerGameResult = DealerResultDto.from(dealer, playersGameResults);

        return new GameResultDto(dealerGameResult, playersGameResults);
    }

    private void updateDealer(Dealer newDealer) {
        this.dealer = newDealer;
    }
}
