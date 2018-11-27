import java.util.ArrayList;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findTargetParkingLot() {
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
