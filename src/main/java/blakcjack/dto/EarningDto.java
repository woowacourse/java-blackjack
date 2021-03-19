package blakcjack.dto;

public class EarningDto {
    private final String name;
    private final int earning;

    private EarningDto(final String name, final int earning) {
        this.name = name;
        this.earning = earning;
    }

    public static EarningDto of(final String name, final int earning) {
        return new EarningDto(name, earning);
    }

    public String getName() {
        return name;
    }

    public int getEarning() {
        return earning;
    }
}
