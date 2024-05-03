package Policy;

import java.io.Serializable;

public class Vehicle implements Serializable {
	int plateNb;
	int modelYear;
	String manufacture;
	int estimatedValue;
	int carDamage;
	
	public Vehicle(int plateNb, int modelYear, String manufacture, int estimatedValue, int carDamage) {
		super();
		this.plateNb = plateNb;
		this.modelYear = modelYear;
		this.manufacture = manufacture;
		this.estimatedValue = estimatedValue;
		this.carDamage = carDamage;
	}

	public int getPlateNb() {
		return plateNb;
	}

	public int getModelYear() {
		return modelYear;
	}

	public String getManufacture() {
		return manufacture;
	}

	public int getEstimatedValue() {
		return estimatedValue;
	}

	public int getCarDamage() {
		return carDamage;
	}

	@Override
	public String toString() {
		return "Plate No=" + plateNb + 
				"\nModel Year=" + modelYear +
				"\nManufacture=" + manufacture
				+ "\nEstimated Value=" + estimatedValue + 
				"\nCarDamage Level=" + carDamage;
	}
	
	
}
