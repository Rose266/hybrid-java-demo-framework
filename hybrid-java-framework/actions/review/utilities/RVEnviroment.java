package review.utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:enviromentConfig/${env}.properties")
public interface RVEnviroment extends Config {
	@Key("App.Url")
	String url();
	
	@Key("App.User")
	String userName();
	
	@Key("App.Pass")
	String pass();
	
	@Key("DB.Host")
	String dbHost();
	
	@Key("DB.User")
	String dbUserName();
	
	@Key("DB.Pass")
	String dbPass();
}
