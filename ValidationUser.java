package com.chainsys.epassproject.validation;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;

import com.chainsys.epassproject.myexception.InvalidAadharNoException;
import com.chainsys.epassproject.myexception.InvalidApplicantNameException;
import com.chainsys.epassproject.myexception.InvalidApplicationNoException;
import com.chainsys.epassproject.myexception.InvalidDobException;
import com.chainsys.epassproject.myexception.InvalidGenderException;
import com.chainsys.epassproject.myexception.InvalidMailIdException;
import com.chainsys.epassproject.myexception.InvalidMobileNoException;
import com.chainsys.epassproject.myexception.InvalidNameException;
import com.chainsys.epassproject.myexception.InvalidNoOfPassengers;
import com.chainsys.epassproject.myexception.InvalidPasswordException;
import com.chainsys.epassproject.myexception.InvalidReasonException;
import com.chainsys.epassproject.myexception.InvalidReturnDateException;
import com.chainsys.epassproject.myexception.InvalidSourceException;
import com.chainsys.epassproject.myexception.InvalidTicketNoException;
import com.chainsys.epassproject.myexception.InvalidVaccinationCertificateException;
import com.chainsys.epassproject.myexception.InvalidVehicleNoException;

public class ValidationUser {
	public boolean aadharNoValidation(long adharNo,Model model) {
		String regex="^[^0-1]\\d{11}$"; 
		  String adharNo1=Long.toString(adharNo);
		  Pattern pattern=Pattern.compile(regex);
	    	Matcher match=pattern.matcher(adharNo1); 
	    	boolean boolean1=match.matches();//check matching 
	    	if(boolean1) {
	    		return true;
	    		
	    	} 
	    	else
	    	{
	    	String errorMessage = "Invalid aadhar no!!It must be contain 12 digits and allowed only numeric values";
		    model.addAttribute("errorMessage1", errorMessage);
		    return false;	
	    	}
	}
	
	public static boolean nameValidation(String name,Model model){
		boolean match=name.matches("[a-z A-Z ]+");//Initializing name format
		if(match) { 
			return true;
    }
		else {
			String errorMessage = "Name should contains only letters ";
		    model.addAttribute("errorMessage2", errorMessage);
		    return false;
		}
}
	
    public static boolean mailIdValidation(String email,Model model){
    	String regex="^[a-zA-Z0-9+.-]+@[a-zA-Z+.-]+$"; //Initializing mail format
    	Pattern pattern=Pattern.compile(regex);
    	Matcher match=pattern.matcher(email);
    	boolean boolean2=match.matches();//check matching
    
    	if(boolean2) {
    		return true;
    	}
    	else {
    		String errorMessage = "Invalid mail id!! it should contain only alphabets after @ symbol";
		    model.addAttribute("errorMessage3", errorMessage);
		    return false;
    }
    }

   public static boolean passwordValidation(String password,Model model) {
  if(password.length() >8 ) {
    return true;
    
   }
    	else {
    		String errorMessage = "Invalid password!! It should be greater than 8 characters,not null values";
		    model.addAttribute("errorMessage4", errorMessage);
		    return false;
    	}
    	
    }

	public static boolean mobileNoValidation(long mobileNo,Model model) {
		String regex="\\d{10}"; //Initializing mobile no format
		  String mobileNo1=Long.toString(mobileNo);
		  Pattern pattern=Pattern.compile(regex);
	    	Matcher match=pattern.matcher(mobileNo1);
	    	boolean boolean2=match.matches();//check matching
	    	if(boolean2) {
	    		return true;
	    		
	    	}
	    	else {
	    		String errorMessage = "Invalid mobileNo!! It must be contain 10 digits and allowed only numeric values";
			    model.addAttribute("errorMessage5", errorMessage);
			    return false;   }
	
 }

    public boolean applicationNoValidation(long applicationNo,Model model)  {
    if(applicationNo!=0) {
	return true;
    }
       else {
    	   String errorMessage = "Invalid applicationNo!!!It doesn't have null values";
		    model.addAttribute("errorMessage6", errorMessage);
		    return false;}
 }
    
    public static boolean applicantNameValidation(String applicantname,Model model){
		boolean matches=applicantname.matches("^[A-Za-z]*$");//Initializing name format
		if(matches) { 
			return true;
    }
		else {
			String errorMessage = "Applicant Name should contains only letters ";
		    model.addAttribute("errorMessage7", errorMessage);
		    return false;
		}
}
    
    public boolean genderValidation(String gender,Model model) {
    String regex="^([M|m]ale|[F|f]emale|[T|t]ransgender)$";
    Pattern pattern=Pattern.compile(regex);
  	Matcher match=pattern.matcher(gender);
  	boolean boolean1=match.matches();//check matching
  	if(boolean1) {
  		return true;
  		
  	}
  	else {
  		String errorMessage = "Name should contains only letters ";
	    model.addAttribute("errorMessage8", errorMessage);
	    return false;}
    }

  	public boolean dobValidation(Date dob,Model model)  {
  	String regex="([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    String dob1=dob.toString();
  	Pattern pattern=Pattern.compile(regex);
  	Matcher match=pattern.matcher(dob1);
  	boolean boolean1=match.matches();//check matching
  	if(boolean1) {
  		return true;
  		
  	}
  	else {
  		String errorMessage = "Invalid dob!! dob match a date in dd/mm/yyyy or dd-mm-yyyy format";
	    model.addAttribute("errorMessage9", errorMessage);
	    return false;  	}
 }
  	
	public boolean noOfPassengersValidation(int noOfPassengers,Model model)  {
	 
		if(noOfPassengers<=5) {
			return true;
		}
		else {
			String errorMessage = "Invalid passengers!! passengers must be 5 members only allowed";
		    model.addAttribute("errorMessage10", errorMessage);
		    return false;
		
	 }
	}
	
	public boolean dateValidation(Date returnDate,Model model ) {	
	  	String regex="([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
	  	String  returnDate1= returnDate.toString();
	  	Pattern pattern=Pattern.compile(regex);
	  	Matcher match=pattern.matcher( returnDate1);
	  	boolean boolean1=match.matches();//check matching
	  	if(boolean1) {
	  		return true;
	  		
	  	}
	  	else {
	  		String errorMessage = "Invalid travel date!! travel date match a date in dd/mm/yyyy or dd-mm-yyyy format";
		    model.addAttribute("errorMessage11", errorMessage);
		    return false;		  	}
	 }
	
	public boolean vaccinationCertificateValidation(String vaccinationCertificate,Model model) {
		 
		if(vaccinationCertificate.equals("Yes")){
			return true;
		}
		else {
			String errorMessage = "Yes is Required";
		    model.addAttribute("errorMessage12", errorMessage);
		    return false;			
	 }
	}
	
    public static boolean vehicleNoValidation(String vehicleNo,Model model) {
       String regex="^[A-Z]{2}[ ][\\d]{1,2}(?: [A-Z])?(?: [A-Z]{2})? [\\d]{4}$";
	  Pattern pattern=Pattern.compile(regex);
	    	Matcher match=pattern.matcher(vehicleNo);
	    	boolean boolean1=match.matches();//check matching
	    	if(boolean1) {
	   	return true;	
	    	}
	    	else {
	    		String errorMessage ="Invalid vehicles no!!! Only upper case and numbers are allowed Similar TN 45 BN 1234";
			    model.addAttribute("errorMessage13", errorMessage);
			    return false;	}
	
}
    
    public static boolean ticketNoValidation(String ticketNo,Model model) {
    
          String regex=	"^[A-Z]{2,3}[\\d]{10}$";
		  Pattern pattern=Pattern.compile(regex);
	    	Matcher match=pattern.matcher(ticketNo);
	    	boolean boolean1=match.matches();//check matching
	    	if(boolean1) {
	    		return true;
	    		
	    	}
	    	else {
	    		String errorMessage = "13 Digits only allowed!!! Only upper case and numbers are allowed. (SGP9876543210 or SG79876543210";
			    model.addAttribute("errorMessage14", errorMessage);
			    return false;	 }
	
}
    public static boolean reasonValidation(String reason,Model model){
    	if(reason.equals("logistic_deliveries")|| reason.equals("medical_supply")|| reason.equals("Essential_services") || reason.equals("medical_emergency")|| reason.equals("Ecommerce_delivery_boys")) {
           return true;
           
    	}
    	else {
    		String errorMessage = "Name should contains only alphabets ";
		    model.addAttribute("errorMessage15", errorMessage);
		    return false;	    	}
    }
    public static boolean placeValidation( String source,Model model){
		boolean matches=source.matches("[a-z A-Z ]+");//Initializing name format
		if(matches) { 
			return true;
    }
		else {
			String errorMessage = "Invalid data!! Only allowed the alphabets ";
		    model.addAttribute("errorMessage16", errorMessage);
		    return false;	
		    }
    }

public boolean checkStatus(String status,Model model) {
	if(status.equals("Approved")) {
		return true;
	}
	else {
		String errorMessage = "Your Status is not Approved";
	    model.addAttribute("errorMessage19", errorMessage);
	    return false;
	}
}	

	public boolean checkTravelStatus(String action,Model model) {
		if(action.equals("Approved")) {
			return true;
		}
		else {
			String errorMessage = "Your Status is not Approved";
		    model.addAttribute("errorMessage20", errorMessage);
		    return false;
		}
	
}
	 public Boolean districtMatch(String fromDistrict,String toDistrict) {
		    String toDistrict1= toDistrict;
		    
		    String fromDistrict1 = fromDistrict;
		    if(!toDistrict1.equals(fromDistrict1)) {
		        return true;
		    }
		    else
		  return false;        
		    
		}
}


