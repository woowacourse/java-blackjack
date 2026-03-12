package domain;

import common.ErrorMessage;
import dto.DealerResultDto;
import dto.GameResultDto;
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
        Card card1 = dealersInitialCards.get(0);
        Card card2 = dealersInitialCards.get(1);
        return Dealer.from(Hand.of(card1, card2));
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
        Optional<Dealer> nextDealer = dealer.addCard(totalDeck::drawCard);
        nextDealer.ifPresent(this::updateDealer);
        return nextDealer.isPresent();
    }

    public List<ParticipantDto> getPlayersGameSettingStates() {
        return multiPlayers.getInitialStates();
    }

    public ParticipantDto getDealerGameSettingState() {
        return ParticipantDto.consistWithInitialInfo(dealer);
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
