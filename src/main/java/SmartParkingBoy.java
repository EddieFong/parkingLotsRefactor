import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
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
        int max = -1;
        for(ParkingLot parkingLot:super.getParkingLots()){
            if ((parkingLot.getCapacity() - parkingLot.getCount()) > max) {
                max = (parkingLot.getCapacity() - parkingLot.getCount());
                target = parkingLot;
            }
        };

        return target;

    }
}
