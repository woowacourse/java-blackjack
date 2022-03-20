package blackjack_statepattern;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.card.CardDeck;
import blackjack_statepattern.dto.CardsDto;
import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Participant;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class BlackjackBoard {

    private final Dealer dealer;
    private final Players players;
    private final CardDeck cardDeck;

    public BlackjackBoard(Dealer dealer, Players players, CardDeck cardDeck) {
        this.dealer = dealer;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void distributeCards() {
        distributeCard(dealer);
        for (Player player : getPlayers()) {
            distributeCard(player);
        }
    }

    private void distributeCard(Participant participant) {
        while (participant.isReady()) {
            participant.receiveCard(cardDeck.draw());
        }
    }

    public void hitCard(Participant participant) {
        participant.receiveCard(cardDeck.draw());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public CardsDto getInitialCardsDto() {
        Map<Participant, List<Card>> participantsCards = new LinkedHashMap<>();
        participantsCards.put(dealer, List.of(dealer.getOneCard()));
        for (Player player : getPlayers()) {
            participantsCards.put(player, player.getCardsValue());
        }
        return CardsDto.of(participantsCards);
    }

    public CardsDto getFinalCardsDto() {
        Map<Participant, List<Card>> participantsCards = new LinkedHashMap<>();
        participantsCards.put(dealer, dealer.getCardsValue());
        for (Player player : getPlayers()) {
            participantsCards.put(player, player.getCardsValue());
        }
        return CardsDto.of(participantsCards);
    }

    public GameResult getGameResult() {
        return GameResult.of(dealer, players.getPlayers());
    }
}
