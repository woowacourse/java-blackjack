package domain.member;

public class Player implements Participant {

    private final MemberInfo playerInfo;
    private final Money betMoney;

    public Player(MemberInfo playerInfo, Money betMoney) {
        this.playerInfo = playerInfo;
        this.betMoney = betMoney;
    }

    @Override
    public MemberInfo info() {
        return playerInfo;
    }

    public int calculateProfit(MemberInfo memberInfo) {
        return betMoney.calculateProfit(playerInfo.state().earningRate(memberInfo.state()));
    }
}
