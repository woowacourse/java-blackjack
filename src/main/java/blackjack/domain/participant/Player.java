package blackjack.domain.participant;

import blackjack.domain.Betting;
import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.state.PlayerRunning;
import blackjack.dto.HitRequest;
import java.util.List;

public class Player extends Participant {

    private final Betting betting;

    public Player(Name name, List<Card> cards, Betting betting) {
        super(name, PlayerRunning.start(cards));
        this.betting = betting;
    }

    public void proceed(CardDeck deck, HitRequest hitRequest) {
        while (!isFinished() && hitRequest == HitRequest.YES) {
            hit(deck);
        }
    }

    @Override
    public List<Card> showInitialCards() {
        return List.copyOf(getCards());
    }

    public int calculateProfit(Outcome outcome) {
        return (int) (betting.getBetMoney() * outcome.getProfitRate());
    }
}
