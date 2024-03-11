package domain;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {
    //TODO: Name으로 조회하기
    private static final int MAX_PLAYERS_COUNT = 8;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(List<Player> players) {
        validatePlayerCount(players);
        this.players = players;
        this.dealer = new Dealer();
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException("플레이어의 수는 최대 8명 입니다.");
        }
    }

    public void initialDealing(Deck deck) {
        takeTwoCards(dealer,deck);
        players.forEach(player -> this.takeTwoCards(player, deck));
    }

    private void takeTwoCards(Gamer gamer, Deck deck) {
        gamer.pickTwoCards(deck);
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getCardsFromName(Name name) {
        return searchFromName(name).getCards();
    }

    public void drawCardFromName(Name name, Deck deck) {
        searchFromName(name).hit(deck);
    }

    public boolean isBustFromName(Name name) {
        return searchFromName(name).isBust();
    }

    private Gamer searchFromName(Name name) {
        return players.stream().filter(player -> player.isName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 참여자 입니다."));
    }
    public int drawDealerCard(Deck deck) {
        return dealer.hit(deck);
    }

    public BlackJackResult getGameResult() {
        return new BlackJackResult(
                dealer.getTotalScore(),
                players.stream()
                        .collect(Collectors.toMap(Gamer::getName, Gamer::getTotalScore)));
    }

    public int getDealerScore() {
        return dealer.getTotalScore();
    }

    public int getScoreFromName(Name name) {
        return searchFromName(name).getTotalScore();
    }
}
