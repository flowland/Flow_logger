package application;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserInfo")
public class DataWrapper {


	    private ArrayList<Person> persons;

	    @XmlElement(name = "person")
	    public List<Person> getPersons() {
	        return persons;
	    }

	    public void setPersons(List<Person> persons) {
	        this.persons = persons;
	    }
	}
}
