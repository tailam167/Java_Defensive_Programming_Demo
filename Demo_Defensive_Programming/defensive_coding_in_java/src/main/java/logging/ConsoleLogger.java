package logging;

import java.util.List;
import java.util.Optional;

import com.defcoding.entities.Flight;

public class ConsoleLogger {
	
	public void writeError(Exception e) {
		System.out.println("Error: " + e);
	}
	
	public void writeInfor(String msgInfor) {
		System.out.println("Infor: " + msgInfor);
	}
	
	public void writeNotify(String msgNotify) {
		System.out.println(msgNotify);
	}
	
	public List<Integer> writeAmount(List<Integer> amountList) {
		return amountList;
	}
	
	public List<String> writeListFlight(List<String> flightList) {
		return flightList;
	}
	
	public void infoEarliestFlight(String msg, Flight earliestFlight) {
		System.out.println(msg + earliestFlight);
	}
	
	public void inforOptinalFlight(String msgString, Optional<Flight> inOptionalFlight) {
		System.out.println(msgString + inOptionalFlight);
	}
}
