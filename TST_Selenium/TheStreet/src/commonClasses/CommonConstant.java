package commonClasses;

public class CommonConstant
{
	public static final String streeturl = "http://awsstage.thestreet.com";
	public static final String workingDir =	System.getProperty("user.dir"); //Get current working directory
	public static final String Config_File_path = workingDir.concat("\\commonProperties\\Locators");
	public static final String Test_Data_Path = workingDir.concat("\\Test Data\\Test_data.xlsx");
	public static final String sheet = "Valid-data";
}
