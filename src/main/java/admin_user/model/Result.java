package admin_user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Result {
    @Id
    private String rollnumber;
    private String section;
    
    @Column(nullable = true)
    private Integer nscq1;
    @Column(nullable = true)
    private Integer nscq2;
    @Column(nullable = true)
    private Integer nscq3;
    @Column(nullable = true)
    private Integer nscq4;
    @Column(nullable = true)
    private Integer nscq5;
    @Column(nullable = true)
    private Integer NSC_Average;
    
    @Column(nullable = true)
    private Integer ccq1;
    @Column(nullable = true)
    private Integer ccq2;
    @Column(nullable = true)
    private Integer ccq3;
    @Column(nullable = true)
    private Integer ccq4;
    @Column(nullable = true)
    private Integer ccq5;
    @Column(nullable = true)
    private Integer CC_Average;
    
    @Column(nullable = true)
    private Integer mwaq1;
    @Column(nullable = true)
    private Integer mwaq2;
    @Column(nullable = true)
    private Integer mwaq3;
    @Column(nullable = true)
    private Integer mwaq4;
    @Column(nullable = true)
    private Integer mwaq5;
    @Column(nullable = true)
    private Integer MWA_Average;
    
    @Column(nullable = true)
    private Integer dlq1;
    @Column(nullable = true)
    private Integer dlq2;
    @Column(nullable = true)
    private Integer dlq3;
    @Column(nullable = true)
    private Integer dlq4;
    @Column(nullable = true)
    private Integer dlq5;
    @Column(nullable = true)
    private Integer DL_Average;
    
    @Column(nullable = true)
    private Integer amq1;
    @Column(nullable = true)
    private Integer amq2;
    @Column(nullable = true)
    private Integer amq3;
    @Column(nullable = true)
    private Integer amq4;
    @Column(nullable = true)
    private Integer amq5;
    @Column(nullable = true)
    private Integer AM_Average;
    
    
    @Column(nullable = true)
    private Integer uhvq1;
    @Column(nullable = true)
    private Integer uhvq2;
    @Column(nullable = true)
    private Integer uhvq3;
    @Column(nullable = true)
    private Integer uhvq4;
    @Column(nullable = true)
    private Integer uhvq5;
    @Column (nullable = true)
    private Integer UHV_Average;
    // Constructors, getters, and setters
    
    
    // You can add constructors and getters/setters for the fields here.
    // Make sure to generate appropriate constructors, getters, and setters for all fields.

    public Result() {
        // Default constructor
    }

    public Result(String rollnumber, String section) {
    	super();
    	this.rollnumber = rollnumber;
    	this.section = section;
    }
    
	public String getRollnumber() {
		return rollnumber;
	}


	public void setRollnumber(String rollnumber) {
		this.rollnumber = rollnumber;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public Integer getNscq1() {
		return nscq1;
	}


	public void setNscq1(Integer nscq1) {
		this.nscq1 = nscq1;
	}


	public Integer getNscq2() {
		return nscq2;
	}


	public void setNscq2(Integer nscq2) {
		this.nscq2 = nscq2;
	}


	public Integer getNscq3() {
		return nscq3;
	}


	public void setNscq3(Integer nscq3) {
		this.nscq3 = nscq3;
	}


	public Integer getNscq4() {
		return nscq4;
	}


	public void setNscq4(Integer nscq4) {
		this.nscq4 = nscq4;
	}


	public Integer getNscq5() {
		return nscq5;
	}


	public void setNscq5(Integer nscq5) {
		this.nscq5 = nscq5;
	}

	public Integer getCcq1() {
		return ccq1;
	}


	public void setCcq1(Integer ccq1) {
		this.ccq1 = ccq1;
	}


	public Integer getCcq2() {
		return ccq2;
	}


	public void setCcq2(Integer ccq2) {
		this.ccq2 = ccq2;
	}


	public Integer getCcq3() {
		return ccq3;
	}


	public void setCcq3(Integer ccq3) {
		this.ccq3 = ccq3;
	}

	public Integer getCcq4() {
		return ccq4;
	}


	public void setCcq4(Integer ccq4) {
		this.ccq4 = ccq4;
	}


	
	public Integer getCcq5() {
		return ccq5;
	}


	public void setCcq5(Integer ccq5) {
		this.ccq5 = ccq5;
	}


	public Integer getMwaq1() {
		return mwaq1;
	}


	public void setMwaq1(Integer mwaq1) {
		this.mwaq1 = mwaq1;
	}


	public Integer getMwaq2() {
		return mwaq2;
	}


	public void setMwaq2(Integer mwaq2) {
		this.mwaq2 = mwaq2;
	}


	public Integer getMwaq3() {
		return mwaq3;
	}


	public void setMwaq3(Integer mwaq3) {
		this.mwaq3 = mwaq3;
	}

	public Integer getMwaq4() {
		return mwaq4;
	}


	public void setMwaq4(Integer mwaq4) {
		this.mwaq4 = mwaq4;
	}


	public Integer getMwaq5() {
		return mwaq5;
	}


	public void setMwaq5(Integer mwaq5) {
		this.mwaq5 = mwaq5;
	}

	public Integer getDlq1() {
		return dlq1;
	}


	public void setDlq1(Integer dlq1) {
		this.dlq1 = dlq1;
	}


	public Integer getDlq2() {
		return dlq2;
	}


	public void setDlq2(Integer dlq2) {
		this.dlq2 = dlq2;
	}


	public Integer getDlq3() {
		return dlq3;
	}


	public void setDlq3(Integer dlq3) {
		this.dlq3 = dlq3;
	}


	public Integer getDlq4() {
		return dlq4;
	}


	public void setDlq4(Integer dlq4) {
		this.dlq4 = dlq4;
	}


	public Integer getDlq5() {
		return dlq5;
	}


	public void setDlq5(Integer dlq5) {
		this.dlq5 = dlq5;
	}



	public Integer getAmq1() {
		return amq1;
	}


	public void setAmq1(Integer amq1) {
		this.amq1 = amq1;
	}

	public Integer getAmq2() {
		return amq2;
	}


	public void setAmq2(Integer amq2) {
		this.amq2 = amq2;
	}


	public Integer getAmq3() {
		return amq3;
	}


	public void setAmq3(Integer amq3) {
		this.amq3 = amq3;
	}


	public Integer getAmq4() {
		return amq4;
	}


	public void setAmq4(Integer amq4) {
		this.amq4 = amq4;
	}

	public Integer getAmq5() {
		return amq5;
	}


	public void setAmq5(Integer amq5) {
		this.amq5 = amq5;
	}


	public Integer getUhvq1() {
		return uhvq1;
	}


	public void setUhvq1(Integer uhvq1) {
		this.uhvq1 = uhvq1;
	}


	public Integer getUhvq2() {
		return uhvq2;
	}


	public void setUhvq2(Integer uhvq2) {
		this.uhvq2 = uhvq2;
	}



	public Integer getUhvq3() {
		return uhvq3;
	}


	public void setUhvq3(Integer uhvq3) {
		this.uhvq3 = uhvq3;
	}

	public Integer getUhvq4() {
		return uhvq4;
	}


	public void setUhvq4(Integer uhvq4) {
		this.uhvq4 = uhvq4;
	}



	public Integer getUhvq5() {
		return uhvq5;
	}


	public void setUhvq5(Integer uhvq5) {
		this.uhvq5 = uhvq5;
	}

	public Integer getNSC_Average() {
		return NSC_Average;
	}

	public void setNSC_Average(Integer nSC_Average) {
		NSC_Average = nSC_Average;
	}

	public Integer getCC_Average() {
		return CC_Average;
	}

	public void setCC_Average(Integer cC_Average) {
		CC_Average = cC_Average;
	}

	public Integer getMWA_Average() {
		return MWA_Average;
	}

	public void setMWA_Average(Integer mWA_Average) {
		MWA_Average = mWA_Average;
	}

	public Integer getDL_Average() {
		return DL_Average;
	}

	public void setDL_Average(Integer dL_Average) {
		DL_Average = dL_Average;
	}

	public Integer getAM_Average() {
		return AM_Average;
	}

	public void setAM_Average(Integer aM_Average) {
		AM_Average = aM_Average;
	}

	public Integer getUHV_Average() {
		return UHV_Average;
	}

	public void setUHV_Average(Integer uHV_Average) {
		UHV_Average = uHV_Average;
	}
	
    // Add getters and setters for all fields
}