package domain;

import static domain.GameResult.BLACKJACK;
import static domain.GameResult.LOSE;
import static domain.GameResult.TIE;
import static domain.GameResult.WIN;

import java.util.Objects;

public class Player extends Gamer {
    private static final int PLAYER_DRAWABLE_THRESHOLD = 20;

    private final PlayerName playerName;
    private final BettingMoney bettingMoney;

    public Player(PlayerName playerName, BettingMoney bettingMoney) {
        super();
        this.playerName = playerName;
        this.bettingMoney = bettingMoney;
    }

    public Player(Player player) {
        super(player);
        this.playerName = new PlayerName(player.playerName.username());
        this.bettingMoney = new BettingMoney(player.bettingMoney.bettingMoney());
    }

    public static Player copyOf(Player player) {
        return new Player(player);
    }

    public boolean isDrawable() {
        return this.isDrawable(PLAYER_DRAWABLE_THRESHOLD);
    }

    public GameResult decideGameResult(Dealer dealer) {
        int playerScore = this.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore > GAMER_BUST_THRESHOLD || dealerScore > GAMER_BUST_THRESHOLD) {
            return decideGameResultWithBust(playerScore);
        }
        return decideGameResultWithoutBust(dealer);
    }

    private GameResult decideGameResultWithBust(int playerScore) {
        if (playerScore > GAMER_BUST_THRESHOLD) { // 플레이어 버스트인 경우 (딜러가 버스트여도 이 경우)
            return LOSE;
        }
        return WIN; // 딜러만 버스트인 경우
    }

    private GameResult decideGameResultWithoutBust(Dealer dealer) {
        if (isBlackJack() && !dealer.isBlackJack()) { // 플레이어만 블랙잭인 경우
            return BLACKJACK;
        }
        if (cards.calculateScore() == dealer.getScore()) { // 점수 무승부
            return TIE;
        }
        if (isBlackJack() && dealer.isBlackJack()
                || cards.calculateScore() > dealer.getScore()) { // 딜러 & 플레이어 모두 블랙잭 혹은 플레이어가 점수 높은 경우
            return WIN;
        }
        return LOSE;
    }

    public int calculateProfit(Dealer dealer) {
        GameResult gameResult = decideGameResult(dealer);
        return (int) (bettingMoney.bettingMoney() * gameResult.getBettingRatio());
    }

    public boolean isSameName(PlayerName playerName) {
        return this.playerName.equals(playerName);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) && Objects.equals(bettingMoney,
                player.bettingMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, bettingMoney);
    }
}
