import java.util.ArrayList;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findTargetParkingLot() {
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
