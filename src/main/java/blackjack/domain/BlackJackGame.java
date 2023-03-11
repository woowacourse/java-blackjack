package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.dto.HandResult;
import blackjack.dto.HandStatus;
import blackjack.dto.TotalGameResult;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private static final int INITIAL_HAND_OUT_COUNT = 2;
    private static final int HIT_CARD_COUNT = 1;

    private final Deck deck;
    private final Participants participants;


    public BlackJackGame(final DeckGenerator deckGenerator, final Dealer dealer, final List<Player> players) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(dealer, players);
    }

    public void handOut() {
        deck.handCardsTo(participants.dealer(), INITIAL_HAND_OUT_COUNT);
        deck.handCardsTo(participants.players(), INITIAL_HAND_OUT_COUNT);
    }

    public List<Player> getHitAblePlayers() {
        return participants.findHitAblePlayers();
    }

    public void hit(final Participant participant) {
        deck.handCardsTo(participant, HIT_CARD_COUNT);
    }

    public int hitOrStayForDealer() {
        int hitCount = 0;
        final Dealer dealer = participants.dealer();
        while (dealer.isHitAble()) {
            hit(dealer);
            hitCount++;
        }
        return hitCount;
    }

    public List<HandStatus> openHandStatuses() {
        final Dealer dealer = participants.dealer();
        final List<HandStatus> handStatuses = new ArrayList<>();
        handStatuses.add(dealer.toHandStatus());
        participants.players()
                .forEach(player -> handStatuses.add(player.toHandStatus()));
        return handStatuses;
    }

    public List<HandResult> openHandResults() {
        final Dealer dealer = participants.dealer();
        final List<HandResult> handResults = new ArrayList<>();
        handResults.add(dealer.toHandResult());
        participants.players().forEach(player -> handResults.add(player.toHandResult()));
        return handResults;
    }

    public TotalGameResult computeTotalGameResult() {
        final GameResultComputer gameResultComputer = new GameResultComputer(participants.collectPlayerJudgeResults());
        return gameResultComputer.computeTotalResult();
    }
}
