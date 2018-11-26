import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = findTargetParkingLot();
        if (parkingLot != null) {
            boolean parked = parkingLot.park(car);
            if (parked){
                ParkingTicket parkingTicket = new ParkingTicket(car, parkingLot);
//                this.lastErrorMessage = null;
                return parkingTicket;
            }
            return null;
        } else {
//            this.lastErrorMessage = "The parking lot is full.";
            return null;
        }
    }

    private ParkingLot findTargetParkingLot() {
        ParkingLot target = null;
        int min = 100;
        for(ParkingLot parkingLot:super.getParkingLots()){
            if ((parkingLot.getCount() / parkingLot.getCapacity()) < min) {
                min = (parkingLot.getCount() / parkingLot.getCapacity());
                target = parkingLot;
            }
        };

        return target;

    }
}
