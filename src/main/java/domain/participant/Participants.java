package domain.participant;

import domain.card.TrumpCard;
import domain.game.GameResult;
import domain.game.WinStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Participants {

    private final String PLAYER_NOT_EXIST = "존재하지 않는 플레이어입니다.";

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(List<ParticipantName> names, Dealer dealer) {
        players = names.stream()
                .map(Player::new)
                .toList();

        this.dealer = dealer;
    }

    private Player findPlayer(ParticipantName name) {
        return players.stream()
                .filter(player -> player.name().isMatch(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_EXIST));
    }

    public List<GameResult> calculatePlayerResults() {
        List<GameResult> gameResults = new ArrayList<>();
        for (ParticipantName name : getPlayerNames()) {
            List<TrumpCard> trumpCards = playerCards(name);
            Score sum = calculateCardSum(name);
            gameResults.add(new GameResult(name, trumpCards, sum));
        }
        return Collections.unmodifiableList(gameResults);
    }

    public List<ParticipantName> getPlayerNames() {
        return players.stream()
                .map(Player::name)
                .toList();
    }

    public GameResult playerResult(ParticipantName name) {
        Player player = findPlayer(name);
        Score sum = player.calculateCardSum();
        List<TrumpCard> trumpCards = player.cards();
        return new GameResult(name, trumpCards, sum);
    }

    public List<TrumpCard> playerCards(ParticipantName name) {
        Player player = findPlayer(name);
        return player.cards();
    }

    public List<TrumpCard> dealerCards(){
        return dealer.cards();
    }

    public Score calculateCardSum(ParticipantName name) {
        Player player = findPlayer(name);
        return player.calculateCardSum();
    }

    public ParticipantName dealerName() {
        return dealer.name();
    }

    public boolean isBust(ParticipantName name) {
        Player player = findPlayer(name);
        return player.isBust();
    }

    public boolean isDrawable(ParticipantName name) {
        Player player = findPlayer(name);
        return player.isDrawable();
    }

    public void addCard(ParticipantName name, TrumpCard trumpCard) {
        Player player = findPlayer(name);
        player.addCard(trumpCard);
    }

    public void dealDealerCard(TrumpCard trumpCard){
        dealer.addCard(trumpCard);
    }

    public WinStatus determinePlayerResult(ParticipantName playerName) {
        Player player = findPlayer(playerName);
        return player.determineResult(dealer.calculateCardSum());
    }
}
