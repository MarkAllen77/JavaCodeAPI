import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StartCode {

		static String ArrayName = null;
		static String ArrayDescription = null;
		static String varApiURL = null;

		private static String readAll(Reader rd) throws IOException {
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
			  sb.append((char) cp);
			}
			return sb.toString();
		}

		public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
			InputStream is = new URL(url).openStream();
			try {
			  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			  String jsonText = readAll(rd);
			  JSONObject json = new JSONObject(jsonText);
			  return json;
			} finally {
			  is.close();
			}
		}
		
		public static String RetrieveArrayObject(String ObjectArrayField, String ObjectArrayFieldContent) {
			JSONObject json;
			
			try {
				json = readJsonFromUrl(varApiURL);
				
				JSONObject obj = new JSONObject(json.toString());
				
				
				JSONArray arr = obj.getJSONArray(ObjectArrayField);
				for (int i = 0; i < arr.length(); i++)
				{
					ArrayName = arr.getJSONObject(i).getString("Name");
					ArrayDescription = arr.getJSONObject(i).getString("Description");
					if (ArrayName.equals("Feature")) {
						break;
					}
				}
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
			return ArrayDescription;
		}
			
		public static String RetrieveStringObject(String ObjectField) {
			JSONObject json;
			String VarObjectString = null;
			
			try {
				json = readJsonFromUrl(varApiURL);
				
				JSONObject obj = new JSONObject(json.toString());
				
				VarObjectString = obj.getString(ObjectField);		
				
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
			return VarObjectString;
		}

		public static Boolean RetrieveBooleanObject(String ObjectField) {
			JSONObject json;
			Boolean VarObjectBoolean = null;

			try {
				json = readJsonFromUrl(varApiURL);
				
				JSONObject obj = new JSONObject(json.toString());
				
				VarObjectBoolean = obj.getBoolean(ObjectField);
				
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
			return VarObjectBoolean;
		}

		public static Integer RetrieveIntegerObject(String ObjectField) {
			JSONObject json;
			int VarObjectInteger = 0;
			
			try {
				json = readJsonFromUrl(varApiURL);
				
				JSONObject obj = new JSONObject(json.toString());
				
				VarObjectInteger = obj.getInt(ObjectField);		
				
			} catch (JSONException | IOException e) {
				e.printStackTrace();
			}
			return VarObjectInteger;
		}
		
		public static void InitializeAPIURL() {
			try {
				String configFilePath = "./Config/config.properties";
				FileInputStream propertyInput = new FileInputStream(configFilePath);
				Properties prop = new Properties();
				prop.load(propertyInput);
				
				varApiURL = prop.getProperty("PROPERTYAPIURL");
								 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		  
		public static void main(String[] args) {
			InitializeAPIURL();
			
			DataValidation.VerifyPassedData(RetrieveStringObject("Name"), "Home & garden");
			DataValidation.VerifyPassedData(RetrieveBooleanObject("CanRelist"), true);
			DataValidation.VerifyPassedData(RetrieveIntegerObject("CategoryId"), 6329);
			DataValidation.VerifyPassedData(RetrieveArrayObject("Promotions","Feature"), "Better position in category");
		}
}
