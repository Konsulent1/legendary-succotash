package tms;

public class ScheduleCom {
    private String ScheduleID;
    private String OrderID;
    private String PalletID;
    private String Date;
    private String Time;
	
	public ScheduleCom(String ScheduleID, String OrderID, String PalletID, String Date, String Time){
            this.ScheduleID = ScheduleID;
            this.OrderID = OrderID;
            this.PalletID = PalletID;
            this.Date = Date;
            this.Time = Time;
            System.out.println("New Schedule Created!!!");
		
	}

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getScheduleID() {
        return ScheduleID;
    }

    public void setScheduleID(String ScheduleID) {
        this.ScheduleID = ScheduleID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getPalletID() {
        return PalletID;
    }

    public void setPalletID(String PalletID) {
        this.PalletID = PalletID;
    }


}

//INSERT INTO Pallet ('PalletID','Quantity','Destination'(choose one),'ProductID')
//To Schedule: 
//INSERT INTO Schedule ('OrderID', 'PalletID'(Bruk samme ID som ble brukt til pallet), 'DateAndTime'())