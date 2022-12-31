package PE2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

 */

/**
 * This CSD class represents a computer science department which consists of
 * different members of the department. Every CSD has one chair person, 3
 * program directors , maximum 70 faculty members, maximum 150 graduate
 * students,and maximum 500 undergrad students.
 * 
 * @author Sanchita Chowdhury
 *
 */

public class CSD {

	private ChairPerson chairPerson; // the instance variables for each member of the department are provided
	private ArrayList<ProgramDirector> programDirectors;
	private ArrayList<Faculty> faculty;
	private ArrayList<UGrad> undergradStudents;
	private ArrayList<Grad> gradStudents;

	/**
	 * This is the overloaded constructor that creates an object of CSD by the given
	 * chairPerson
	 * 
	 * @param chairPerson this is chair person assigned to the CSD
	 * 
	 */
	public CSD(ChairPerson chairPerson) { // constructor for class CSD
		this.chairPerson = chairPerson;
		this.programDirectors = new ArrayList<>();
		this.faculty = new ArrayList<>();
		this.undergradStudents = new ArrayList<>();
		this.gradStudents = new ArrayList<>();
	}

	/**
	 * This is a getter method for ChairPerson
	 * 
	 * @return the chair person of the CSD
	 */
	public ChairPerson getChairPerson() {// getter method
		return chairPerson;
	}

	/**
	 * Setter for ChairPerson
	 * 
	 * @param chairPerson sets the chair person of CSD
	 */

	public void setChairPerson(ChairPerson chairPerson) {
		this.chairPerson = chairPerson;
	}

	/**
	 * This is a getter method for Program Directors; gets a list of program
	 * directors
	 * 
	 * @return the program directors of the CSD
	 */
	public ArrayList<ProgramDirector> getProgramDirectors() {
		return programDirectors;
	}

	/**
	 * Setter for Program Directors
	 * 
	 * @param programDirectors sets the program directors for CSD
	 */
	public void setProgramDirectors(ArrayList<ProgramDirector> programDirectors) {
		this.programDirectors = programDirectors;
	}

	/**
	 * This is a getter method for Faculty; gets a list of faculty members
	 * 
	 * @return the faculty of the CSD
	 */

	public ArrayList<Faculty> getFaculty() {
		return faculty;
	}

	/**
	 * Setter for Faculty
	 * 
	 * @param faculty sets the faculty members for CSD
	 */

	public void setFaculty(ArrayList<Faculty> faculty) {
		this.faculty = faculty;
	}

	/**
	 * This is a getter method for undergrad students; gets a list of undergrad
	 * students
	 * 
	 * @return the list of undergrad students of the CSD
	 */

	public ArrayList<UGrad> getUndergradStudents() {
		return undergradStudents;
	}

	/**
	 * Setter for undergrad students
	 * 
	 * @param undergradStudents sets the undergrad student members for CSD
	 */

	public void setUndergradStudents(ArrayList<UGrad> undergradStudents) {
		this.undergradStudents = undergradStudents;
	}

	/**
	 * This is a getter method for grad students; gets a list of grad students
	 * 
	 * @return the list of grad students of the CSD
	 */

	public ArrayList<Grad> getGradStudents() {
		return gradStudents;
	}

	/**
	 * Setter for grad students
	 * 
	 * @param gradStudents sets the grad student members for CSD
	 */

	public void setGradStudents(ArrayList<Grad> gradStudents) {
		this.gradStudents = gradStudents;
	}

	/**
	 * This is a getter method for number of faculty members; gets the size of
	 * faculty arrayList
	 * 
	 * @return the list size of faculty
	 */

	public int getNumOfFaculty() {
		return faculty.size();
	}

	/**
	 * This is a getter method for number of grad students; gets the size of
	 * gradSudent arrayList
	 * 
	 * @return the list size of gradStudents
	 */

	public int getNumOfGradStudents() {
		return gradStudents.size();
	}

	/**
	 * This is a getter method for number of undergrad students; gets the size of
	 * undergradSudent arrayList
	 * 
	 * @return the list size of undergradStudents
	 */

	public int getNumOfUGradStudents() {
		return undergradStudents.size();
	}

	/**
	 * This method admits a new undergrad student.Assigns a faculty to every newly
	 * admitted undergrad student.Ensures the department does not admit the same
	 * student twice.
	 * 
	 * @param student is the UGrad student to be admitted.
	 * @exception NoSpaceException is thrown if the CSD reached the max. quota for
	 *                             Undergrad students (i.e. max 500 UGrad students)
	 * 
	 */

	public void AdmitStudent(UGrad student) throws NoSpaceException {
		if (undergradStudents.size() < 500) {
			if ((undergradStudents.size() == 0) || duplicate(student, undergradStudents)) { // if the underGrad student list is size 0 or if duplicate student record exists; helper method																	// duplicate() is called
				undergradStudents.add(student);
				setAdvisingUgrads(student);
			}
		} else {
			throw new NoSpaceException("No more space"); // if the max 500 quota for undergrad students is reached.
		}
	}

	/**
	 * This is a helper method which checks if any duplicate objects exist in the
	 * Array list. This method helps to ensure the department does not have
	 * duplicate records.
	 * 
	 * @param person is the Person to be compared to see if they already have a
	 * record in the department
	 * @param list   is the ArrayList to be compared with person.
	 * @return boolean value ; if duplicate person exists returns true , else
	 * returns false.
	 * 
	 */

	public boolean duplicate(Person person, ArrayList list) {// Duplicate record check: helper method; this method is used to simplify the AdmitStudent method.
		if (list.size() != 0) {
			for (Object p : list) {
				if (p.equals(person)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method removes a student who has graduated already from the CSD;
	 * student's information is removed from recoords.
	 * 
	 * @param student is the student to be removed after graduating.
	 */
	public void AlumnusUGrad(UGrad student) { // Removes a student from CSD.
		if (undergradStudents.contains(student)) {
			undergradStudents.remove(student);
		}
		for (Faculty f : faculty) {
			if (f.getAdvisingUgrads().contains(student)) { // if this student has records in AdvisingUgrads; clears
															// student from the records
				f.getAdvisingUgrads().remove(student);
			}
		}
	}

	/**
	 * This method admits a new Grad student.Assigns the Grad student as a TA to a
	 * faculty who hasn't exceeded the TAs limit. the department does not admit the
	 * same Grad student twice.
	 * 
	 * @param student is the Grad student to be admitted.
	 * @exception NoSpaceException is thrown if the CSD reached the max. quota for
	 *                             Undergrad students (i.e. max 150 Grad students)
	 * 
	 */

	public void HireTA(Grad student) throws NoSpaceException {
		if (gradStudents.size() < 150) {
			if ((gradStudents.size() == 0) || duplicate(student, gradStudents)) {// duplicate is the helper method used
																					// to check for any duplicate
																					// student rceord
				gradStudents.add(student);
				setTAs(student);
			}
		} else {
			throw new NoSpaceException("No more space");
		}
	}

	/**
	 * This method removes a Grad student who has graduated already from the CSD;
	 * student's information is removed from records.
	 * 
	 * @param student is the Grad student to be removed after graduating.
	 */
	public void AlumnusGrad(Grad student) throws NoTAException {
		if (gradStudents.contains(student)) {
			gradStudents.remove(student); // Removing a Grad student from CSD.
		}
		for (Faculty f : faculty) {
			if (f.getTAs().contains(student)) {
				f.getTAs().remove(student);
				if (f.getTAs().size() == 0) {
					throw new NoTAException("No more TAs available");
				}
			}
		}
	}

	/**
	 * This method extracts and returns all undergraduate student infos.
	 * 
	 * @return a sorted list of students is returned according to their full names;
	 * java collections sort is used
	 */

	public ArrayList<UGrad> ExtractAllUGradDetails() {
		Collections.sort(undergradStudents);
		return undergradStudents;
	}

	/**
	 * This method extracts and returns all the information of faculty members
	 * stored in the university faculty records
	 * 
	 * @return a sorted list of faculty is returned according to their full names;
	 * java collections sort is used
	 */

	public ArrayList<Faculty> ExtractAllFacultyDetails() {
		Collections.sort(faculty);
		return faculty;
	}

	/**
	 * This method extracts and returns all Grad Student infos.
	 * 
	 * @return a sorted list of Grad students is returned according to their full
	 * names; java collections sort is used
	 */

	public ArrayList<Grad> ExtractAllGradDetails() {
		Collections.sort(gradStudents);
		return gradStudents;
	}

	/**
	 * This method extracts and returns all student's informations who are advisees
	 * 
	 * @return a sorted list of advisees of a particular faculty is returned
	 *  according to their full names; java collections sort is used
	 */

	public ArrayList<UGrad> ExtractAdviseesDetails(Faculty faculty) {
		ArrayList<UGrad> list = new ArrayList<>(faculty.getAdvisingUgrads());
		Collections.sort(list);
		return list;
	}

	/**
	 * This method extracts and returns all Grad student's informations who are
	 * assigned to a particular faculty as a list of TAs
	 * 
	 * @return a sorted list of TAs of a particular faculty is returned according to
	 * heir full names; java collections sort is used
	 */

	public ArrayList<Grad> ExtractTAsDetails(Faculty faculty) {
		ArrayList<Grad> list = new ArrayList<>(faculty.getTAs());
		Collections.sort(list);
		return list;
	}

	/**
	 * This method extracts and returns all Faculty informations
	 * 
	 * @return a sorted list of faculty members is returned according to their full
	 * names; java collections sort is used
	 */

	public ArrayList<Faculty> ExtractFacultyDetails(String program) {
		ArrayList<Faculty> list = new ArrayList<>();
		for (Faculty f : faculty) {
			if (f.getProgram().equals(program)) {
				list.add(f);
			}
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * This method hires a new faculty member; adds faculty information to
	 * university faculty records. The newly hired faculty is assigned to the
	 * designated Program Director according to the specialty of faculty member, and
	 * then the hired faculty will be added to the corresponding Program Director's
	 * record responsible of the same program.
	 * 
	 * @exception throws NoSpaceException if number of faculty members exceed a
	 *  maximum of 70 members.
	 */

	public void HireFaculty(Faculty f) throws NoSpaceException {
		if (faculty.size() < 70) {
			if ((faculty.size() == 0) || duplicate(f, faculty)) {// duplicate method helps check if any duplicate
																	// faculty record exists
				faculty.add(f);
				for (ProgramDirector pd : programDirectors) {
					if (pd.getProgram().equals(f.getProgram())) {
						if (pd.getFaculty().size() < 25) {
							pd.getFaculty().add(f);
						}
					}
				}
			}
		} else {
			throw new NoSpaceException("No more spaces");
		}
	}

	/**
	 * This method removes retired faculty information from university faculty
	 * records. The designated program Director will assign the students and TAs of
	 * the retired faculty to the next faculty member.
	 * 
	 * @exception throws NoSpecialtyException if no faculty is left.
	 */

	public void RetireFaculty(Faculty f) throws NoSpecialtyException {
		if (faculty.contains(f)) {
			faculty.remove(f);
			for (Grad gs : f.getTAs()) {
				setTAs(gs);
			}
			for (UGrad us : f.getAdvisingUgrads()) {
				setAdvisingUgrads(us);
			}
			for (ProgramDirector pd : programDirectors) {
				if (pd.getProgram().equals(f.getProgram())) {
					pd.getFaculty().remove(f);
					if (pd.getFaculty().size() == 0) {
						throw new NoSpecialtyException(
								"There are no faculties availible with the same specialty if this program");
					}
				}
			}
		}
	}

	/**
	 * This method adds a new Program Director. Number of Program Directors are 3
	 * for each of program.
	 */

	public void addProgramDirector(ProgramDirector programDirector) {
		if (programDirectors.size() < 3) {
			if (programDirectors.size() == 0) {
				programDirectors.add(programDirector);
			} else {
				boolean duplicate = true; // checks for duplicate program director informations
				for (ProgramDirector pd : programDirectors) {
					if (pd.getProgram().equals(programDirector.getProgram())) {
						duplicate = false;
						break;
					}
				}
				if (duplicate) {
					programDirectors.add(programDirector);
				}
			}
		}
	}

	/**
	 * This method assigns the Grad student as a TA to a faculty who has not
	 * exceeded the TAs limit yet. Maximum of 5 Grad students can be assigned to a
	 * Faculty as TAs
	 */

	public void setTAs(Grad student) {
		for (Faculty f : faculty) {
			if (f.getTAs().size() < 5) {
				f.getTAs().add(student);
				student.setAdvisor(f);
				break;
			}
		}
	}

	/**
	 * This method assigns a faculty to the UGrad student; adds the student to the
	 * related faculty record Maximum of 8 UGrad students can be assigned to a
	 * Faculty for advising
	 */

	public void setAdvisingUgrads(UGrad student) {
		for (Faculty fac : faculty) {
			if (fac.getAdvisingUgrads().size() < 8) {
				fac.getAdvisingUgrads().add(student);
				student.setAdvisor(fac);
				break;
			}
		}
	}

}

/**
 * This abstract Person class holds information about any Person in the CSD.
 * Information includes first name, last name, age, gender and address
 */

abstract class Person {
	private String firstName; // instance variables for class Person
	private String lastName;
	private int age;
	private String gender;
	private String address;

	/**
	 * This is the overloaded constructor that creates an object of Person by the
	 * given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param addresse  is the address of the person
	 * 
	 */
	public Person(String firstName, String lastName, int age, String gender, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}

	/**
	 * This is a getter method for Person's firstName
	 * 
	 * @return the firstName of the Person
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName
	 * 
	 * @param firstName sets the person's first name
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * This is a getter method for Person's lastName
	 * 
	 * @return the lastName of the Person
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName
	 * 
	 * @param lastName sets the person's first name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * This is a getter method for Person's age
	 * 
	 * @return the age of the Person
	 */

	public int getAge() {
		return age;
	}

	/**
	 * Setter for age
	 * 
	 * @param age sets the person's age
	 */

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * This is a getter method for Person's gender
	 * 
	 * @return the gender of the Person
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for gender
	 * 
	 * @param gender sets the person's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * This is a getter method for Person's address
	 * 
	 * @return the address of the Person
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for address
	 * 
	 * @param address sets the person's address
	 */

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Overrides the toString method
	 * 
	 * @return First Name= firstName, Last Name= lastName, age= age, gender= gender,
	 * address= address
	 */

	@Override
	public String toString() {
		return "First Name= " + firstName + ", Last Name= " + lastName + ", age= " + age + ", gender= " + gender
				+ ", address= " + address;
	}
}

/**
 * This Academic class is a subclass of Person class ;it is a super class for
 * Administrator and Faculty Class. This class holds information such as
 * employee ID and salary and program name.
 */

class Academic extends Person {
	private int empID; // instance variable for employee ID
	private static int count = 100; // counter to increment empID each time a new object is created
	private double salary;
	private String program;

	/**
	 * This is the overloaded constructor that creates an object of Academic by the
	 * given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param addresse  is the address of the person
	 * 
	 */

	public Academic(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.empID = count++; // increments the employee Id for each new object
	}

	/**
	 * This is a getter method for employee ID
	 * 
	 * @return the employee ID of the Academic
	 */
	public int getEmployeeID() {
		return empID;
	}

	/**
	 * Setter for employeeID
	 * 
	 * @param emp sets the employee's ID
	 */

	public void setEmpID(int emp) {
		this.empID = emp;
	}

	/**
	 * This is a getter method for employee Salary
	 * 
	 * @return the salary of the Academic
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Setter for salary
	 * 
	 * @param salary sets the employee's salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * This is a getter method for program name
	 * 
	 * @return the program of the Academic
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * Setter for Academic's program
	 * 
	 * @param program sets the Academic's program
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * overrides the equals method
	 * 
	 * @param o is the object to be compared with
	 * @return returns true if employee ID and Salary matches with object o
	 */

	@Override
	public boolean equals(Object o) {

		Academic academic = (Academic) o;// creates an Academic object from the object o which we passed
		if (getEmployeeID() != academic.getEmployeeID())
			return false;
		if (getSalary() != getSalary())
			return false;

		return true;
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the format : [[ employeeID, salary[firstName,
	 *         lastName, age, gender, address]]]
	 */
	@Override
	public String toString() {
		return "[[" + getEmployeeID() + ", " + getSalary() + "[" + getFirstName() + ", " + getLastName() + ", "
				+ getAge() + ", " + getGender() + ", " + getAddress() + "]]]";
	}
}

/**
 * This Administrator class is a subclass of Academics class ;it is a super
 * class for ProgramDirector and ChairPerson Class. This class holds information
 * such as the ones in its superclass.
 */
class Administrator extends Academic {
	public Administrator(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the format : [[ employeeID, salary[firstName,
	 *         lastName, age, gender, address]]]; just like its super class
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}

/**
 * This Faculty class is a subclass of Academics class implements Comparable
 * interface
 * 
 */
class Faculty extends Academic implements Comparable<Faculty> {
	private ArrayList<UGrad> undergradStudents; // array list of UGrad students; instance variable
	private ArrayList<Grad> gradStudents; // array list of Grad students; instance variable

	/**
	 * This is the overloaded constructor that creates an object of Faculty by the
	 * given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param addresse  is the address of the person
	 */
	public Faculty(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.undergradStudents = new ArrayList<>(); // creates a new list object of UGrad students
		this.gradStudents = new ArrayList<>(); // creates a new list object of Grad students
	}

	/**
	 * This is a getter method for Advising UGrad students
	 * 
	 * @return list of UGrad students
	 */
	public ArrayList<UGrad> getAdvisingUgrads() {
		return undergradStudents;
	}

	/**
	 * Setter for Advising UGrad students
	 * 
	 * @param program sets the Ugrad students as advising students
	 */
	public void setAdvisingUgrads(ArrayList<UGrad> advisingUgrads) {
		this.undergradStudents = advisingUgrads;
	}

	/**
	 * This is a getter method for TAs
	 * 
	 * @return list of Grad students who are TAs
	 */
	public ArrayList<Grad> getTAs() {
		return gradStudents;
	}

	/**
	 * Setter for TAs
	 * 
	 * @param program sets the Grad students as TAs
	 */
	public void setTAs(ArrayList<Grad> TAs) {
		this.gradStudents = TAs;
	}

	/**
	 * This is a getter method for getting size/ number of Advising UGrad students
	 * 
	 * @return size of UGrad students list
	 */
	public int getNumOfAdvisingUGrads() {
		return undergradStudents.size();
	}

	/**
	 * This is a getter method for getting size/ number of TAs
	 * 
	 * @return size of TAs/ Grad students' list
	 */
	public int getNumOfTAs() {
		return gradStudents.size();
	}

	/**
	 * overrides the compareTo method compares two faculties but first and last name
	 * 
	 * @return 0 if the faculties are the same, returns -1 if fac1<fac2 and returns
	 *         1 otherwise
	 */
	@Override
	public int compareTo(Faculty faculty) {
		String fac1 = this.getFirstName() + ", " + this.getLastName();
		String fac2 = faculty.getFirstName() + ", " + faculty.getLastName();
		if (fac1.equals(fac2)) {
			return 0;
		} else if (fac1.compareTo(fac2) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the format : "Faculty program[[ employeeID,
	 * salary[firstName, lastName, age, gender, address]]]"
	 * 
	 */

	@Override
	public String toString() {
		return "Faculty " + this.getProgram() + super.toString();
	}

	/**
	 * overrides the equals method
	 * 
	 * @param o is the object to be compared with
	 * @return returns true if employee ID and Salary and undergrad and grad
	 *         students matches with object o
	 */

	@Override
	public boolean equals(Object o) {

		Faculty faculty = (Faculty) o;// creates an Faculty object from the object o which we passed
		if (getEmployeeID() != faculty.getEmployeeID())
			return false;
		if (getSalary() != getSalary())
			return false;
		if (!undergradStudents.equals(faculty.undergradStudents))
			return false;
		if (!gradStudents.equals(faculty.gradStudents))
			return false;

		return true;
	}

}

/**
 * This ProgramDirector class is a subclass of Administrator class This class
 * holds information such as the ones in its superclass.
 */
class ProgramDirector extends Administrator {
	private ArrayList<Faculty> faculty;

	/**
	 * This is the overloaded constructor that creates an object of ProgramDirector
	 * by the given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param address   is the address of the person
	 * 
	 */
	public ProgramDirector(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		faculty = new ArrayList<>(); // new faculty array list object
	}

	/**
	 * This is a getter method for getting faculty members
	 * 
	 * @return faculty ; an array list of faculty members
	 */
	public ArrayList<Faculty> getFaculty() {
		return faculty;
	}

	/**
	 * This is a setter method for setting faculty members
	 * 
	 * @param faculty ; an array list of faculty members is passed
	 */
	public void setFaculty(ArrayList<Faculty> faculty) {
		this.faculty = faculty;
	}

	/**
	 * overrides the equals method
	 * 
	 * @param o is the object to be compared with
	 * @return returns true if employee ID and Salary and faculty list matches with
	 *         object o
	 */

	@Override
	public boolean equals(Object o) {

		ProgramDirector progDir = (ProgramDirector) o;// creates an ProgramDirector object from the object o which we
														// passed
		if (getEmployeeID() != progDir.getEmployeeID())
			return false;
		if (getSalary() != getSalary())
			return false;
		if (!faculty.equals(progDir.faculty))
			return false;

		return true;
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the format : "ProgramDirector[[ employeeID,
	 * salary[firstName, lastName, age, gender, address]]]"
	 * 
	 */
	@Override
	public String toString() {
		return "ProgramDirector " + super.toString();
	}
}

/**
 * This ChairPerson class is a subclass of Administrator class This class holds
 * information such as the ones in its superclass.
 */
class ChairPerson extends Administrator {

	/**
	 * This is the overloaded constructor that creates an object of ChairPerson by
	 * the given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param address   is the address of the person
	 * 
	 */
	public ChairPerson(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the format : "Chair Person[[[ employeeID,
	 * salary[firstName, lastName, age, gender, address]]]]"
	 * 
	 */
	@Override
	public String toString() {
		return "Chair Person [" + super.toString() + "]";
	}
}

/**
 * This Student class is a subclass of Person class implements Comparable
 * interface
 * 
 */
class Student extends Person implements Comparable<Student> {
	private int ID;// instance variable for student ID
	private static int count = 1000; // counter to increment student ID each time new student obj is created
	private Faculty advisor;

	/**
	 * This is the overloaded constructor that creates an object of Student by the
	 * given attributes.
	 * 
	 * @param firstName is the first name of the person
	 * @param lastName  is the last name of the person
	 * @param age       is the age of the person
	 * @param gender    is the gender of the person
	 * @param address   is the address of the person
	 */
	public Student(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
		this.ID = count++;
	}

	/**
	 * This is a getter method for getting student ID
	 * 
	 * @return ID for the student
	 */
	public int getID() {
		return ID;
	}

	/**
	 * This is a setter method for setting student ID
	 * 
	 * @param ID passed in for the student
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * This is a getter method for getting advisor
	 * 
	 * @return advisor for the student
	 */

	public Faculty getAdvisor() {
		return advisor;
	}

	/**
	 * This is a setter method for setting advisor
	 * 
	 * @param advisor passed in for the student
	 */
	public void setAdvisor(Faculty advisor) {
		this.advisor = advisor;
	}

	/**
	 * overrides the compareTo method compares two students by first and last name
	 * 
	 * @return 0 if the students are the same, returns -1 if s1<s2 and returns 1
	 *         otherwise
	 */
	@Override
	public int compareTo(Student student) {
		String s1 = this.getFirstName() + ", " + this.getLastName();
		String s2 = student.getFirstName() + ", " + student.getLastName();
		if (s1.equals(s2)) {
			return 0;
		} else if (s1.compareTo(s2) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * overrides the equals method
	 * 
	 * @param o is the object to be compared with
	 * @return returns true if student ID matches
	 */

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!super.equals(o)) // dosen't match with super class
			return false;
		Student student = (Student) o;
		if (getID() != student.getID())
			return false;
		return true;
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the similar format : "Undergraduate
	 *         [1000[[Rebert, Jack, 59, Male, Birchmount Road]]]"
	 */
	@Override
	public String toString() {
		return "[" + this.getID() + "[[" + getFirstName() + ", " + getLastName() + ", " + getAge() + ", " + getGender()
				+ ", " + getAddress() + "]]]";
	}
}

/**
 * UGrad class is a subclass of Student
 */
class UGrad extends Student {

	/**
	 * overloaded constructor with same parameters as its super class
	 */
	public UGrad(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the similar format : "Undergraduate
	 *         [1000[[Rebert, Jack, 59, Male, Birchmount Road]]]"
	 */
	@Override
	public String toString() {
		return "Undergraduate " + super.toString();
	}
}

/**
 * UGrad class is a subclass of Student
 */
class Grad extends Student {
	/**
	 * overloaded constructor with same parameters as its super class
	 */
	public Grad(String firstName, String lastName, int age, String gender, String address) {
		super(firstName, lastName, age, gender, address);
	}

	/**
	 * overrides the toString method
	 * 
	 * @return returns a String in the similar format : "Graduate [1000[[Rebert,
	 *         Jack, 59, Male, Birchmount Road]]]"
	 */
	@Override
	public String toString() {
		return "Graduate " + super.toString();
	}
}

// three custom exceptions

/**
 * @exception NoSpaceException is a custom Made Exception For this CSD class It
 * Is thrown when there is no space left to admit
 * new faculty/students
 * 
 */
class NoSpaceException extends Exception {
	public NoSpaceException(String message) {
		super(message);
	}
}

/**
 * @exception NoSpecialtyException is a custom Made Exception For this CSD class
 *  It Is thrown when there is no other faculty
 *  is available with the same specialty of the program
 */
class NoSpecialtyException extends Exception {
	public NoSpecialtyException(String message) {
		super(message);
	}
}

/**
 * @exception NoTAException is a custom Made Exception For this CSD class It Is
 * thrown when there is no other TA is available with the same specialty of the program
 * 
 */
class NoTAException extends Exception {
	public NoTAException(String message) {
		super(message);
	}
}
