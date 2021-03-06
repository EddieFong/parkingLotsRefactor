import java.util.ArrayList;

public class ParkingBoy {
    private ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    private String lastErrorMessage;

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null){
            this.lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }
        if ( (ticket.getCar() == null) || (ticket.getParkingLot() == null) || ticket.isUsed()){
            return handle_wrong_ticket();
        }
        return returnActualFetchingCar(ticket);
    }

    private Car returnActualFetchingCar(ParkingTicket ticket) {
        ParkingLot parkingLot = ticket.getParkingLot();
        ticket.setUsed(true);
        return parkingLot.fetch(ticket.getCar());
    }

    private Car handle_wrong_ticket() {
        this.lastErrorMessage = "Unrecognized parking ticket.";
        return null;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = findTargetParkingLot();
        if (parkingLot != null) {
            boolean parked = parkingLot.isCarParked(car);
            if (parked){
                ParkingTicket parkingTicket = new ParkingTicket(car, parkingLot);
                this.lastErrorMessage = null;
                return parkingTicket;
            }
            return null;
        } else {
            this.lastErrorMessage = "The parking lot is full.";
            return null;
        }
    }

    protected ParkingLot findTargetParkingLot() {
        for(ParkingLot parkingLot:parkingLots){
            if (parkingLot.getCapacity() > parkingLot.getCount())
                return parkingLot;
        };

        return null;

    }


    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
