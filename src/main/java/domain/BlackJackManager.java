package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlackJackManager {

    private final List<Participant> participants;
    private final CardDeck cardDeck;

    public BlackJackManager(List<Participant> participants, CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    // 게임이 시작되면 모든 플레이어에게 카드를 2장씩 나눠준다.
    public void start() {
        for (Participant participant : participants) {
            participant.addCard(cardDeck.getAndRemoveFrontCard());
            participant.addCard(cardDeck.getAndRemoveFrontCard());
        }
    }

    // 게임의 결과를 반환한다
    public ParticipantsResult calculateResult() {
        // 플레이어와 딜러를 분리
        final Participant dealer = participants.getFirst();
        final List<Participant> players = new ArrayList<>(participants);
        players.removeFirst();
        List<PlayerResult> playerResults = new ArrayList<>();
        DealerResult dealerResult = new DealerResult();
        for (final Participant player : players) {
            // 딜러-플레이어 중 딜러의 결과를 얻는다.
            GameResult currentDealerResult = GameResult.calculateResult(dealer, player);
            dealerResult.add(currentDealerResult);
            playerResults.add(new PlayerResult(player, currentDealerResult.reverse()));
        }
        return new ParticipantsResult(dealerResult, playerResults);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlackJackManager that = (BlackJackManager) o;
        return Objects.equals(participants, that.participants) && Objects.equals(
            cardDeck, that.cardDeck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participants, cardDeck);
    }
}
