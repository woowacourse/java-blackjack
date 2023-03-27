package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Participants participants;
    private final GameResult gameResult;

    public BlackjackGame(Participants participants, CardDeck cardDeck, GameResult gameResult) {
        this.participants = participants;
        this.cardDeck = cardDeck;
        this.gameResult = gameResult;
    }

    public static BlackjackGame of(Participants participants, List<BetAmount> betAmounts) {
        GameResult gameResult = GameResult.of(participants.getPlayers(), betAmounts);
        return new BlackjackGame(participants, new CardDeck(), gameResult);
    }

    public void dealOutCard() {
        for (Player player : participants.getPlayers()) {
            List<Card> cards = cardDeck.pickTwice();
            player.addCards(cards);
        }
        dealOutCardToDealer();
    }

    private void dealOutCardToDealer() {
        Dealer dealer = participants.getDealer();
        List<Card> cards = cardDeck.pickTwice();
        dealer.addCards(cards);
    }

    public void calculateBetAmount() {
        for (Player player : participants.getPlayers()) {
            ResultState resultState = ResultState.of(player, participants.getDealer());
            gameResult.calculateResultEachPlayer(player, resultState.getYield());
        }
    }

    public BetAmount resultEachPlayer(Player player) {
        return gameResult.eachPlayer(player);
    }

    public BetAmount dealerResult() {
        return gameResult.dealer();
    }

    public void giveCard(Participant participant) {
        participant.addCard(cardDeck.pick());
    }
}
