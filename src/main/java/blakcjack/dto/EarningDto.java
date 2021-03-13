package blakcjack.dto;

public class EarningDto {
    private final String name;
    private final int earning;

    private EarningDto(final String name, final int earning) {
        this.name = name;
        this.earning = earning;
    }

    // TODO : 구조가 확정되면 나중에 수정
//    public static EarningDto of(final Participant participant) {
//        return new EarningDto(participant.getNameValue(), participant.getMoneyValue());
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getMoney() {
//        return money;
//    }
}
