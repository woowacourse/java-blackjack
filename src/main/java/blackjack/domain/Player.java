package blackjack.domain;

public class Player extends Participant {

    private static final int NICKNAME_MINIMUM_LENGTH = 4;
    private static final int NICKNAME_MAXIMUM_LENGTH = 10;

    private final String nickname;
    private Bet bet;


    public Player(final Hand hand, final Status status, final String nickname) {
        super(hand, status);
        validateNicknameLength(nickname);
        this.nickname = nickname;
    }

    private void validateNicknameLength(final String nickname) {
        if (nickname.length() < NICKNAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 4자 이상이어야 합니다.");
        }
        if (nickname.length() > NICKNAME_MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 10자 이하여야 합니다.");
        }
    }

    public int getProfit() {
        return bet.calculateProfit();
    }

    public void bet(final Bet bet) {
        if (this.bet != null) {
            throw new IllegalStateException("해당 플레이어는 이미 베팅금을 입력하였습니다.");
        }
        this.bet = bet;
    }

    public void decidePayoutPolicy(final GameResult gameResult) {
        bet.decidePayoutPolicy(gameResult);
    }

    @Override
    public String getNickname() {
        return nickname;
    }

}
