package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.result.DealerResultDto;
import blackjack.domain.result.GameResultDto;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_UNDER = 16;

    private final Deck deck;

    public Dealer() {
        super(DEALER_NAME, Hand.createEmptyHand());
        this.deck = Deck.createShuffledDeck();
    }

    public void drawBaseCard() {
        selfDraw();
        selfDraw();
    }

    public void drawBaseCardToPlayers(List<Player> players) {
        for (Player player : players) {
            deal(player);
            deal(player);
        }
    }

    public void deal(Player player) {
        player.receiveCard(draw());
    }

    public void selfDraw() {
        cardHand.add(draw());
    }

    private Card draw() {
        return deck.drawCard();
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public boolean shouldReceive() {
        return cardHand.getDealerTotal() <= DEALER_UNDER;
    }

    public GameResultDto getGameResult(List<Player> players) {
        List<PlayerResultDto> playersResults = new ArrayList<>();
        DealerResultDto dealerMatchCount = new DealerResultDto();
        int dealerTotal = getHandTotal();

        for (Player player : players) {
            MatchResult matchResult = player.getMatchResult(dealerTotal);

            playersResults.add(PlayerResultDto.from(player, matchResult));

            MatchResult dealerMatch = matchResult.reverse();
            dealerMatchCount.add(dealerMatch);
        }

        return new GameResultDto(getCards(), dealerTotal, dealerMatchCount, playersResults);
    }

    @Override
    public int getHandTotal() {
        return cardHand.getDealerTotal();
    }
}
