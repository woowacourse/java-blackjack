package dealer;

import card.CardDeck;
import cardGame.GameParticipantCards;
import dealer.dto.DealerGameResult;
import player.Players;

public class Dealer extends GameParticipantCards {

    private static final int MIN_DEALER_SCORE = 16;

    public Dealer(CardDeck cardDeck) {
        super(cardDeck.firstCardSettings());
    }

    public void getExtraCard(CardDeck cardDeck) {
        while (isNotOverMinScore()) {
            receiveCard(cardDeck.pickCard());
        }
    }

    private boolean isNotOverMinScore() {
        return getCardScore() <= MIN_DEALER_SCORE;
    }

    public DealerGameResult getWinningResult(Players players) {
        int winningCount = (int) players.getPlayers().stream()
                .filter(player -> !player.isWinner(getCardScore()))
                .count();

        return new DealerGameResult(winningCount, players.getSize() - winningCount);
    }
}
