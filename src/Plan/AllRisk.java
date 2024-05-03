package Plan;

public class AllRisk extends Risk {

	@Override
	public float getPremium() {
		// TODO Auto-generated method stub
		return 0.075f;
	}

	@Override
	public float getCoverage() {
		// TODO Auto-generated method stub
		return 1f;
	}

	@Override
	public float getCeiling() {
		// TODO Auto-generated method stub
		return 10;
	}
	
	public String[] allRisksCovered = {
			"Fire",
			"Robbery",
			"Third Party Damage",
			"Vehicle Damage",
			"Driver Damage",
			"Transport",
			"Car Replacement"
			};
}
