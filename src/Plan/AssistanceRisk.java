package Plan;

public class AssistanceRisk extends Risk{

	@Override
	public float getPremium() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCoverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getCeiling() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String[] assistanceRisksCovered = {
			"Fire",
			"Robbery",
			"Transport",
			"Car Replacement"
	};

}
