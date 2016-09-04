package edu.npu.hotelapp.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class ReservationList implements Serializable {
	private static final long serialVersionUID = 1L;
	// XmlElement sets the name of the entities
	@XmlElement(name = "customer")
	private List<Customer> custList;

	public ReservationList() {
	}
	
	public List<Customer> getSList() {
		return custList;
	}

	public void setStudentList(List<Customer> newStudList) {
		this.custList = newStudList;
	}
	
	public int numEntries() {
		if (custList == null) return 0;
		return custList.size();
	}
	
	public Customer getStudent(int id) {
		return custList.get(id);
	}
	
	public String toString() {
		String listStr;
		
		listStr = "CustomerList{";
		for (Customer entry: custList) {
			listStr = listStr + "\n\t" + entry;
		}
		
		listStr = listStr + "\n}";
		return listStr;
	}
}
