package vo;

public class Revenue {
    private final double revenue;

    private Revenue(double revenue) {
        this.revenue = revenue;
    }

    public static Revenue from(double revenue) {
        return new Revenue(revenue);
    }

    public double getRevenue() {
        return revenue;
    }
}
