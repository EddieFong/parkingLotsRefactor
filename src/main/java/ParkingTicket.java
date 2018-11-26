public class ParkingTicket {
    private final ParkingLot parkingLot;
    private boolean used;

    public Car getCar() {
        return car;
    }

    private final Car car;

    public ParkingTicket(Car car, ParkingLot parkingLot) {
        this.car = car;
        this.parkingLot = parkingLot;
        this.used = false;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
