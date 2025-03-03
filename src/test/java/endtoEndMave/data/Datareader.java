package endtoEndMave.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Datareader {

	public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//READ JSON TO STRING //Get file
		String jsonContent = FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
		//(new File (System.getProperty("user.dir")+"\\src\\test\\java\\endtoEndMave\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		//this was in File but cant be used in many test the same so every test will have its own path
		
		//String to HashMap - Jackson Datbind
	
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		return data;
 		
		
		//{map, map}
	}
}
