package Project3;

public class TripCost {
    private final double distance;
    private final double gasCost;
    private final int gasMileage;
    private final int numDays;
    private final double hotelCost;
    private final double foodCost;
    private final int attractionCost;

    public TripCost(double distance, double gasCost, int gasMileage, int numDays, double hotelCost, double foodCost, int attractionCost) {
        this.distance = distance;
        this.gasCost = gasCost;
        this.gasMileage = gasMileage;
        this.numDays = numDays;
        this.hotelCost = hotelCost;
        this.foodCost = foodCost;
        this.attractionCost = attractionCost;
    }

    public double calculateCost() {
        double fuelCost = (distance / gasMileage) * gasCost;
        return fuelCost + (hotelCost + foodCost) * numDays + attractionCost;
    }
}