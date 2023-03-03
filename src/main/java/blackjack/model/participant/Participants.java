package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.result.Result;
import blackjack.model.result.ResultChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void distributeTwoCardsToEach(CardDeck cardDeck) {
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Participant> getParticipants(){
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public boolean hasNextPlayer() {
        for (Player player : players) {
            if (!player.isFinished()) {
                return true;
            }
        }
        return false;
    }

    public Player nextPlayer() {
        for (Player player : players) {
            if (!player.isFinished()) {
                return player;
            }
        }
        throw new IllegalStateException("카드를 뽑을 수 있는 플레이어가 더 이상 없습니다.");
    }

    public void hitOrStandByPlayer(CardDeck cardDeck, Player player, boolean isHit) {
        if (isHit) {
            player.play(cardDeck);
            return;
        }
        player.changeToStand();
    }

    public int hitOrStandByDealer(CardDeck cardDeck) {
        int hitCount = 0;
        while (!dealer.isFinished()) {
            dealer.play(cardDeck);
            hitCount++;
        }
        return hitCount;
    }

    public Map<Name, Result> playerResult() {
        Map<Name, Result> playerResult = new HashMap<>();
        ResultChecker resultChecker = new ResultChecker();
        for (Player player : players) {
            Result result = resultChecker.checkPlayerResult(player, dealer);
            playerResult.put(player.getName(), result);
        }
        return playerResult;
    }

}
