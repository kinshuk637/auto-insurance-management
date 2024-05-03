package mainscreen;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import Plan.AllRisk;
import Plan.AssistanceRisk;
import Plan.DriverRisk;
import Plan.ObligatoryRisk;
import Plan.VehicleRisk;
import Policy.Customer;
import Policy.Policy;
import Policy.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainScreen extends JFrame {
	//Customization
	Font myFont = new Font("SansSerif",Font.BOLD,20);
	Color myColor = Color.GRAY;
	
	//Panel1
	
	JTextField subFName;
	JTextField subLName;
	JTextField subCity;
	JTextField subPhone;
	
	//Panel2
	JTextField plateNb;
	JTextField model;
	JTextField manufacturer;
	JTextField estimated;
	
	JRadioButton damageRadio1;
	JRadioButton damageRadio2;
	JRadioButton damageRadio3;
	JRadioButton damageRadio4;
	
	ButtonGroup G1;
	
	//Panel 3
	
	JCheckBox obligatoryCHKBX;
	JCheckBox allRiskCHKBX;
	JCheckBox vDamageCHKBX;
	JCheckBox dDamageCHKBX;
	JCheckBox assisCHKBX;
	
	List<String> coveredRisksList = new ArrayList<>();
	List<Float> premiumRisksList = new ArrayList<>();
	List<Float> coverageRisksList = new ArrayList<>();
	List<Float> ceilingRiskList = new ArrayList<>();
	
	//Panel 4
	JRadioButton yearRadio1;
	JRadioButton yearRadio2;
	JRadioButton yearRadio3;
	ButtonGroup G2;
	JLabel todayLBL;
	int validityYear;
	SimpleDateFormat df;
	Date currentDate;
	
	//Panel 5
	JTextArea risksTXT;
	JTextField searchTXT;
	Map<Integer, Customer> customerMap = new TreeMap<>();
	
	//Panel 7
	JTextArea policyTXT;
	
	//Panel 8
	JTextArea customerTXT;
	
	//Panel 9
	JLabel claimingTXT;
	JLabel claimingTXT2;
	JTextField claimingCustomerField;
	
	//Panel10
	JLabel claimingCustomerNameLBL;
	JLabel claimStatusLBL2;
	JTextArea claimingCustomerRisksCoveredAREA;
	JLabel claimingCustomerValidDateLBL;
	boolean cond1;
	boolean cond2;
	boolean cond3;
	
	//Panel 11
	JTextArea settlementArea;
	float totalPremium = 0f;
	float totalCoverage = 0f;
	float totalCeiling = 0f;
	
	//Panel12
	JTextArea settlementArea2;
	
	
	
	
	//Constructor
	public MainScreen() {
		CustomizePanel1();
		CustomizePanel2();
		CustomizePanel3();
		CustomizePanel4();
		CustomizePanel5();
		CustomizePanel6();
		CustomizePanel7();
		CustomizePanel8();
		CustomizePanel9();
		CustomizePanel10();
		CustomizePanel11();
		CustomizePanel12();
	}
	
	private void CustomizePanel12() {
		JPanel p12 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Settlements",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p12.setBorder(titledBorder);
		p12.setBounds(1275,250,250,500);  //x,y,width,height
		p12.setLayout(new GridLayout(2,1)); //rows, cols
		
		settlementArea2 = new JTextArea();
		settlementArea2.setOpaque(false);
		
		//increase the size of the font in the jtextarea
		Font font = settlementArea2.getFont();
		float size = font.getSize() + 4.0f;
		settlementArea2.setFont(font.deriveFont(size));
		
		p12.add(settlementArea2);
		add(p12);
	}
	
	private void CustomizePanel11() {
		JPanel p11 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Payments",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p11.setBorder(titledBorder);
		p11.setBounds(1275,15,250,230);  //x,y,width,height
		p11.setLayout(new GridLayout(2,1)); //rows, cols
		
		settlementArea = new JTextArea();
		settlementArea.setOpaque(false);
		
		//increase the size of the font in the jtextarea
		Font font = settlementArea.getFont();
		float size = font.getSize() + 4.0f;
		settlementArea.setFont(font.deriveFont(size));
		
		p11.add(settlementArea);
		add(p11);
	}
	
	private void CustomizePanel10() {
		JPanel p10 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Claim Status",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p10.setBorder(titledBorder);
		p10.setBounds(960,520,300,230);  //x,y,width,height
		p10.setLayout(new GridLayout(4,1)); //rows, cols
		
		//JLabels
		claimingCustomerNameLBL = new JLabel("Claiming Customer: ");
		claimingCustomerValidDateLBL = new JLabel("Date Validity of Policy: ");
		claimStatusLBL2 = new JLabel("Claiming Status: ");
		
		//JTextArea
		claimingCustomerRisksCoveredAREA = new JTextArea();
		JScrollPane pictureScrollPane = new JScrollPane(claimingCustomerRisksCoveredAREA);
		claimingCustomerRisksCoveredAREA.setOpaque(false);
		
		p10.add(claimingCustomerNameLBL);
		p10.add(claimingCustomerValidDateLBL);
		p10.add(pictureScrollPane);
		p10.add(claimStatusLBL2);
		
		add(p10);
		
		
	}
	
	private void CustomizePanel9() {
		JPanel p9 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Claims",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p9.setBorder(titledBorder);
		p9.setBounds(960,15,300,485);  //x,y,width,height
		
		claimingTXT = new JLabel("Enter Plate No. for the Claiming Customer");
		JLabel spacer99 = new JLabel("                                             ");
		claimingTXT2 = new JLabel("Select the Type of Damage or Assistance Needed");
		
		claimingCustomerField = new JTextField();
		claimingCustomerField.setPreferredSize(new Dimension(250,30)); //width, height
		claimingCustomerField.setOpaque(false);
		
		String[] items = {"Fire",
				"Robbery",
				"Third Party Damage",
				"Vehicle Damage",
				"Driver Damage",
				"Transport",
				"Car Replacement"};
		
		final JList<String> claimList = new JList<String>(items);
		
		claimList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		claimList.setOpaque(false);
		
		claimList.setPreferredSize(new Dimension(250,150)); //width, height
		
		JButton searchClaimer = new JButton("Search Customer");
		List<String> coveredRisksByUserLIST = new ArrayList<String>();
		searchClaimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cond1 = false; // is the customer registered before
				cond2 = false; // does the customer's policy cover the claimed risk
				cond3 = false; // is the policy date valid
				
				try {
					coveredRisksByUserLIST.clear();
					Customer c = claimsSearchCustomerByMobileNo();
					claimingCustomerNameLBL.setText("Claiming Customer: "+c.getFname()+" "+c.getLname()+"\n");
					cond1=true;
					
					String str7="";
					for(int i=0;i<c.getPolicy().getRisksCoveredLIST().size();i++) {
						str7+= c.getPolicy().getRisksCoveredLIST().get(i)+" \n";
						coveredRisksByUserLIST.add(c.getPolicy().getRisksCoveredLIST().get(i));
					}
					
					LocalDate validityOfPolicy = c.getPolicy().getPolicyDate();
					int policyValidityYear = c.getPolicy().getValidityYear();
					validityOfPolicy = validityOfPolicy.plusYears(policyValidityYear);
					
					checkPolicyValidity(validityOfPolicy);
					
					claimingCustomerRisksCoveredAREA.setText("Covered Risks by Customer plan:\n"+str7);
					claimingCustomerValidDateLBL.setText("Date Validity of Policy: "+validityOfPolicy);
					
					claimingCustomerRisksCoveredAREA.setText("Covered Risks by customer plan:\n"+str7);
					claimingCustomerValidDateLBL.setText("Date Validity of Policy: "+validityOfPolicy+" || "+checkPolicyValidity(validityOfPolicy));
					
					if(c.getPolicy().getRisksCoveredLIST().size()>=5) {
						// if the user has a all risks plan
						cond2 = true;
					}
				}catch(Exception e1) {
					claimingCustomerNameLBL.setText("Claiming customer: Not Found");
				}
			}
		});
		
		JButton confirmClaimBTN = new JButton("Confirm Claim");
		confirmClaimBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get the index of all the selected items
				int[] selectedIx = claimList.getSelectedIndices();
				List<String> claimed_list = new ArrayList<>();
				
				if(claimList.getSelectedIndex()!=-1) {
					for(int i=0;i<selectedIx.length;i++) {
						String k = "" + claimList.getModel().getElementAt(selectedIx[i])+" ";
						claimed_list.add(""+claimList.getModel().getElementAt(selectedIx[i]));
						System.out.print(""+k);
					}
				}
				
				//Check for the included risks
				if(cond2==false) {
					cond2 = claimed_list.containsAll(coveredRisksByUserLIST);
				}
				
				// check for claim validty to add the claim to the customer
				//claims map on other file myfile2
				claimIsValid();
				
				// Display Settlements
				Customer c = claimsSearchCustomerByMobileNo();
				
				if(claimIsValid()) {
					int claims_nb = claimed_list.size();
					if(claimed_list.contains("Fire")) {
						settlementArea2.setText("Fire Department: "+
					            c.getPolicy().getVehicle().getEstimatedValue()*0.25+" $");
					}else if(claimed_list.contains("Robbery")) {
						settlementArea2.setText("ProSec Company: "+c.getPolicy().getVehicle().getEstimatedValue()*0.5+" $"+
								"Pay for Customer: "+c.getPolicy().getVehicle().getEstimatedValue()*1+" $");
					}else if(claimed_list.contains("Third Party Damage")) {
						settlementArea2.setText("Drive in other Car: "+2000+"$");
					}else if(claimed_list.contains("Vehicle Damage")) {
						settlementArea2.setText("Pay for Customer: "+c.getPolicy().getVehicle().getEstimatedValue()*1+" $");
					}else if(claimed_list.contains("Driver Damage")) {
						settlementArea2.setText("Pay for Customer: "+c.getPolicy().getVehicle().getEstimatedValue()*1+" $");
					}else if(claimed_list.contains("Transport")) {
						settlementArea2.setText("Transport Company: "+c.getPolicy().getVehicle().getEstimatedValue()*0.5+" $");
					}else if(claimed_list.contains("Car Replacement")) {
						settlementArea2.setText("Car Rental Company: "+c.getPolicy().getVehicle().getEstimatedValue()*0.2+" $");
					}else if(claims_nb>2) {
						settlementArea2.setText("Driver "+
								c.getPolicy().getVehicle().getEstimatedValue()*4+" $"+
								"Hospital "+c.getPolicy().getVehicle().getEstimatedValue()*4+" $"+
								"Car Rental Company "+c.getPolicy().getVehicle().getEstimatedValue()*0.2+" $"+
								"Third Party Driver "+c.getPolicy().getVehicle().getEstimatedValue()*2+" $");
					}
				}
			}
		});
		
		p9.add(claimingTXT);
		p9.add(claimingCustomerField);
		p9.add(searchClaimer);
		p9.add(spacer99);
		p9.add(claimingTXT2);
		p9.add(claimList);
		p9.add(confirmClaimBTN);
		
		add(p9);
		
	}
	
	private void CustomizePanel8() {
		JPanel p8 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Customer Details",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p8.setBorder(titledBorder);
		p8.setBounds(645,520,300,230);  //x,y,width,height
		p8.setLayout(new GridLayout(6,1)); //rows, cols
		
		customerTXT = new JTextArea(20,1); //rows,cols
		customerTXT.setEditable(false);
		customerTXT.setOpaque(false);
		customerTXT.setLineWrap(true);
		
		//increase the size of the font in the jtextarea
		Font font = customerTXT.getFont();
		float size = font.getSize() + 3.0f;
		customerTXT.setFont(font.deriveFont(size));
		
		p8.add(customerTXT);
		
		p8.setLayout(new GridLayout(1,1));
		add(p8);
	}
	
	private void CustomizePanel7() {
		JPanel p7 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Policy Details",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p7.setBorder(titledBorder);
		p7.setBounds(645,250,300,250);  //x,y,width,height
		p7.setLayout(new GridLayout(6,1)); //rows, cols
		
		policyTXT = new JTextArea(20,1); //rows,cols
		policyTXT.setEditable(false);
		policyTXT.setOpaque(false);
		policyTXT.setLineWrap(true);
		
		//Font
		Font font = policyTXT.getFont();
		float size = font.getSize() + 3.0f;
		policyTXT.setFont(font.deriveFont(size));
		
		p7.add(policyTXT);
		p7.setLayout(new GridLayout(1,1)); //rows, columns
		add(p7); // adding panel7 to jframe
	}
	
	private void CustomizePanel6() {
		JPanel p6 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Covered Risks",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p6.setBorder(titledBorder);
		p6.setBounds(645,15,300,200);  //x,y,width,height
		
		risksTXT = new JTextArea(7,1); // rows, cols
		risksTXT.setEditable(false);
		risksTXT.setOpaque(false);
		risksTXT.setLineWrap(true);
		
		//Font
		Font font = risksTXT.getFont();
		float size = font.getSize() + 3.0f;
		risksTXT.setFont(font.deriveFont(size));
		
		p6.add(risksTXT);
		p6.setLayout(new GridLayout(1,1));
		
		add(p6); // adding p6 to jframe
		
	}
	
	private void CustomizePanel5() {
		JPanel p5 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Actions",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p5.setBorder(titledBorder);
		p5.setBounds(330,520,300,230);  //x,y,width,height
		p5.setLayout(new GridLayout(7,1));  // rows,cols
		
		JButton saveBTN = new JButton("Save Customer");
		JButton showBTN = new JButton("Show Plan Details");
		JButton loadBTN = new JButton("Load Customer");
		JButton newBTN = new JButton("New Customer");
		
		searchTXT = new JTextField("Enter Car Plate No.");
		searchTXT.setOpaque(false);
		
		showBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add here the code to be executed
				String str = "";
				for(int i=0;i<coveredRisksList.size();i++) {
					str+=coveredRisksList.get(i)+"\n";
				}
				risksTXT.setText(str);
				System.out.println(str);
				
				try {
					policyTXT.setText(getPolicyData().toString());
					System.out.println(getPolicyData().toString());
					displayPaymentsOfPolicy();
				}catch(ParseException parseException){
					parseException.printStackTrace();
				}
			}
		});
		
		saveBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(plateNb.getText());
				System.out.println(model.getText());
				// Add here the code to be executed	
				try {
					saveCustomerMapToDisk();
				}catch(ParseException parseException){
					parseException.printStackTrace();
				}catch(IOException ioException) {
					ioException.printStackTrace();
				}catch(ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				}
			}
		});
		
		JLabel spacer3 = new JLabel(" ");
		spacer3.setOpaque(false);
		
		loadBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add here the code to be executed	
				searchCustomerByMobileNo();
				
			}
		});
		
		JLabel spacer4 = new JLabel(" ");
		spacer4.setOpaque(false);
		
		JLabel spacer5 = new JLabel(" ");
		spacer5.setOpaque(false);
		
		JLabel spacer6 = new JLabel(" ");
		spacer6.setOpaque(false);
		
		newBTN.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Add here the code to be executed	
				NewCustomer();
				
			}
		});
	
		p5.add(spacer6);
		p5.add(showBTN);
		p5.add(saveBTN);
		p5.add(newBTN);
		p5.add(spacer5);
		p5.add(searchTXT);
		p5.add(loadBTN);
		
		add(p5);
	}
	
	private void CustomizePanel4() {
		JPanel p4 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Validity Period",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p4.setBorder(titledBorder);
		p4.setBounds(330,250,300,250);  //x,y,width,height
		p4.setLayout(new GridLayout(6,1));  // rows,cols
		
		//Radio Buttons
		JLabel spacer2 = new JLabel(" ");
		spacer2.setOpaque(false);
		
		yearRadio1 = new JRadioButton();
		yearRadio1.setText(" 1 year");
		yearRadio2 = new JRadioButton();
		yearRadio2.setText(" 2 years");
		yearRadio3 = new JRadioButton();
		yearRadio3.setText(" 3 years");
		
		yearRadio1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validityYear = 1;
			}
		});
		
		yearRadio2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validityYear = 2;
			}
		});
		
		yearRadio3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				validityYear = 3;
			}
		});
		
		//Button Group
		G2 = new ButtonGroup();
		G2.add(yearRadio1);
		G2.add(yearRadio2);
		G2.add(yearRadio3);
		
		//Time and Date
		todayLBL = new JLabel();
		df = new SimpleDateFormat("dd/MM/yyyy");
		currentDate = new Date();
		todayLBL.setText("Today: "+df.format(currentDate));
		todayLBL.setOpaque(false);
		
		Font font = todayLBL.getFont();
		float size = font.getSize() + 3.0f;
		todayLBL.setFont(font.deriveFont(size));
		
		//Adding all components to panel 4
		p4.add(spacer2);
		p4.add(spacer2);
		p4.add(todayLBL);
		p4.add(spacer2);
		p4.add(yearRadio1);
		p4.add(yearRadio2);
		p4.add(yearRadio3);
		
		//Adding panel4 to jframe
		add(p4);
		
	}
	
	private void CustomizePanel3() {
		JPanel p3 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Plan",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p3.setBorder(titledBorder);
		p3.setBounds(330,15,300,200);  //x,y,width,height
		p3.setLayout(new GridLayout(6,1));  // rows,cols
		
		JLabel packageLBL = new JLabel("Please select your plan:");
		
		//CheckBox
		obligatoryCHKBX = new JCheckBox("Obligatory");
		allRiskCHKBX = new JCheckBox("All Risk");
		vDamageCHKBX = new JCheckBox("Vehicle Damage");
		dDamageCHKBX = new JCheckBox("Driver Damage");
		assisCHKBX = new JCheckBox("Assistance");
		
		//Get all risks covered by plan
		getRisksCoveredByPlan();
		
		//Adding components to Panel 3
		p3.add(packageLBL);
		p3.add(obligatoryCHKBX);
		p3.add(allRiskCHKBX);
		p3.add(vDamageCHKBX);
		p3.add(dDamageCHKBX);
		p3.add(assisCHKBX);
		
		add(p3);
		
	}
	
	
	private void CustomizePanel2() {
		JPanel p2 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Vehicle",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p2.setBorder(titledBorder);
		
		//JLabel p2
		JLabel plateNbLBL = new JLabel("Plate No.");
		JLabel modelLBL = new JLabel("Model Year");
		JLabel manufacturerLBL = new JLabel("Manufacturer");
		JLabel estimatedLBL = new JLabel("Estimated Value");
		JLabel spaceLBL = new JLabel(" ");
		JLabel damageLBL = new JLabel("Major Damage");
		
		//JTextFields
		plateNb = new JTextField();
		plateNb.setOpaque(false);
		
		model = new JTextField();
		model.setOpaque(false);
		
		manufacturer = new JTextField();
		manufacturer.setOpaque(false);
		
		estimated = new JTextField();
		estimated.setOpaque(false);
		
		//Radio Buttons
		damageRadio1 = new JRadioButton();
		damageRadio1.setText(" Motor");
		
		damageRadio2 = new JRadioButton();
		damageRadio2.setText(" Wheels");
		
		damageRadio3 = new JRadioButton();
		damageRadio3.setText(" Body");
		
		damageRadio4 = new JRadioButton();
		damageRadio4.setText(" None");
		
		G1 = new ButtonGroup();
		G1.add(damageRadio1);
		G1.add(damageRadio2);
		G1.add(damageRadio3);
		G1.add(damageRadio4);
		
		// Adding components to panel2
		p2.add(plateNbLBL);
		p2.add(plateNb);
		
		p2.add(modelLBL);
		p2.add(model);
		
		p2.add(manufacturerLBL);
		p2.add(manufacturer);
		
		p2.add(estimatedLBL);
		p2.add(estimated);
		
		p2.add(spaceLBL);
		
		p2.add(damageLBL);
		p2.add(damageRadio1);
		p2.add(damageRadio2);
		p2.add(damageRadio3);
		p2.add(damageRadio4);
		
		//Customize panel2
		p2.setBounds(15,250,300,500);
		p2.setLayout(new GridLayout(14,1));
		setLayout(null);
		add(p2);
		
		
		
	}

	
	
	
	private void CustomizePanel1() {
		JPanel p1 = new JPanel();
		TitledBorder titledBorder = 
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray,1),
						"Customer",TitledBorder.CENTER,
						TitledBorder.DEFAULT_POSITION,myFont,myColor);
		
		p1.setBorder(titledBorder);
		
		JLabel FnameLBL = new JLabel("First Name");
		JLabel LnameLBL = new JLabel("Last Name");
		JLabel CityLBL = new JLabel("City");
		JLabel PhoneLBL = new JLabel("Phone No");
		
		subFName = new JTextField();
		subFName.setOpaque(false);
		
		subLName = 	new JTextField();
		subLName.setOpaque(false);
		
		subCity = new JTextField();
		subCity.setOpaque(false);
		
		subPhone = new JTextField();
		subPhone.setOpaque(false);
		
		p1.add(FnameLBL);
		p1.add(subFName);
		p1.add(LnameLBL);
		p1.add(subLName);
		p1.add(CityLBL);
		p1.add(subCity);
		p1.add(PhoneLBL);
		p1.add(subPhone);
		
		p1.setBounds(15,15,300,200);
		p1.setLayout(new GridLayout(4,2));
		
		//adding panels to JFrame
		setLayout(null);
		add(p1);
		
		//Main function
		
	}
	
	/**************************Methods**********************************/
	/*******************************************************************/
	
	// Get customer data
	public Customer getCustomerData() throws ParseException {
		Customer customer = new Customer(
		subFName.getText(),
		subLName.getText(),
		subCity.getText(),
		Integer.parseInt(subPhone.getText()),
		getPolicyData()
		);
		return customer;
	}
	
	// Get Vehicle Data
	public Vehicle getVehicleData() throws ParseException{
		Vehicle vehicle = new Vehicle(
				Integer.parseInt(plateNb.getText()),
				Integer.parseInt(model.getText()),
				manufacturer.getText(),
				Integer.parseInt(estimated.getText()),
				getDamageState()
				);
		return vehicle;
	}
	
	// Get Policy Data
	public Policy getPolicyData() throws ParseException{
		currentDate = new Date();
		LocalDate now = LocalDate.now();
		Policy policy = new Policy(getVehicleData(), 
				coveredRisksList,
				premiumRisksList, 
				coverageRisksList,
				ceilingRiskList,
				validityYear, 
				now);
		
		return policy;
	}
	
	// Get Damage Data
	public int getDamageState() {
		if(damageRadio1.isSelected()) {
			return 1;
		}else if(damageRadio2.isSelected()) {
			return 2;
		}else if(damageRadio3.isSelected()) {
			return 3;
		}else {
			return 0;
		}

	}
	
	// Get Plan Details
	public void getRisksCoveredByPlan() {
		AllRisk allRisk = new AllRisk();
		ObligatoryRisk obligatoryRisk = new ObligatoryRisk();
		allRiskCHKBX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dDamageCHKBX.setEnabled(false);
				vDamageCHKBX.setEnabled(false);
				assisCHKBX.setEnabled(false);
				obligatoryCHKBX.setEnabled(false);
				
				//Adding Risk Details to an array
				for(int i=0;i<allRisk.allRisksCovered.length;i++) {
					coveredRisksList.add(allRisk.allRisksCovered[i]);
				}
				premiumRisksList.add(allRisk.getPremium());
				coverageRisksList.add(allRisk.getCoverage());
				ceilingRiskList.add(allRisk.getCeiling());
			}
			
		});
		
		obligatoryCHKBX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// adding risk details to an array
				coveredRisksList.add(obligatoryRisk.obligatoryRisksCovered[0]);
				premiumRisksList.add(obligatoryRisk.getPremium());
				coverageRisksList.add(obligatoryRisk.getCoverage());
				ceilingRiskList.add(obligatoryRisk.getCeiling());
				
			}
		});
		
		vDamageCHKBX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VehicleRisk vehicleRisk = new VehicleRisk();
				// adding risk details to an array
				coveredRisksList.add(vehicleRisk.vehicleRisksCovered[0]);
				premiumRisksList.add(vehicleRisk.getPremium());
				coverageRisksList.add(vehicleRisk.getCoverage());
				ceilingRiskList.add(vehicleRisk.getCeiling());
				
			}
		});
		
		dDamageCHKBX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DriverRisk driverRisk = new DriverRisk();
				// adding risk details to an array
				coveredRisksList.add(driverRisk.driverRisksCovered[0]);
				premiumRisksList.add(driverRisk.getPremium());
				coverageRisksList.add(driverRisk.getCoverage());
				ceilingRiskList.add(driverRisk.getCeiling());
			}
		});
		
		assisCHKBX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssistanceRisk assistanceRisk = new AssistanceRisk();
				// Adding risk details to an array
				for(int i=0;i<assistanceRisk.assistanceRisksCovered.length;i++) {
					coveredRisksList.add(assistanceRisk.assistanceRisksCovered[i]);
				}
				premiumRisksList.add(assistanceRisk.getPremium());
				coverageRisksList.add(assistanceRisk.getCoverage());
				ceilingRiskList.add(assistanceRisk.getCeiling());
				
			}
		});
	}
	
	
	// Saving Dat to disk
	public void saveCustomerMapToDisk() throws IOException, ClassNotFoundException, ParseException {
		File file = new File("D:/auto-insurance-data/myfile.dat");
		int plateNum = Integer.parseInt(plateNb.getText());
		
		if(!file.exists()) {
			//Creating a new file
			System.out.println("File doesn't exist");
			file.createNewFile();
			
			saveCustomerMapToNewFile(plateNum,file);
		}else {
			//file exists
			TreeMap<Integer,Customer> newMapToSave = new TreeMap<Integer, Customer>();
			InputStream inpStr = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(inpStr);
			
			TreeMap<Integer,Customer> mapInFile = (TreeMap<Integer, Customer>) ois.readObject();
			ois.close();
			inpStr.close();
			
			// Get old map
			for(Map.Entry<Integer,Customer> m: mapInFile.entrySet()) {
				newMapToSave.put(m.getKey(), m.getValue());
			}
			
			//Updating the map, adding new customer to map
			newMapToSave.put(plateNum, getCustomerData());
			
			// Saving the updates to file
			OutputStream os = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(newMapToSave);
			oos.flush();
			oos.close();
			
		}
		
	}
	
	public void saveCustomerMapToNewFile(int plateNum, File file) throws ParseException,IOException {
		try {
		TreeMap<Integer,Customer> newMapToSave = new TreeMap<Integer, Customer>();
		
		//Adding new Customer to map
		newMapToSave.put(plateNum, getCustomerData());
		
		OutputStream os = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(newMapToSave);
		oos.flush();
		oos.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	// Resetting fields to empty
	private void NewCustomer() {
		coveredRisksList.clear();
		coverageRisksList.clear();
		premiumRisksList.clear();
		ceilingRiskList.clear();
		
		cond1 = false;
		cond2 = false;
		cond3 = false;
		
		//Set text fields to empty
		subFName.setText("");
		subLName.setText("");
		subCity.setText("");
		subPhone.setText("");
		plateNb.setText("");
		model.setText("");
		manufacturer.setText("");
		estimated.setText("");
		
		// set radio button selection to non
		G1.clearSelection();
		G2.clearSelection();
		
		//Reset checkbox
		obligatoryCHKBX.setSelected(false);
		allRiskCHKBX.setSelected(false);
		vDamageCHKBX.setSelected(false);
		dDamageCHKBX.setSelected(false);
		assisCHKBX.setSelected(false);
		
		// enable the checkboxes
		dDamageCHKBX.setEnabled(true);
		vDamageCHKBX.setEnabled(true);
		assisCHKBX.setEnabled(true);
		obligatoryCHKBX.setEnabled(true);
	}
	
	private void searchCustomerByMobileNo() {
		File file = new File("D:/auto-insurance-data/myfile.dat");
		try{
			InputStream inpStr = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(inpStr);
			
			TreeMap<Integer,Customer> mapInFile = (TreeMap<Integer,Customer>)ois.readObject();
			
			ois.close();
			inpStr.close();
			
			Customer cust_found = mapInFile.get(Integer.parseInt(searchTXT.getText()));
			customerTXT.setText(cust_found.toString());
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private Customer claimsSearchCustomerByMobileNo() {
		Customer customer = new Customer();
		File file = new File("D:/auto-insurance-data/myfile.dat");
		
		try {
			InputStream inpStr = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(inpStr);
			
			TreeMap<Integer, Customer> mapInFile = (TreeMap<Integer,Customer>)ois.readObject();
			
			ois.close();
			inpStr.close();
			
			customer = mapInFile.get(Integer.parseInt(searchTXT.getText()));
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public boolean checkPolicyValidity(LocalDate validityOfPolicy) {
		LocalDate now = LocalDate.now();
		if(now.isBefore(validityOfPolicy)) {
			cond3 = true;
			return true;
		}else {
			cond3 = false;
			return false;
		}
	}
	
	public boolean claimIsValid() {
		if(cond1==true && cond2==true && cond3==true) {
			claimStatusLBL2.setText("Claiming Status: You can register the Claim");
			return true;
		}else {
			claimStatusLBL2.setText("Claiming Status: Not able to register the Claim");
			return false;
		}
	}
	
	private void displayPaymentsOfPolicy() {
		for(int i=0;i<premiumRisksList.size();i++) {
			totalPremium+=premiumRisksList.get(i);
			totalCoverage+=coverageRisksList.get(i);
			totalCeiling+=ceilingRiskList.get(i);
		}
		
		settlementArea.setText(
				"Total Premium: "+totalPremium*Integer.parseInt(estimated.getText())+"$ \n"+
		        "Risks Coverage: "+ totalCoverage*Integer.parseInt(estimated.getText())*10+"$ \n"+
				"Max Ceiling: "+totalCeiling*Integer.parseInt(estimated.getText())+100000+"$ \n");
	}
	
	
	public static void main(String[] args) {
		MainScreen mainScreen = new MainScreen();
		mainScreen.setVisible(true);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.setTitle("Auto Insurance Management");
		mainScreen.setBounds(0,0,1920,1080);
		
	}
	}
