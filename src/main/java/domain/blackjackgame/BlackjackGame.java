package domain.blackjackgame;

import domain.card.deck.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;

public class BlackjackGame {
    private final CardDeck cardDeck;

    public BlackjackGame(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void initGame(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            participant.receiveInitialCards(cardDeck.draw(), cardDeck.draw());
        }
    }

    public void dealCardTo(Participant participant) {
        participant.receiveAdditionalCard(cardDeck.draw());
    }

    public GameResult createGameResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        GameResult gameResult = new GameResult();
        for (Player player : participants.getPlayers()) {
            ResultStatus status = getResultStatusByScore(dealer, player);
            gameResult.record(player, status);
        }
        return gameResult;
    }

    private ResultStatus getResultStatusByScore(Dealer dealer, Player player) {
        if (player.isBust()) {
            return ResultStatus.LOSE;
        }
        if (dealer.isBust()) {
            return ResultStatus.WIN;
        }
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return ResultStatus.DRAW;
        }
        if (player.isBlackjack()) {
            return ResultStatus.WIN;
        }
        if (dealer.isBlackjack()) {
            return ResultStatus.LOSE;
        }
        return getResultStatusByScore(player, dealer);
    }

    private ResultStatus getResultStatusByScore(Player player, Dealer dealer) {
        if (player.isGreaterThan(dealer.calculateScore())) {
            return ResultStatus.WIN;
        }
        if (player.isLessThan(dealer.calculateScore())) {
            return ResultStatus.LOSE;
        }
        return ResultStatus.DRAW;
    }
}
