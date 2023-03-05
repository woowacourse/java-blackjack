package dto.response;


public class WinLoseResult {

    private final String name;
    private final boolean isWin;

    public WinLoseResult(final String name, final boolean isWin) {
        this.name = name;
        this.isWin = isWin;
    }


    public String getName() {
        return name;
    }

    public boolean isWin() {
        return isWin;
    }
}
