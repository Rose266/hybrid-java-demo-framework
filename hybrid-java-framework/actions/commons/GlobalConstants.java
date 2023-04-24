package commons;

import java.io.File;

public class GlobalConstants {
	public final static String PROJECT_PATH = System.getProperty("user.dir");
	public final static String OS_NAME = System.getProperty("os.name");
	public final static String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "reportNGScreenshot" + File.separator;
	public final static String UPLOAD_FILE_PATH = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	
	public final static long LONG_TIMEOUT = 30;
	public final static long WAIT_TIMEOUT = 15;
	public final static long SHORT_TIMEOUT = 5;
	public final static long RETRY_TEST_FAIL = 3;
	
}
