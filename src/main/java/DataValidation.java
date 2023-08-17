
public class DataValidation {
	public static void VerifyPassedData(String variableData, String comparedTo) {
		if (variableData.equals(comparedTo)) {
			System.out.println("Verify " + comparedTo + " : passed");
		} else {
			System.out.println("Verify " + comparedTo + " : failed, value retrieved: " + variableData);
		}
	}
	
	public static void VerifyPassedData(int variableData, int comparedTo) {
		if (variableData == comparedTo) {
			System.out.println("Verify " + comparedTo + " : passed");
		}else {
			System.out.println("Verify " + comparedTo + " : failed, value retrieved: " + variableData);
		}			
	}

	public static void VerifyPassedData(boolean variableData, boolean comparedTo) {
		if (variableData == comparedTo) {
			System.out.println("Verify " + comparedTo + " : passed");
		}
		else {
			System.out.println("Verify " + comparedTo + " : failed, value retrieved: " + variableData);
		} 
	}
}
